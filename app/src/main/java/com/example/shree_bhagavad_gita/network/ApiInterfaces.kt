package com.example.shree_bhagavad_gita.network

import com.example.shree_bhagavad_gita.model.ChapterSummary
import com.example.shree_bhagavad_gita.model.VerseModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface ChapterApi {
    @GET("chapters/")
    fun getChapter(@Query("skip") skip:String,@Query("limit") limit:String): Call<Array<ChapterSummary>>;

    @GET("chapters/{page}/")
    fun getChapterByPage(@Path("page") page:Int):Call<ChapterSummary>
}


interface VerseApi {
    @GET("chapters/{chapter}/verses/")
    fun getAllVerse(@Path("chapter") chapter:String):Call<ArrayList<VerseModel>>
}