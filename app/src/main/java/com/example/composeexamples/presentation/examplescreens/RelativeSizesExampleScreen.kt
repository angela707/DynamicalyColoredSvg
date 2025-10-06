package com.example.composeexamples.presentation.examplescreens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun RelativeSizesExampleScreen() {
    Scaffold { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            verticalArrangement = Arrangement.spacedBy(32.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                FixedSizeCard()
            }
            item {
                ResponsiveCard()
            }
        }
    }
}

@Composable
fun FixedSizeCard() {
    Box(
        modifier = Modifier
            .width(400.dp)
            .height(200.dp)
            .background(Color.Gray),
        contentAlignment = Alignment.Center
    ) {
        Text(
            modifier = Modifier.size(
                width = 200.dp,
                height = 22.dp
            ),
            text = "FixedSize Card",
            textAlign = TextAlign.Center,
            fontSize = 20.sp,
            color = Color.White,
            overflow = TextOverflow.Ellipsis
        )
    }
}

@Composable
fun ResponsiveCard() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(2f)
            .padding(horizontal = 16.dp)
            .background(Color.Gray),
        contentAlignment = Alignment.Center
    ) {
        Text(
            modifier = Modifier.padding(8.dp),
            text = "Responsive Card",
            textAlign = TextAlign.Center,
            fontSize = 20.sp,
            color = Color.White,
            overflow = TextOverflow.Ellipsis
        )
    }
}
