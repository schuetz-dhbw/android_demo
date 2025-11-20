package de.dhbw.heidenheim.schuetz.simplenavigation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import dagger.hilt.android.AndroidEntryPoint
import de.dhbw.heidenheim.schuetz.simplenavigation.ui.HomeRoute
import de.dhbw.heidenheim.schuetz.simplenavigation.ui.HomeScreenContent
import de.dhbw.heidenheim.schuetz.simplenavigation.ui.ProfileRoute
import de.dhbw.heidenheim.schuetz.simplenavigation.ui.ProfileScreenContent
import de.dhbw.heidenheim.schuetz.simplenavigation.ui.SettingsRoute
import de.dhbw.heidenheim.schuetz.simplenavigation.ui.SettingsScreenContent
import de.dhbw.heidenheim.schuetz.simplenavigation.ui.SettingsViewModel
import de.dhbw.heidenheim.schuetz.simplenavigation.ui.theme.SimpleNavigationTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            // ViewModel wird von Hilt automatisch erstellt
            val settingsViewModel: SettingsViewModel = hiltViewModel()

            // Dark Mode State aus ViewModel
            val darkModeEnabled by settingsViewModel.darkModeEnabled.collectAsStateWithLifecycle()

            SimpleNavigationTheme(
                darkTheme = darkModeEnabled
            ) {

                val navController = rememberNavController()

                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = HomeRoute,
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        composable<HomeRoute> {
                            HomeScreenContent(navController = navController)
                        }
                        composable<ProfileRoute> { backStackEntry ->
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