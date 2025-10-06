package com.example.composeexamples.presentation.examplescreens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.composeexamples.presentation.viewmodel.CleanUiState
import com.example.composeexamples.presentation.viewmodel.ComposeViewModel
import com.example.composeexamples.presentation.viewmodel.GodUiState
import com.example.composeexamples.presentation.viewmodel.TimerInfo
import com.example.composeexamples.presentation.viewmodel.UserInfo

@Composable
fun UiStateExampleScreen() {
    val viewModel = hiltViewModel<ComposeViewModel>()
    val godUiState by viewModel.godUiState.collectAsState()
    val cleanUiState by viewModel.cleanUiState.collectAsState()

    Scaffold { paddingValues ->
        Row(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(32.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                GodDashboard(godUiState)
            }

            Column(
                modifier = Modifier.weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CleanDashboard(cleanUiState)
            }
        }
    }
}



@Composable
fun GodDashboard(uiState: GodUiState) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text("GOD STATE", style = MaterialTheme.typography.titleMedium)
        Spacer(Modifier.height(8.dp))
        GodUserInfo(uiState)
        GodTimer(uiState)
    }
}

@Composable
fun GodUserInfo(uiState: GodUiState) {
    Text("User: ${uiState.userName}")
    Text("Email: ${uiState.email}")
}

@Composable
fun GodTimer(uiState: GodUiState) {
    Text("Seconds: ${uiState.timerSeconds}")
}


@Composable
fun CleanDashboard(uiState: CleanUiState) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text("CLEAN STATE", style = MaterialTheme.typography.titleMedium)
        Spacer(Modifier.height(8.dp))
        CleanUserInfo(uiState.userInfo)
        CleanTimer(uiState.timer)
    }
}

@Composable
fun CleanUserInfo(userInfo: UserInfo) {
    Text("User: ${userInfo.userName}")
    Text("Email: ${userInfo.email}")
}

@Composable
fun CleanTimer(timer: TimerInfo) {
    Text("Seconds: ${timer.seconds}")
}



@Composable
fun Screen() {
    var count by remember { mutableStateOf(0) }

    Counter(
        count = count,
        onCountChange = { count = it }
    )
}

@Composable
fun Counter(
    count: Int,
    onCountChange: (Int) -> Unit
) {
    Column {
        Text("Count: $count")
        Button(onClick = { onCountChange(count + 1) }) {
            Text("Increment")
        }
    }
}
