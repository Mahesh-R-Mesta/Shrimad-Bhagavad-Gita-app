package com.example.shree_bhagavad_gita.ui.theme.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.KeyboardArrowRight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.shree_bhagavad_gita.model.VerseModel
import com.example.shree_bhagavad_gita.ui.theme.Shree_bhagavad_gitaTheme
import com.example.shree_bhagavad_gita.ui.theme.component.orangeGradiant
import com.example.shree_bhagavad_gita.ui.theme.orange60
import com.example.shree_bhagavad_gita.view_model.SlokaViewModel

class SlokaPageActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val chapterNo =  intent.getStringExtra("chapter")
        val subject = intent.getStringExtra("subject")
        setContent {
            Shree_bhagavad_gitaTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    SlokaListingScreen(chapterNo,subject,innerPadding)
                }
            }
        }
    }
}


@Composable
fun SlokaListingScreen(chapter:String?,subject:String?, innerPadding:PaddingValues) {
    var versesArray by remember { mutableStateOf(arrayListOf<VerseModel>())}
    var loadingIndicate by remember { mutableStateOf(true) }
    val context = LocalContext.current
    val launcher = rememberLauncherForActivityResult(contract = ActivityResultContracts.StartActivityForResult()) {}

    val slokaViewModel = SlokaViewModel()
    LaunchedEffect(key1 = Unit) {
        slokaViewModel.loadSlokasByChapter(
            chapterNo = chapter,
            onResponse = { data ->
                versesArray = data
            },
            loading = { isLoading ->
                loadingIndicate = isLoading
            },
            toast =  { message ->
                Toast.makeText(context,message,Toast.LENGTH_SHORT).show()
            }
        )
    }

    fun startDetailActivity(verse:VerseModel) {
        val intent = Intent(context, SlokaDetailActivity::class.java)
        intent.putExtra("sloka",verse)
        launcher.launch(intent)
    }


    Box(modifier = Modifier
        .fillMaxSize()
        .padding(innerPadding)
        .orangeGradiant()) {
        Text(text = "Chapter-$chapter $subject", style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.SemiBold, color = Color.White), modifier = Modifier.padding(horizontal = 15.dp, vertical = 12.dp),)
        LazyColumn(modifier = Modifier.padding(top = 47.dp, start = 5.dp, end = 5.dp)) {
            items(versesArray.size) { it ->
                Box(modifier =Modifier.padding(top = 5.dp)) {
                    Card(
                        modifier = Modifier
                            .height(50.dp)
                            .fillMaxWidth()
                            .shadow(elevation = 6.dp, shape = RoundedCornerShape(5.dp)),
                        onClick = {startDetailActivity(versesArray[it])},
                        shape = RoundedCornerShape(5.dp),
                        colors = CardDefaults.cardColors(containerColor = Color.White, contentColor = Color.Black)
                    ) {
                        Text(
                            "Sloka ${it + 1}",
                            style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.SemiBold),
                            modifier = Modifier.padding(14.dp)
                        )
                    }
                    IconButton(onClick = {  startDetailActivity(versesArray[it]) }, modifier = Modifier.align(Alignment.CenterEnd)) {
                        Icon(Icons.Rounded.KeyboardArrowRight, contentDescription = "", tint = orange60)
                    }
                }
            }
            }
        if(loadingIndicate) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center),
                Color.White,
                strokeWidth = 3.5.dp
            )
        }
        }
    }




@Preview(showBackground = true)
@Composable
fun GreetingPreview3() {
    Shree_bhagavad_gitaTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        SlokaListingScreen("1","Vishada yoga",innerPadding)
        }
    }
}