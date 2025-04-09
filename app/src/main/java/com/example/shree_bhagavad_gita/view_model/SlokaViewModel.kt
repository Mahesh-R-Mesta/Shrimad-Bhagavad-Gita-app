package com.example.shree_bhagavad_gita.view_model

import com.example.shree_bhagavad_gita.model.VerseModel
import com.example.shree_bhagavad_gita.network.RetrofitProvider
import com.example.shree_bhagavad_gita.network.VerseApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SlokaViewModel {
    fun loadSlokasByChapter(
        chapterNo: String?,
        onResponse: (ArrayList<VerseModel>) -> Unit,
        loading:(Boolean) -> Unit,
        toast: (String?) -> Unit
    ) {
        val retrofit = RetrofitProvider.instance()
        val sloka = retrofit.create(VerseApi::class.java)
        loading(true)
        val response = sloka.getAllVerse(chapterNo?:"1");
        response.enqueue(object: Callback<ArrayList<VerseModel>> {
            override fun onResponse(
                call: Call<ArrayList<VerseModel>>,
                response: Response<ArrayList<VerseModel>>
            ) {
                if(response.isSuccessful){
                    val verses = response.body()
                    onResponse(verses?:arrayListOf())
                } else {
                    toast("Server error request failed")
                }
                loading(false)
            }
            override fun onFailure(call: Call<ArrayList<VerseModel>>, t: Throwable) {
                toast(t.message)
                loading(false)
            }
        })
    }
}