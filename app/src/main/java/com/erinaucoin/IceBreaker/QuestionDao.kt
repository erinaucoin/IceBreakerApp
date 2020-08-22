package com.erinaucoin.IceBreaker

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface QuestionDao {
    @Query("SELECT * FROM question_table ORDER BY RAND() LIMIT 1")
    fun getRandomQuestion(): Question

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(question: Question)

    @Query("DELETE FROM question_table")
    suspend fun deleteAll()
}