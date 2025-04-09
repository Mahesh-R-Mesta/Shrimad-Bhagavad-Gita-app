package com.example.shree_bhagavad_gita.model

import com.google.gson.annotations.SerializedName

data class Translations(
    @SerializedName("id") var id: Int? = null,
    @SerializedName("description") var description: String? = null,
    @SerializedName("author_name") var authorName: String? = null,
    @SerializedName("language") var language: String? = null

)
