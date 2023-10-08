package com.example.artspaceapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import com.example.artspaceapp.ui.theme.ArtSpaceAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        ViewCompat.setOnApplyWindowInsetsListener(window.decorView.rootView) { _, insets ->
            insets.consumeSystemWindowInsets()
        }
        setContent {
            ArtSpaceAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background,
                ) {
                    App()
                }
            }
        }
    }
}

@Composable
fun App(modifier: Modifier = Modifier) {
    val displayWidthInDp = LocalConfiguration.current.screenWidthDp.dp
    var currentProfileId by remember { mutableStateOf(1) }
    var imageResource: Painter = painterResource(R.drawable.image_1)
    var text: String = stringResource(R.string.string_1)
    var name: String = stringResource(R.string.name_1)
    var age = ""

    when (currentProfileId) {
        0 -> {
            currentProfileId = 4
        } 1 -> {
            imageResource = painterResource(R.drawable.image_1)
            text = stringResource(R.string.string_1)
            name = stringResource(R.string.name_1)
            age = stringResource(R.string.age_1)
        } 2 -> {
            imageResource = painterResource(R.drawable.image_2)
            text = stringResource(R.string.string_2)
            name = stringResource(R.string.name_2)
            age = stringResource(R.string.age_2)
        } 3 -> {
            imageResource = painterResource(R.drawable.image_3)
            text = stringResource(R.string.string_3)
            name = stringResource(R.string.name_3)
            age = stringResource(R.string.age_3)
        } 4 -> {
            imageResource = painterResource(R.drawable.image_4)
            text = stringResource(R.string.string_4)
            name = stringResource(R.string.name_4)
            age = stringResource(R.string.age_4)
        } 5 -> {
            currentProfileId = 1
        }
    }

    Column(modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ProfilePicture(image = imageResource, width = displayWidthInDp)

        Box(modifier = modifier) {

            ProfileInfoCard(name = name, age = age, text = text)
            Spacer(modifier = Modifier.height(1150.dp))

            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
                    .align(Alignment.BottomStart)
                    .padding(start = 10.dp, end = 10.dp, bottom = 100.dp)
            ) {
                Button(
                    onClick = { currentProfileId-- },
                    colors = ButtonDefaults.buttonColors(Color.Black)
                ) {
                    Text("<", fontSize = 40.sp, color = Color.DarkGray, textAlign = TextAlign.Center)
                }
                Button(onClick = { currentProfileId++ },
                    colors = ButtonDefaults.buttonColors(Color.Black)) {
                    Text(">", fontSize = 40.sp, color = Color.DarkGray, textAlign = TextAlign.Center)
                }
            }
            Column (modifier = Modifier.align(Alignment.BottomStart)){
                Image(
                    painter = ColorPainter(Color.DarkGray),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .size(1.dp)
                )
                Image(
                    painter = ColorPainter(Color.Black),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .size(100.dp)
                )
            }
        }
    }
}

@Composable
fun ProfileInfoCard(name: String, age: String, text: String) {
    Column(modifier = Modifier
        .padding(end = 18.dp, start = 18.dp)
        .verticalScroll(rememberScrollState())
    ) {
        Text(
            text = "$name, $age",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(top = 10.dp, bottom = 25.dp)
        )
        Text(
            text = text,
            fontWeight = FontWeight.Medium,
            fontSize = 17.sp,
            modifier = Modifier.alpha(0.6f)
        )
        Spacer(modifier = Modifier.height(200.dp))
    }
}

@Composable
fun ProfilePicture(image: Painter, width: Dp) {
    Box(modifier = Modifier.fillMaxWidth()) {
        Image(
            painter = image,
            contentScale = ContentScale.Crop,
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(width) // height = width -> we get a perfect square
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ArtSpaceAppTheme {
        App()
    }
}