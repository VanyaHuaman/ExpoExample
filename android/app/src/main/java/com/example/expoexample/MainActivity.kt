package com.example.expoexample

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.expoexample.ui.theme.ExpoExampleTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ExpoExampleTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainScreen() {
                        when (it) {
                            NavigationRoute.EXPO_ACTIVITY -> {
                                val intent = Intent(
                                    this@MainActivity,
                                    ExpoActivity::class.java
                                )
                                startActivity(intent)
                            }

                            NavigationRoute.REACT_NATIVE_ACTIVITY -> {
                                val intent = Intent(
                                    this@MainActivity,
                                    ReactNativeExampleActivity::class.java
                                )
                                startActivity(intent)
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun MainScreen(navigationEvent: (navRoute: NavigationRoute) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Green),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Text(
            text = "This is just to test launching from a native screen. " +
                    "If the Expo or React Native activities then we would have the " +
                    "application launch straight into one of those",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(36.dp)
        )
        NavButton(text = "Expo Activity") {
            navigationEvent(NavigationRoute.EXPO_ACTIVITY)
        }
        NavButton(text = "React Native Activity") {
            navigationEvent(NavigationRoute.REACT_NATIVE_ACTIVITY)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    MainScreen(navigationEvent = {})
}

@Composable
fun NavButton(text: String, navFunction: () -> Unit) {
    Button(modifier = Modifier.wrapContentSize(), onClick = { navFunction() }) {
        Text(text = text)
    }
}

@Preview(showBackground = true)
@Composable
fun NavButtonPreview() {
    ExpoExampleTheme {
        NavButton("Expo Activity") {}
    }
}