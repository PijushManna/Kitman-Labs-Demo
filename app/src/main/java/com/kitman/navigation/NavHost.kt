package com.kitman.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.kitman.ui.details.DetailsScreen
import com.kitman.ui.home.HomeScreen
import com.kitman.ui.login.LoginScreen


@Composable
fun NavHost() {
    val navHost = rememberNavController()
    NavHost(navHost, "login"){
        composable("login"){
            LoginScreen {
                navHost.navigate("home"){
                    popUpTo("home"){
                        inclusive = false
                    }
                }
            }
        }
        composable("home"){
            HomeScreen{
                navHost.navigate("details/${it.id}")
            }
        }
        composable("details/{id}"){
            val id = it.arguments?.getString("id")?.toInt()
            DetailsScreen(id)
        }
    }
}