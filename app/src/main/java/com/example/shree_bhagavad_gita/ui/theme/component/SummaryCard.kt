package com.example.shree_bhagavad_gita.ui.theme.component
import android.content.Intent
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowDropDown
import androidx.compose.material.icons.rounded.KeyboardArrowDown
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.shree_bhagavad_gita.model.ChapterSummary
import com.example.shree_bhagavad_gita.ui.theme.ui.SlokaPageActivity

@Composable
fun SummaryCardItem(index:Int,chapter:ChapterSummary,onClick: ()->Unit = {}) {
    var visibleSummary by remember { mutableStateOf(false) }
    val launcher = rememberLauncherForActivityResult(contract = ActivityResultContracts.StartActivityForResult()) {}
    val context = LocalContext.current
    Box(modifier = Modifier.padding(vertical = 5.dp)) {
        Card(modifier = Modifier
            .shadow(elevation = 3.dp, shape = RoundedCornerShape(10.dp)),
            colors = CardDefaults.cardColors(containerColor = Color.White, contentColor = Color.Black),
            ) {
            Column(
                modifier = Modifier
                    .padding(horizontal = 20.dp, vertical = 10.dp)
                    .fillMaxWidth()
                    .animateContentSize()
            ) {
                Text("${index + 1}) ${chapter.name_translated}",
                        style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Medium))
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    "${chapter.name_meaning}",
                    style = TextStyle(fontSize = 15.sp, fontWeight = FontWeight.Normal)
                )
                if(visibleSummary) Spacer(modifier = Modifier.height(8.dp))
                Box(
                    Modifier
                        .fillMaxWidth()
                        .background(color = Color(0xffF9FAFD), shape = RoundedCornerShape(10.dp))
                        .padding(if (visibleSummary) 15.dp else 0.dp)
                        .height(if (visibleSummary) Int.MAX_VALUE.dp else 0.dp)
                        .animateContentSize()) {
                    Column {
                        Text("Summary", style = TextStyle(fontSize = 15.sp, fontWeight = FontWeight.SemiBold))
                        Spacer(modifier = Modifier.height(5.dp))
                        Text(text = chapter.chapter_summary?:"None", style = TextStyle(color = Color(0xFF344050), fontSize = 14.sp))
                        CustomButton(label = "Open", modifier = Modifier
                            .height(40.dp)
                            .padding(top = 5.dp)
                            .align(Alignment.End),  onClick = { val intent = Intent(context,SlokaPageActivity::class.java)
                            intent.putExtra("chapter","${index+1}")
                            intent.putExtra("subject",chapter.name_translated)
                            launcher.launch(intent)
                        })
                    }
                }
//                Box(modifier = Modifier
//                    .align(Alignment.End)
//                    .padding(top = 13.dp, end = 10.dp)) {
//                    CustomButton(label = "Open", modifier = Modifier
//                        .height(35.dp),  onClick = { val intent = Intent(context,SlokaPageActivity::class.java)
//                         intent.putExtra("chapter","$index")
//                        launcher.launch(intent)
//                    })
//                }
            }

        }
        IconButton(modifier = Modifier.align(Alignment.TopEnd), onClick = {
            visibleSummary = !visibleSummary
            onClick()
        }) {
            Icon(Icons.Rounded.KeyboardArrowDown, contentDescription = "Arrow icon", modifier = Modifier
                .size(35.dp, 35.dp)
                .rotate(if (visibleSummary) 180F else 0F))
        }
    }
}

@Preview
@Composable
fun ComposeViewComponent() {
    SummaryCardItem(index = 1, chapter = ChapterSummary(
        id = 1,
        name = "Arjuna",
        name_meaning = "Testing app",
        name_transliterated = "Txeting",
        name_translated = "Vishada yoga",
        verses_count = 10,
        chapter_number =1,
        chapter_summary = "The first chapter of the Bhagavad Gita - \\\"Arjuna Vishada Yoga\\\" introduces the setup, the setting, the characters and the circumstances that led to the epic battle of Mahabharata, fought between the Pandavas and the Kauravas. It outlines the reasons that led to the revelation of the of Bhagavad Gita.\\nAs both armies stand ready for the battle, the mighty warrior Arjuna, on observing the warriors on both sides becomes increasingly sad and depressed due to the fear of losing his relatives and friends and the consequent sins attributed to killing his own relatives. So, he surrenders to Lord Krishna, seeking a solution. Thus, follows the wisdom of the Bhagavad Gita.",
        chapter_summary_hindi = "335435435345435435"
    ))
}