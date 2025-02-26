package com.example.pokeman2.ui.theme

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.pokeman2.viewmodel.QuizViewModel

@JvmOverloads
@Composable
fun LeaderboardScreen(viewModel: QuizViewModel) {
    val scores by viewModel.topScores.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.loadTopScores()
    }

    LazyColumn {
        items(scores) { score ->
            Text(text = "${score.playerName}: ${score.score}", style = MaterialTheme.typography.bodyLarge)
        }
    }
}
