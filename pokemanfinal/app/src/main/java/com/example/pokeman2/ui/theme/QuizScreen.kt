package com.example.pokeman2.ui.theme



import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInHorizontally
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.dp
import com.example.pokeman2.viewmodel.QuizQuestion
import com.example.pokeman2.viewmodel.QuizViewModel


@Composable
fun QuizScreen(viewModel: QuizViewModel){
    val questions by viewModel.quizQuestions.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.generateQuiz()
    }

    Column(modifier = androidx.compose.ui.Modifier
                      .fillMaxSize()
                     .padding(16.dp)) {
        if (questions.isEmpty()) {
            CircularProgressIndicator()
        } else {
            AnimatedVisibility(visible = true, enter = fadeIn() + slideInHorizontally()) {
                QuizQuestionCard(question = questions.first(), onAnswerSelected = { })
            }
        }
    }
}

@Composable
fun QuizQuestionCard(question: QuizQuestion, onAnswerSelected: (String) -> Unit) {
    var selected by remember { mutableStateOf<String?>(null) }

    Column {
        Text(text = question.question, style = MaterialTheme.typography.headlineMedium)

        question.options.forEach { option ->
            Button(
                onClick = { selected = option },
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (selected == option) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surface
                )
            ) {
                Text(option)
            }
        }
    }
}
