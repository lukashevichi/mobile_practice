package by.maxluxs.kr1.model.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import by.maxluxs.kr1.model.vo.HomeFlower
import by.maxluxs.kr1.model.vo.HomeFlowers
import kotlinx.coroutines.flow.Flow

@Dao
interface HomeFlowerDao {

    @Query("SELECT * FROM HOME_FLOWERS")
    fun getAll(): Flow<HomeFlowers>

    @Query("SELECT * FROM HOME_FLOWERS WHERE UUID IN (:flowerIds)")
    fun loadAllByIds(flowerIds: Array<String>): Flow<HomeFlowers>

    @Query("SELECT * FROM HOME_FLOWERS WHERE UUID IN (:flowerIds) LIMIT 1")
    fun loadById(flowerIds: String): Flow<HomeFlower>

    @Query(
        "SELECT * FROM HOME_FLOWERS WHERE NAME LIKE :name LIMIT 1"
    )
    fun findByName(name: String): Flow<HomeFlower>

    @Insert(onConflict = REPLACE)
    suspend fun insertAll(vararg users: HomeFlower)

    @Insert(onConflict = REPLACE)
    suspend fun insertAll(users: List<HomeFlower>)

    @Delete
    suspend fun delete(user: HomeFlower)
}