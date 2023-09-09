package com.i.healthy

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.i.auth_impl.signin.ui.SignInScreen
import com.i.auth_impl.signup.ui.SignUpScreen
import com.i.healthy.ui.theme.HealthyTheme
import com.i.navigation.Navigator
import com.i.navigation.ScreenDest.AddRecordScreenDest
import com.i.navigation.ScreenDest.RecordDetailsScreenDest
import com.i.navigation.ScreenDest.RecordListScreenDest
import com.i.navigation.ScreenDest.SignInScreenDest
import com.i.navigation.ScreenDest.SignUpScreenDest
import com.i.records_impl.addrecord.ui.AddRecordScreen
import com.i.records_impl.recorddetails.ui.RecordScreen
import com.i.records_impl.recordlist.ui.RecordsListScreen
import org.koin.androidx.compose.get

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HealthyTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background,
                ) {
                    val navController: NavHostController = rememberNavController()
                    val screenNavigator: Navigator = get()
                    val screen by screenNavigator.screen.collectAsState()

                    LaunchedEffect(screen) {
                        if (navController.currentDestination?.route != screen.id) {
                            navController.navigate(screen.id)
                        }
                    }

                    NavHost(
                        modifier = Modifier,
                        navController = navController,
                        startDestination = SignInScreenDest.id
                    ) {
                        composable(SignInScreenDest.id) {
                            SignInScreen() // navController.navigate(RECORDS_LIST_SCREEN_ROUTE)
                        }
                        composable(SignUpScreenDest.id) {
                            SignUpScreen() // navController.navigate(RECORDS_LIST_SCREEN_ROUTE)
                        }
                        composable(RecordListScreenDest.id) {
                            RecordsListScreen() // navController.navigate(ADD_RECORD_SCREEN_ROUTE)
                        }
                        composable(RecordDetailsScreenDest.id) {
                            RecordScreen()
                        }
                        composable(AddRecordScreenDest.id) {
                            AddRecordScreen()
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Hello $name!",
            modifier = modifier,
            textAlign = TextAlign.Center
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    HealthyTheme {
        Greeting("Android")
    }
}
