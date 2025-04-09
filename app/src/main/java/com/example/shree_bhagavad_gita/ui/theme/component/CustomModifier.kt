package com.example.shree_bhagavad_gita.ui.theme.component

import androidx.compose.foundation.background
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import com.example.shree_bhagavad_gita.ui.theme.brightYellow
import com.example.shree_bhagavad_gita.ui.theme.dullPinkish


fun Modifier.orangeGradiant(): Modifier {
    return this.background(
        brush = Brush.linearGradient(
            colors = listOf(
                dullPinkish, brightYellow
            ),
            start = Offset.Zero,
            end = Offset.Infinite
        )
    )
}
