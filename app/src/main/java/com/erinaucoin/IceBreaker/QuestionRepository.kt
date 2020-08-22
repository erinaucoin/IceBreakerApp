package com.erinaucoin.IceBreaker

import androidx.lifecycle.LiveData

// Declares the DAO as a private property in the constructor. Pass in the DAO
// instead of the whole database, because you only need access to the DAO
class QuestionRepository(private val questionDao: QuestionDao) {

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    val nextQuestion: LiveData<Question> = questionDao.getRandomQuestion()

    suspend fun insert(question: Question) {
        questionDao.insert(question)
    }
}