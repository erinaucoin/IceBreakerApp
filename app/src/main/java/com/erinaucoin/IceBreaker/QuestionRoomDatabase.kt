package com.erinaucoin.IceBreaker

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

//class QuestionRoomDatabase {
//}

// Annotates class to be a Room Database with a table (entity) of the Question class
//TODO set up the export schema
@Database(entities = arrayOf(Question::class), version = 1, exportSchema = false)
public abstract class QuestionRoomDatabase : RoomDatabase() {

    abstract fun questionDao(): QuestionDao

    private class QuestionDatabaseCallback(private val scope: CoroutineScope) : RoomDatabase.Callback(){

        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { database -> scope.launch {
                populateDatabase(database.questionDao())
                }
            }
        }

        suspend fun populateDatabase(questionDao: QuestionDao){
            //Delete all content here.
            questionDao.deleteAll()

            //Add sample questions.
            var question = Question(0,"What is your spirit animal?")
            questionDao.insert(question)
            question = Question(0,"When you were little, what did you want to be when you grew up?")
            questionDao.insert(question)


            //TODO add more questions
        }
    }

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: QuestionRoomDatabase? = null

        fun getDatabase(context: Context, scope: CoroutineScope): QuestionRoomDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    QuestionRoomDatabase::class.java,
                    "question_database"
                )
                    .addCallback(QuestionDatabaseCallback(scope))
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}