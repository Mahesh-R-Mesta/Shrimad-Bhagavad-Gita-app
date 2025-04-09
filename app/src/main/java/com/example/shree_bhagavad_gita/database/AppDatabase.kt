package com.example.shree_bhagavad_gita.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.shree_bhagavad_gita.database.dao.ChapterDao
import com.example.shree_bhagavad_gita.database.entity.Chapter


@Database(entities = [Chapter::class], version = 1)
abstract class AppDatabase:RoomDatabase(){
    abstract fun getChapterDao(): ChapterDao
}