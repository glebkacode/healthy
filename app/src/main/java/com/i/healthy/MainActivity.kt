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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.i.auth_impl.signin.SignInScreen
import com.i.auth_impl.signup.SignUpScreen
import com.i.healthy.ui.theme.HealthyTheme
import com.i.records_impl.addrecord.AddRecordScreen
import com.i.records_impl.recorddetails.RecordScreen
import com.i.records_impl.recordlist.RecordsListScreen

private const val SIGN_IN_SCREEN_ROUTE = "sign_in_route"
private const val SIGN_UP_SCREEN_ROUTE = "sign_up_route"
private const val RECORDS_LIST_SCREEN_ROUTE = "records_list_route"
private const val RECORD_SCREEN_ROUTE = "record_route"
private const val ADD_RECORD_SCREEN_ROUTE = "add_record_route"

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HealthyTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController: NavHostController = rememberNavController()
                    NavHost(
                        modifier = Modifier,
                        navController = navController,
                        startDestination = SIGN_IN_SCREEN_ROUTE
                    ) {
                        composable(SIGN_IN_SCREEN_ROUTE) {
                            SignInScreen(
                                onSignInCompleted = {
                                    navController.navigate(RECORDS_LIST_SCREEN_ROUTE)
                                },
                                onSignUpRequired = {
                                    navController.navigate(SIGN_UP_SCREEN_ROUTE)
                                }
                            )
                        }
                        composable(SIGN_UP_SCREEN_ROUTE) {
                            SignUpScreen {
                                navController.navigate(RECORDS_LIST_SCREEN_ROUTE)
                            }
                        }
                        composable(RECORDS_LIST_SCREEN_ROUTE) {
                            RecordsListScreen {
                                navController.navigate(ADD_RECORD_SCREEN_ROUTE)
                            }
                        }
                        composable(RECORD_SCREEN_ROUTE) {
                            RecordScreen()
                        }
                        composable(ADD_RECORD_SCREEN_ROUTE) {
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
