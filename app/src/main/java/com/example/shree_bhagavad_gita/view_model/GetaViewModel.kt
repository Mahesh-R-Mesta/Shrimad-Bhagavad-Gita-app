package com.example.shree_bhagavad_gita.view_model

import android.content.Context
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import com.example.shree_bhagavad_gita.database.AppDatabase
import com.example.shree_bhagavad_gita.database.entity.Chapter
import com.example.shree_bhagavad_gita.model.ChapterSummary
import com.example.shree_bhagavad_gita.network.ChapterApi
import com.example.shree_bhagavad_gita.network.RetrofitProvider
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.logging.Logger


class GetaViewModel:ViewModel() {
    var chapters = mutableStateOf(mutableListOf<ChapterSummary>())
    var loading = mutableStateOf(false)

    val logger = Logger.getLogger(this.toString());

    fun insertChapter(chapters:Array<ChapterSummary>?,context:Context) {
        val modelChapter: List<Chapter> = chapters?.map { it ->
            Chapter(
                id = it.id ?: (Math.random() * 10023).toInt(),
                chapterNumber = it.chapter_number?.toString(),
                chapterSummary = it.chapter_summary,
                verseCount = it.verses_count,
                name = it.name,
                nameMeaning = it.name_meaning,
                nameTranslated = it.name_translated);

        }!!
       val db = Room.databaseBuilder(context,AppDatabase::class.java,"bhagavad-gita").build()
       val chapterTable = db.getChapterDao()
//        Thread(Runnable { chapterTable.insertAllChapter(chapter = modelChapter) })
    }


    fun loadSummaryList(context:Context) {
//        val c1 = Chapter(id = 12, name = "Hello", nameTranslated = "H2", verseCount = 5, nameMeaning = "sdsf", chapterNumber = "45",  chapterSummary = "dasd")
//        insertChapter(chapters = arrayOf(c1), context = context)
       val retrofit = RetrofitProvider.instance()
       val chapter = retrofit.create(ChapterApi::class.java)
        loading.value = true
       val response:Call<Array<ChapterSummary>> = chapter.getChapter(skip = "0", limit = "18")
        insertChapter(response.execute().body(),context);
        response.enqueue(object: Callback<Array<ChapterSummary>> {
            override fun onResponse(
                call: Call<Array<ChapterSummary>>,
                response: Response<Array<ChapterSummary>>
            ) {
                chapters.value = response.body()?.toMutableList()?: mutableListOf()
                Log.d("response",response.body().toString())
                loading.value = false
            }
            override fun onFailure(call: Call<Array<ChapterSummary>>, t: Throwable) {
                Log.e("error",t.toString())
                loading.value = false
            }

        })
    }
}