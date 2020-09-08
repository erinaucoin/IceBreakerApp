package com.erinaucoin.IceBreaker

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class QuestionViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: QuestionRepository
    // Using LiveData and caching what getRandomQuestion returns has several benefits:
    // - We can put an observer on the data (instead of polling for changes) and only update the
    //   the UI when the data actually changes.
    // - Repository is completely separated from the UI through the ViewModel.
    val nextQuestion : LiveData<Question>
    val allQuestion: LiveData<List<Question>>

    init{
        val questionsDao = QuestionRoomDatabase.getDatabase(application, viewModelScope).questionDao()
        repository = QuestionRepository(questionsDao)
        nextQuestion = repository.nextQuestion
        allQuestion = repository.allQuestions
    }

    /**
     * Launching a new coroutine to insert the data in a non-blocking way
     */

    fun insert(question: Question) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(question)
    }
}