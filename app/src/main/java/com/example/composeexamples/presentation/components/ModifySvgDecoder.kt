package com.example.composeexamples.presentation.components

import coil3.Extras
import coil3.ImageLoader
import coil3.decode.DecodeResult
import coil3.decode.Decoder
import coil3.decode.ImageSource
import coil3.fetch.SourceFetchResult
import coil3.getOrDefault
import coil3.request.Options
import coil3.svg.SvgDecoder

class ModifySvgDecoder(
    private val result: SourceFetchResult,
    private val options: Options
) : Decoder {
    override suspend fun decode(): DecodeResult? {
        val originalSvg = result.source.source().use { it.readUtf8() }
        val isSvg = result.mimeType?.contains("svg", ignoreCase = true) == true ||
                originalSvg.contains("<svg", ignoreCase = true)
        if (!isSvg) return null

        val colors: List<String> = options.extras.getOrDefault(ColorSVGExtras.colors)
        val patternString: String = options.extras.getOrDefault(ColorSVGExtras.regex)

        val colorRegex = Regex(patternString)

        val mutated = recolorSvg(originalSvg, colors, colorRegex)

        val mutatedSource = ImageSource(
            source = okio.Buffer().writeUtf8(mutated),
            fileSystem = options.fileSystem
        )
        return SvgDecoder(mutatedSource, options).decode()
    }

    class Factory : Decoder.Factory {
        override fun create(
            result: SourceFetchResult,
            options: Options,
            imageLoader: ImageLoader
        ): Decoder {
            return ModifySvgDecoder(result, options)
        }
    }
}

private fun recolorSvg(svg: String, colors: List<String>, regex: Regex): String =
    regex.replace(svg) { mr ->
        val idx = (mr.groupValues.getOrNull(1)?.toIntOrNull() ?: 1) - 1
        val replacement = colors.getOrNull(idx)?.trim().orEmpty()
        replacement.ifEmpty { mr.groupValues.getOrNull(2).orEmpty() }
    }

object ColorSVGExtras {
    val colors: Extras.Key<List<String>> = Extras.Key(default = emptyList())
    val regex: Extras.Key<String> = Extras.Key(default = "")
    const val COLORS_KEY = "colors"
}