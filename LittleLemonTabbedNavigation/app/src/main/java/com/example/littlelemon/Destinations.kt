package com.example.littlelemon


interface Destinations {
    val route: String
    val icon: Int
    val title: String
}

object Home:Destinations {
    override val route = "Home"
    override val icon = R.drawable.ic_home
    override val title = "Home"
}

object Menu:Destinations {
    override val route = "Menu"
    override val icon = R.drawable.ic_menu
    override val title = "Menu"
}

object Location:Destinations {
    override val route = "Location"
    override val icon = R.drawable.ic_location
    override val title = "Location"
}