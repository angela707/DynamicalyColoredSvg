package com.example.composeexamples.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun HomeScreen(
    onNavigateToSlotting: () -> Unit,
    onNavigateToUiState: () -> Unit,
    onNavigateToLazyLayouts: () -> Unit,
    onNavigateToRelativeSizes: () -> Unit
) {
    Scaffold { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .padding(paddingValues)
                .padding(horizontal = 16.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                Button(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = onNavigateToSlotting
                ) {
                    Text(
                        "Slotting Example"
                    )
                }
            }

            item {
                Button(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = onNavigateToUiState
                ) {
                    Text("UiState Example")
                }
            }

            item {
                Button(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = onNavigateToLazyLayouts
                ) {
                    Text("Lazy Layouts Example")
                }
            }

            item {
                Button(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = onNavigateToRelativeSizes
                ) {
                    Text("Relative Sizes Example")
                }
            }
        }
    }
}