package com.example.shree_bhagavad_gita.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class Chapter(
    @PrimaryKey val id:Int,
    @ColumnInfo(name="name") val name:String?,
    @ColumnInfo(name="name_translated") val nameTranslated:String?,
    @ColumnInfo(name="name_meaning") val nameMeaning:String?,
    @ColumnInfo(name="chapter_number") val chapterNumber:String?,
    @ColumnInfo(name="chapter_summary") val chapterSummary:String?,
    @ColumnInfo(name="verse_count")  val verseCount:Int?)