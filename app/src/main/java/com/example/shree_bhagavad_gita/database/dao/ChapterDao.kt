package com.example.shree_bhagavad_gita.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.shree_bhagavad_gita.database.entity.Chapter

@Dao
interface ChapterDao {
    @Query("SELECT * FROM chapter")
    fun getAllChapters():List<Chapter>

    @Insert
    fun insertAllChapter(vararg chapter:Chapter)
}