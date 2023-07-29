package com.example.littlelemonfoodorderingapp

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.OutlinedButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun Profile(context: Context, navController: NavController) {
    val  sharedPreferences = context.getSharedPreferences("Little Lemon", Context.MODE_PRIVATE)
    val firstName = remember { mutableStateOf(sharedPreferences.getString("firstName", "")) }
    val lastName = remember { mutableStateOf(sharedPreferences.getString("lastName", "")) }
    val emailAddress = remember { mutableStateOf(sharedPreferences.getString("emailAddress", "")) }
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Column(
            modifier = Modifier
                .padding(25.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "Logo Image",
                modifier = Modifier
                    .fillMaxHeight(0.18f)
                    .aspectRatio(1.6f)
            )
            Text(
                text = "Profile information",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(start = 3.dp, bottom = 50.dp)
                    .height(25.dp)
            )
            OutlinedTextField(
                enabled = false,
                readOnly = true,
                value = firstName.value!!,
                textStyle = LocalTextStyle.current.copy(color = Color.Black),
                onValueChange = {},
                label = { Text(text = "First name", color = Color.Black) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 3.dp, bottom = 20.dp)
            )
            OutlinedTextField(
                enabled = false,
                readOnly = true,
                value = lastName.value!!,
                textStyle = LocalTextStyle.current.copy(color = Color.Black),
                onValueChange = {},
                label = { Text(text = "Last name", color = Color.Black) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 3.dp, bottom = 20.dp)
            )
            OutlinedTextField(
                enabled = false,
                readOnly = true,
                value = emailAddress.value!!,
                textStyle = LocalTextStyle.current.copy(color = Color.Black),
                onValueChange = {},
                label = { Text(text = "Email", color = Color.Black) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 3.dp, bottom = 20.dp)
            )
            OutlinedButton(
                onClick = { navController.navigate(Onboarding.route) },
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFFF4CE14)),
                modifier = Modifier
                    .padding(top = 48.dp)
            ) {
                Text(
                    text = "Log out",
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