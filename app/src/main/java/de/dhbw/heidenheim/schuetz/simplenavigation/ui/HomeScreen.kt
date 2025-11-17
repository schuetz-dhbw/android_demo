package de.dhbw.heidenheim.schuetz.simplenavigation.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import de.dhbw.heidenheim.schuetz.simplenavigation.ui.theme.SimpleNavigationTheme

@Composable
fun HomeScreenContent() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text (
            text = "Home Screen",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = {}
        ) {
            Text (text = "Go to profile")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {}
        ) {
            Text (text = "Go to settings")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomePreview() {
    SimpleNavigationTheme {
        HomeScreenContent()
    }
}