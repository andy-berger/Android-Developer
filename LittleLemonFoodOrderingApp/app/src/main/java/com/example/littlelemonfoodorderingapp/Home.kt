package com.example.littlelemonfoodorderingapp

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.text.font.FontWeight.Companion.ExtraBold
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage

@Composable
fun Home(navController: NavController) {
    val viewModel: FoodItemsViewModel = viewModel()
    val databaseMenuItems = viewModel.getAllItems().observeAsState(emptyList()).value
    val searchPhrase = remember { mutableStateOf("") }
    Column() {
        Row(horizontalArrangement = Arrangement.End) {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "Logo Image",
                modifier = Modifier
                    .padding(top = 25.dp, bottom = 25.dp)
                    .weight(3.5f)
                    .height(40.dp)
            )
            Image(
                painter = painterResource(id = R.drawable.profile),
                contentDescription = "Profile Image",
                modifier = Modifier
                    .padding(top = 25.dp)
                    .weight(0.5f)
                    .height(50.dp)
                    .clickable { navController.navigate(Profile.route) }
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .background(color = Color(0xFF495E57))
                .padding(start = 10.dp)
        ) {
            Text(
                text = "Little Lemon",
                fontFamily = FontFamily(Font(R.font.markazi)),
                fontSize = 58.sp,
                color = Color(0xFFF4CE14),
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(35.dp)
                .background(color = Color(0xFF495E57))
                .padding(start = 10.dp)
        ) {
            Text(
                text = "Chicago",
                fontFamily = FontFamily(Font(R.font.markazi)),
                fontSize = 35.sp,
                color = White,
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
                .background(color = Color(0xFF495E57))
                .padding(start = 10.dp)
        ) {
            Text(
                text = "We are a family owned Mediterranean restaurant, focused on traditional recipes served with a modern twist.",
                fontFamily = FontFamily(Font(R.font.karla)),
                fontSize = 15.sp,
                color = White,
                modifier = Modifier
                    .padding(top = 20.dp)
                    .width(200.dp)
            )
            Image(
                painter = painterResource(id = R.drawable.hero_image),
                contentDescription = "Hero Logo Image",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .padding(start = 25.dp)
                    .width(140.dp)
                    .height(140.dp)
                    .clip(RoundedCornerShape(18.dp))
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = Color(0xFF495E57))
                .height(80.dp)
        ) {
            Search() { searchPhrase.value = it }
        }
        Row() {
            LaunchedEffect(Unit) {
                viewModel.getMenuData()
            }
            LowerPanel(databaseMenuItems, searchPhrase)
        }
    }
}

@Composable
fun Search(search: (parameter: String) -> Unit) {
    val searchPhrase = remember { mutableStateOf("") }
    OutlinedTextField(
        value = searchPhrase.value,
        onValueChange = {
            searchPhrase.value = it
            search(searchPhrase.value)
        },
        placeholder = { Text(
            text = "Enter search phrase",
            color = Color.Gray,
            modifier = Modifier
                .padding(start = 60.dp)
        ) },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            backgroundColor = White,
            focusedBorderColor = Color.Gray,
            unfocusedBorderColor = Color.Gray,
            cursorColor = Color.Gray,
        ),
        leadingIcon = { Icon(Icons.Filled.Search, contentDescription = null) },
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 10.dp, end = 10.dp)
            .background(Color(0xFF495E57))
    )
}

@Composable
fun LowerPanel(databaseMenuItems: List<MenuItem>, search: MutableState<String>) {
    val categories = databaseMenuItems.map {
        it.category.replaceFirstChar { character -> character.uppercase() }
    }.toSet()
    val selectedCategory = remember {
        mutableStateOf("")
    }
    val items = if (search.value == "") {
        databaseMenuItems
    } else {
        databaseMenuItems.filter {
            it.title.contains(search.value, ignoreCase = true)
        }
    }
    val filteredItems = if (selectedCategory.value == "" || selectedCategory.value == "all") {
        items
    } else {
        items.filter { it.category.contains(selectedCategory.value, ignoreCase = true) }
    }
    Column {
        MenuCategories(categories) { selectedCategory.value = it }
        Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
            for (item in filteredItems) {
                MenuItem(item = item)
            }
        }
    }
}

@Composable
fun MenuCategories(categories: Set<String>, categoryLambda: (sel: String) -> Unit) {
    val cat = remember { mutableStateOf("") }
    Column() {
        Text(
            text = "ORDER FOR DELIVERY!",
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            modifier = Modifier.padding(start = 10.dp, top = 10.dp)
        )
        Row(
            modifier = Modifier
                .horizontalScroll(rememberScrollState())
                .padding(start = 10.dp, top = 10.dp, bottom = 10.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Category(category = "All") {
                cat.value = it.lowercase()
                categoryLambda(it.lowercase())
            }
            for (category in categories) {
                Category(category = category) {
                    cat.value = it
                    categoryLambda(it)
                }
            }
        }
        Divider(
            thickness = 2.dp,
            color = Color.LightGray,
            modifier = Modifier.padding(start = 10.dp, end = 10.dp)
        )
    }
}

@Composable
fun Category(category: String, selectedCategory: (sel: String) -> Unit) {
    val clicked = remember {
        mutableStateOf(false)
    }
    Button(onClick = {
        clicked.value = !clicked.value
        selectedCategory(category)
    },
        shape = CutCornerShape(18),
        colors = ButtonDefaults.buttonColors(
            contentColor = Color(0xFF495E57),
            backgroundColor = Color(0xFFDCE3DF)
        )) {
        Text(
            text = category,
            fontWeight = ExtraBold
        )
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun MenuItem(item: MenuItem) {
    val description = if (item.description.length > 70) {
        item.description.substring(0, 70) + "..."
    } else {
        item.description
    }
    Card(
        elevation = 5.dp
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 1.dp, vertical = 5.dp)
        ) {
            Column(
                Modifier
                    .fillMaxWidth(0.74f)
                    .padding(start = 10.dp, top = 8.dp, bottom = 8.dp)
            ) {
                Text(
                    text = item.title,
                    fontWeight = Bold,
                    fontFamily = FontFamily(Font(R.font.karla)),
                    fontSize = 20.sp,
                )
                Text(
                    text = description,
                    fontFamily = FontFamily(Font(R.font.karla)),
                    fontSize = 15.sp
                )
                Text(
                    text = "$ ${item.price}",
                    fontFamily = FontFamily(Font(R.font.karla)),
                    fontSize = 18.sp
                )
            }
            GlideImage(
                model = item.imageUrl,
                contentDescription = "",
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(90.dp, 90.dp),
            )
        }
        Divider(
            thickness = 1.dp,
            color = Color.LightGray,
            modifier = Modifier.padding(start = 10.dp, end = 10.dp)
        )
    }
}