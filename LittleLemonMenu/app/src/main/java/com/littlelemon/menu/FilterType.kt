package com.littlelemon.menu

sealed class FilterType {
    object All : FilterType()
    object Drinks : FilterType()
    object Food : FilterType()
    object Dessert : FilterType()
}
