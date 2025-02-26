package com.example.pokeman2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import com.example.pokeman2.ui.theme.QuizScreen
import com.example.pokeman2.viewmodel.QuizViewModel

class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        enableEdgeToEdge()
        setContent {
            val quizViewModel: QuizViewModel by viewModels()
            QuizScreen(viewModel = quizViewModel)
            }
        }
    }


