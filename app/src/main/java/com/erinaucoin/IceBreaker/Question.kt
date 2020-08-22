package com.erinaucoin.IceBreaker

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "question_table")
class Question(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "question") val question: String
)