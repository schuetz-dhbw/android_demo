package de.dhbw.heidenheim.schuetz.simplenavigation.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import de.dhbw.heidenheim.schuetz.simplenavigation.ui.theme.SimpleNavigationTheme

@Composable
fun SettingsScreenContent(navController: NavController) {
    var darkModeEnabled by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Settings Screen",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(32.dp))

        Row (
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text (text = "Dark Mode")
            Switch(
                checked = darkModeEnabled,
                onCheckedChange = { darkModeEnabled = it }
            )
        }

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = {
                navController.popBackStack()
            }
        ) {
            Text (text = "Back to home")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SettingsPreview() {
    SimpleNavigationTheme {
        SettingsScreenContent(rememberNavController())
    }
}