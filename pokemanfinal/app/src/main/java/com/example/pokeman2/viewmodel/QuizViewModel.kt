package com.example.pokeman2.viewmodel
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokeman2.api.PokemonRepository
import com.example.pokeman2.data.QuizDatabase
import com.example.pokeman2.model.Score
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

data class QuizQuestion(
    val question: String,
    val correctAnswer: String,
    val options: List<String>
)

class QuizViewModel(application: Application) : AndroidViewModel(application) {
    private val db = QuizDatabase.getInstance(application).scoreDao()

    private val _quizQuestions = MutableStateFlow<List<QuizQuestion>>(emptyList())
    val quizQuestions: StateFlow<List<QuizQuestion>> = _quizQuestions

    private val _topScores = MutableStateFlow<List<Score>>(emptyList())
    val topScores: StateFlow<List<Score>> = _topScores

    fun generateQuiz() {
        viewModelScope.launch(Dispatchers.IO) {
            val names = PokemonRepository.fetchPokemonNames()
            if (names.size >= 4) {
                val questions = names.shuffled().take(5).map { name ->
                    val options = (names - name).shuffled().take(3) + name
                    QuizQuestion("Which Pokémon is this?", name, options.shuffled())
                }
                _quizQuestions.value = questions
            }
        }
    }

    fun saveScore(playerName: String, score: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            db.insertScore(Score(playerName = playerName, score = score))
        }
    }

    fun loadTopScores() {
        viewModelScope.launch(Dispatchers.IO) {
            _topScores.value = db.getTopScores()
        }
    }


}
