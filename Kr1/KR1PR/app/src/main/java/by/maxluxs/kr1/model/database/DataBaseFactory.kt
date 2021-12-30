package by.maxluxs.kr1.model.database

import android.content.Context
import androidx.room.Room
import by.maxluxs.kr1.model.Contract.DATA_BASE_NAME

object DataBaseFactory {

    fun createFlowerDataBase(context: Context): FlowerDatabase = Room
        .databaseBuilder(
            context,
            FlowerDatabase::class.java,
            DATA_BASE_NAME
        )
        .fallbackToDestructiveMigration()
        .build()

}