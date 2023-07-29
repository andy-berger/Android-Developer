package com.example.littlelemonfoodorderingapp

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.OutlinedButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.DarkGray
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun Onboarding(context: Context, navController: NavController) {
    val  sharedPreferences = context.getSharedPreferences("Little Lemon", Context.MODE_PRIVATE)
    var firstname by remember {
        mutableStateOf("")
    }
    var lastname by remember {
        mutableStateOf("")
    }
    var emailaddress by remember {
        mutableStateOf("")
    }
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Column() {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "Logo Image",
                modifier = Modifier
                    .fillMaxHeight(0.18f)
                    .aspectRatio(1.6f)
            )
        }
        Column() {
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .background(color = Color(0xFF495E57))
            ) {
                Text(
                    text = "Let's get to know you",
                    color = White,
                    fontSize = 24.sp
                )
            }
        }
        Column(
            modifier = Modifier
                .padding(25.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(75.dp)
            ) {
                Text(
                    text = "Personal information",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            Text(
                text = "First name",
                fontSize = 14.sp,
                color = DarkGray,
                modifier = Modifier
                    .padding(top = 24.dp)
                    .height(25.dp)
            )
            OutlinedTextField(
                value = firstname,
                onValueChange = {firstname = it},
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
            )
            Text(
                text = "Last name",
                fontSize = 14.sp,
                color = DarkGray,
                modifier = Modifier
                    .padding(top = 24.dp)
                    .height(25.dp)
            )
            OutlinedTextField(
                value = lastname,
                onValueChange = {lastname = it},
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
            )
            Text(
                text = "Email",
                fontSize = 14.sp,
                color = DarkGray,
                modifier = Modifier
                    .padding(top = 24.dp)
                    .height(25.dp)
            )
            OutlinedTextField(
                value = emailaddress,
                onValueChange = {emailaddress = it},
                shape = RoundedCornerShape(8.dp),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
            )
            OutlinedButton(
                onClick = {
                    if (
                        firstname.isNotBlank() &&
                        lastname.isNotBlank() &&
                        android.util.Patterns.EMAIL_ADDRESS.matcher(emailaddress).matches()
                    ) {
                        sharedPreferences.edit()
                            .putString("firstName", firstname)
                            .putString("lastName", lastname)
                            .putString("emailAddress", emailaddress)
                            .putBoolean("registered", true)
                            .apply()

                        Toast.makeText(
                            context,
                            "Registration successful!",
                            Toast.LENGTH_SHORT
                        ).show()

                        navController.navigate(Home.route)
                    } else {
                        Toast.makeText(
                            context,
                            "Registration unsuccessful. Please enter all data.",
                            Toast.LENGTH_SHORT
                        ).show()
                    }},
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFFF4CE14)),
                modifier = Modifier
                    .padding(top = 48.dp)
            ) {
                Text(
                    text = "Register",
                    fontSize = 16.sp,
                    color = Color.Black,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                )
            }
        }
    }
}