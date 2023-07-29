package com.example.littlelemonfoodorderingapp

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Delete
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.RoomDatabase

@Entity
data class MenuItem(
    @PrimaryKey val id: Int,
    val title: String,
    val description: String,
    val price: Double,
    val category: String,
    val imageUrl: String
)

@Dao
interface MenuDao {
    @Query("SELECT * FROM MenuItem")
    fun getAllMenuItems(): LiveData<List<MenuItem>>

    @Insert
    fun insertAll(vararg menuItems: MenuItem)

    @Delete
    fun deleteMenuItem(menuItem: MenuItem)

    @Query("SELECT (SELECT COUNT(*) FROM MenuItem) == 0")
    fun isEmpty(): Boolean
}

@Database(entities = [MenuItem::class], version = 1)
abstract class MenuDatabase: RoomDatabase() {
    abstract fun menuDao(): MenuDao
}