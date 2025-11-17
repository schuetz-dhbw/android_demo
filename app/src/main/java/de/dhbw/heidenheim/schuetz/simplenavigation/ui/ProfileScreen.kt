package de.dhbw.heidenheim.schuetz.simplenavigation.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
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
fun ProfileScreenContent(navController: NavController, name: String)
{
    var currentName by remember { mutableStateOf(name) }

    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text ="Profile Screen",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(32.dp))

        Text(text = "Hello $currentName!")

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = currentName,
            onValueChange = { currentName = it },
            label = { Text(text = "Name") }
        )

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
fun ProfilePreview() {
    SimpleNavigationTheme {
        ProfileScreenContent(rememberNavController(),"Android")
    }
}