package com.example.composeexamples.presentation.examplescreens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.composeexamples.presentation.viewmodel.CleanUiState
import com.example.composeexamples.presentation.viewmodel.ComposeViewModel
import com.example.composeexamples.presentation.viewmodel.TimerInfo
import com.example.composeexamples.presentation.viewmodel.UserInfo

@Composable
fun SlottingExampleScreen() {
    val viewModel = hiltViewModel<ComposeViewModel>()
    val uiState by viewModel.cleanUiState.collectAsState()

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
                WithoutSlottingExample(uiState)
            }

            Column(
                modifier = Modifier.weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                WithSlottingExample(
                    header = { SlottingUserInfoSection(uiState.userInfo) },
                    body = { SlottingTimerSection(uiState.timer) }
                )
            }
        }
    }
}

@Composable
fun WithoutSlottingExample(uiState: CleanUiState) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text("WITHOUT SLOTTING", style = MaterialTheme.typography.titleMedium)
        Spacer(Modifier.height(8.dp))
        Text("User: ${uiState.userInfo.userName}")
        Text("Email: ${uiState.userInfo.email}")
        Text("Seconds: ${uiState.timer.seconds}")
    }
}

@Composable
fun WithSlottingExample(
    header: @Composable () -> Unit,
    body: @Composable () -> Unit
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text("WITH SLOTTING", style = MaterialTheme.typography.titleMedium)
        Spacer(Modifier.height(8.dp))
        header()
        body()
    }
}

@Composable
fun SlottingUserInfoSection(userInfo: UserInfo) {
    Text("User: ${userInfo.userName}")
    Text("Email: ${userInfo.email}")
}

@Composable
fun SlottingTimerSection(timer: TimerInfo) {
    Text("Seconds: ${timer.seconds}")
}

