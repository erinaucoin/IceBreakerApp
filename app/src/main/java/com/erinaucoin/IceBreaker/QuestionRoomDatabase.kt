package com.erinaucoin.IceBreaker

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

//class QuestionRoomDatabase {
//}

// Annotates class to be a Room Database with a table (entity) of the Question class
//TODO set up the export schema
@Database(entities = arrayOf(Question::class), version = 1, exportSchema = false)
public abstract class QuestionRoomDatabase : RoomDatabase() {

    abstract fun questionDao(): QuestionDao

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: QuestionRoomDatabase? = null

        fun getDatabase(context: Context): QuestionRoomDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    QuestionRoomDatabase::class.java,
                    "word_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}