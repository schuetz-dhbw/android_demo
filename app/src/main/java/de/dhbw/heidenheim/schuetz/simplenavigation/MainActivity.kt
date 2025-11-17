package de.dhbw.heidenheim.schuetz.simplenavigation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import de.dhbw.heidenheim.schuetz.simplenavigation.ui.HomeRoute
import de.dhbw.heidenheim.schuetz.simplenavigation.ui.HomeScreenContent
import de.dhbw.heidenheim.schuetz.simplenavigation.ui.ProfileRoute
import de.dhbw.heidenheim.schuetz.simplenavigation.ui.ProfileScreenContent
import de.dhbw.heidenheim.schuetz.simplenavigation.ui.SettingsRoute
import de.dhbw.heidenheim.schuetz.simplenavigation.ui.SettingsScreenContent
import de.dhbw.heidenheim.schuetz.simplenavigation.ui.theme.SimpleNavigationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SimpleNavigationTheme {

                val navController = rememberNavController()

                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    // NavHost definiert alle Screens und alle verfügbaren Routen
                    // Registrierung der Screens via composable<ScreenType>
                    NavHost(
                        navController = navController,
                        startDestination = HomeRoute,
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        composable<HomeRoute> {
                            // Best Practice: Nicht ganzen navController übergeben,
                            // Sondern nur benötigte Funktionen / Parameter
                            HomeScreenContent(navController = navController)
                        }
                        composable<ProfileRoute> { backStackEntry ->
                            // Holt die übergebenen Elemente
                            val profile = backStackEntry.toRoute<ProfileRoute>()
                            ProfileScreenContent(
                                navController = navController,
                                name = profile.name
                            )
                        }
                        composable<SettingsRoute> {
                            SettingsScreenContent(navController = navController)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    SimpleNavigationTheme {
        Greeting("Android")
    }
}