package by.maxluxs.kr1.model.database

import androidx.room.Database
import androidx.room.RoomDatabase
import by.maxluxs.kr1.model.dao.HomeFlowerDao
import by.maxluxs.kr1.model.vo.HomeFlower

@Database(entities = [HomeFlower::class], exportSchema = true, version = 1)
abstract class FlowerDatabase : RoomDatabase() {
    abstract fun homeFlowerDao(): HomeFlowerDao
}