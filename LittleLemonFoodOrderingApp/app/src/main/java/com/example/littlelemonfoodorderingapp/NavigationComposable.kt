package com.example.littlelemonfoodorderingapp

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import android.content.Context
import androidx.navigation.NavController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi

@ExperimentalGlideComposeApi
@Composable
fun NavigationComposable(context: Context, navController: NavController) {
    val navController = rememberNavController()
    val sharedPreferences = context.getSharedPreferences("Little Lemon", Context.MODE_PRIVATE)
    var isLoggedIn = sharedPreferences.getBoolean("userRegistered", true)
    NavHost(
        navController = navController,
        startDestination =
        if (isLoggedIn) {
            Home.route
            } else {
            Onboarding.route
        }) {
        composable(Onboarding.route) {
            Onboarding(context, navController)
        }
        composable(Home.route) {
            Home(navController = navController)
        }
        composable(Profile.route) {
            Profile(context, navController)
        }
    }
}