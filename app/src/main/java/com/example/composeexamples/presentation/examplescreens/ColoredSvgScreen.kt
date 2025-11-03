package com.example.composeexamples.presentation.examplescreens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composeexamples.presentation.components.FlowerImage

private const val FLOWER_SVG_URL =
    "https://raw.githubusercontent.com/angela707/DynamicalyColoredSvg/main/flower_with_placeholder_colors.svg"

@Composable
fun ColoredSvgScreen() {
    Scaffold { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            val partLabels = remember { PART_LABELS }
            val colorRows = remember { COLOR_ROWS }

            var selectedPartIndex by remember { mutableStateOf(0) }
            var chosenColors by remember { mutableStateOf(colorRows.map { it.first() }) }

            FlowerImage(
                modifier = Modifier
                    .size(400.dp)
                    .align(Alignment.CenterHorizontally),
                colorList = chosenColors.map { it.toHexString() },
                svgUrl = FLOWER_SVG_URL,
                contentScale = ContentScale.Fit
            )

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "Pick a part to customize:",
                fontSize = 18.sp,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )

            SegmentedPartPicker(
                labels = partLabels,
                selectedIndex = selectedPartIndex,
                onPartSelected = { selectedPartIndex = it }
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Select a color for ${partLabels[selectedPartIndex]}",
                fontSize = 16.sp,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )

            ColorPickerRow(
                colors = colorRows[selectedPartIndex],
                selectedColor = chosenColors[selectedPartIndex],
                onColorSelected = { color ->
                    val updated = chosenColors.toMutableList()
                    updated[selectedPartIndex] = color
                    chosenColors = updated
                }
            )
        }
    }
}

@Composable
fun SegmentedPartPicker(
    labels: List<String>,
    selectedIndex: Int,
    modifier: Modifier = Modifier,
    onPartSelected: (Int) -> Unit
) {
    LazyRow(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        items(labels.size) { index ->
            val label = labels[index]
            val isSelected = index == selectedIndex
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(12.dp))
                    .background(
                        if (isSelected) MaterialTheme.colorScheme.primary
                        else MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.6f)
                    )
                    .clickable { onPartSelected(index) }
                    .padding(horizontal = 12.dp, vertical = 8.dp)
            ) {
                Text(
                    text = label,
                    color = if (isSelected) MaterialTheme.colorScheme.onPrimary
                    else MaterialTheme.colorScheme.onSurfaceVariant,
                    fontSize = 14.sp
                )
            }
        }
    }
}

@Composable
fun ColorPickerRow(
    colors: List<Color>,
    selectedColor: Color,
    modifier: Modifier = Modifier,
    onColorSelected: (Color) -> Unit
) {
    LazyRow(
        modifier = modifier.padding(horizontal = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        items(
            items = colors,
            key = { it.value.hashCode() }
        ) { color ->
            val isSelected = color == selectedColor

            Box(
                modifier = Modifier
                    .size(40.dp)
                    .aspectRatio(1f)
                    .background(color, shape = RoundedCornerShape(6.dp))
                    .border(
                        width = if (isSelected) 4.dp else 2.dp,
                        color = if (isSelected)
                            MaterialTheme.colorScheme.onBackground
                        else
                            Color.Gray.copy(alpha = 0.6f),
                        shape = RoundedCornerShape(6.dp)
                    )
                    .clickable { onColorSelected(color) },
                contentAlignment = Alignment.Center
            ) {
                if (isSelected) {
                    Text(
                        text = "✓",
                        color = MaterialTheme.colorScheme.onPrimary,
                        fontSize = 16.sp
                    )
                }
            }
        }
    }
}

val COLOR_LIST = listOf(
    Color.Red,
    Color.Magenta,
    Color(0xFFFFA500),
    Color.Yellow,
    Color.Green,
    Color.Cyan,
    Color.Blue,
    Color(0xFF800080),
    Color(0xFF00CED1),
    Color(0xFF8B4513),
    Color(0xFFFFC0CB),
    Color(0xFFFA8072),
    Color(0xFF4682B4),
)

private val PART_LABELS = (1..8).map { "Petal $it" } + "Center"
private val COLOR_ROWS = List(9) { COLOR_LIST }


fun Color.toHexString(): String {
    val intColor =
        (this.red * 255).toInt().shl(24) or
                (this.green * 255).toInt().shl(16) or
                (this.blue * 255).toInt().shl(8) or
                (this.alpha * 255).toInt()
    return "#%08X".format(intColor)
}
