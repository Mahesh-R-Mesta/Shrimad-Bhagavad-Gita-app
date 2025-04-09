package com.example.shree_bhagavad_gita.ui.theme.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.shree_bhagavad_gita.ui.theme.Shree_bhagavad_gitaTheme
import com.example.shree_bhagavad_gita.ui.theme.component.SummaryCardItem
import com.example.shree_bhagavad_gita.ui.theme.component.orangeGradiant
import com.example.shree_bhagavad_gita.view_model.GetaViewModel

class HomeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Shree_bhagavad_gitaTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    GeetaListingPage(Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun GeetaListingPage(modifier: Modifier) {
    val geetaViewModel = GetaViewModel()
    val isLoading by remember { geetaViewModel.loading }
    val chapters by remember { geetaViewModel.chapters }
    val context = LocalContext.current
    LaunchedEffect(key1 = Unit) {
        geetaViewModel.loadSummaryList(context = context)
    }
    Box(modifier = modifier
        .fillMaxSize()
        .orangeGradiant()) {
        IconButton(modifier = Modifier.align(alignment = Alignment.TopEnd), onClick = { /*TODO*/ }) {
            Icon(Icons.Rounded.Settings, contentDescription = "settings", tint = Color.White, )
        }
        Text(text = "Bhagavad Gita Chapters", style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.SemiBold, color = Color.White),modifier = Modifier.padding(horizontal = 15.dp, vertical = 12.dp))
        LazyColumn(modifier = Modifier.padding(top = 47.dp),  contentPadding = PaddingValues(horizontal = 10.dp, vertical = 2.dp)) {
            items(chapters.size) { index ->
                SummaryCardItem(index = index, chapter = chapters[index])
            }
        }
      if(isLoading) CircularProgressIndicator(modifier=Modifier.align(Alignment.Center), Color.White, strokeWidth = 3.dp)
    }

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview2() {
    Shree_bhagavad_gitaTheme {
        GeetaListingPage(modifier = Modifier.padding(10.dp))
    }
}