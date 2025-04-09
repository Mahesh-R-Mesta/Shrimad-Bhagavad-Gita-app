package com.example.shree_bhagavad_gita.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class VerseModel(
    @SerializedName("id") var id: Int? = null,
    @SerializedName("verse_number") var verseNumber: Int? = null,
    @SerializedName("chapter_number") var chapterNumber: Int? = null,
    @SerializedName("slug") var slug: String? = null,
    @SerializedName("text") var text: String? = null,
    @SerializedName("transliteration") var transliteration : String? = null,
    @SerializedName("word_meanings") var wordMeanings : String? = null,
    @SerializedName("translations") var translations : ArrayList<Translations> = arrayListOf(),
    @SerializedName("commentaries") var commentaries : ArrayList<Commentaries> = arrayListOf()
):Serializable
