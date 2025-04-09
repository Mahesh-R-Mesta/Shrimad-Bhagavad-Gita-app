package com.example.shree_bhagavad_gita.ui.theme.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.shree_bhagavad_gita.model.VerseModel
import com.example.shree_bhagavad_gita.ui.theme.Shree_bhagavad_gitaTheme
import com.example.shree_bhagavad_gita.util.serializable

class SlokaDetailActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val verseModel = intent.serializable<VerseModel>("sloka");
        setContent {
            Shree_bhagavad_gitaTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    SlokaDetailComposeScreen(verseModel, innerPadding)
                }
            }
        }
    }
}

@Composable
fun SlokaDetailComposeScreen(verse:VerseModel?, padding:PaddingValues) {
    Box(modifier = Modifier.padding(padding).fillMaxSize()) {
        Text("Sloka-1 Arjun vishada yog")
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview4() {
    Shree_bhagavad_gitaTheme {
        Scaffold { innerPadding ->
            SlokaDetailComposeScreen(VerseModel(),innerPadding)
        }
    }
}