package by.maxluxs.kr1.model.vo

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import by.maxluxs.kr1.model.Contract.HomeFlower.CATEGORY
import by.maxluxs.kr1.model.Contract.HomeFlower.INSTRUCTIONS
import by.maxluxs.kr1.model.Contract.HomeFlower.NAME
import by.maxluxs.kr1.model.Contract.HomeFlower.PHOTO
import by.maxluxs.kr1.model.Contract.HomeFlower.TABLE_NAME
import by.maxluxs.kr1.model.Contract.HomeFlower.UUID

@Entity(tableName = TABLE_NAME)
data class HomeFlower(
    @ColumnInfo(name = CATEGORY)
    val category: String,
    @ColumnInfo(name = INSTRUCTIONS)
    val instructions: String,
    @ColumnInfo(name = PHOTO)
    val photo: String,
    @ColumnInfo(name = NAME)
    val name: String,
    @PrimaryKey @ColumnInfo(name = UUID)
    val uuid: String
)

typealias HomeFlowers = List<HomeFlower>