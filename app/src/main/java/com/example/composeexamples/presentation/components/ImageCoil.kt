package com.example.composeexamples.presentation.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import coil3.ImageLoader
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.svg.SvgDecoder

private const val FLOWER_REGEX =
    """var\(--placeholder-color-(\d+)(?:,\s*(#[0-9a-fA-F]{3,8}))?\)"""

@Composable
fun FlowerImage(
    modifier: Modifier = Modifier,
    svgUrl: String,
    colorList: List<String>,
    contentScale: ContentScale,
    colorFilter: ColorFilter? = null,
    regexPattern: String = FLOWER_REGEX
) {
    ColouredImageCoil(
        modifier = modifier,
        svgUrl = svgUrl,
        colorList = colorList,
        contentScale = contentScale,
        colorFilter = colorFilter,
        regexPattern = regexPattern
    )
}

@Composable
fun ColouredImageCoil(
    svgUrl: String,
    colorList: List<String>,
    contentScale: ContentScale,
    regexPattern: String,
    modifier: Modifier = Modifier,
    colorFilter: ColorFilter? = null,
    contentDescription: String? = null
) {
    val context = LocalContext.current

    val imageLoader = remember(context) {
        ImageLoader.Builder(context)
            .components {
                add(ModifySvgDecoder.Factory())
                add(SvgDecoder.Factory())
            }
            .build()
    }

    val request = ImageRequest.Builder(context)
        .data(svgUrl)
        .apply {
            extras[ColorSVGExtras.colors] = colorList
            extras[ColorSVGExtras.regex] = regexPattern
        }
        .memoryCacheKeyExtra(ColorSVGExtras.COLORS_KEY, colorList.joinToString(","))
        .build()

    AsyncImage(
        model = request,
        imageLoader = imageLoader,
        contentDescription = contentDescription,
        modifier = modifier,
        contentScale = contentScale,
        colorFilter = colorFilter
    )
}
