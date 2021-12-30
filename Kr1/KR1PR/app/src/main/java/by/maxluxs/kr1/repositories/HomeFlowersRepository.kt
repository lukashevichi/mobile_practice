package by.maxluxs.kr1.repositories

import by.maxluxs.kr1.Plants
import by.maxluxs.kr1.model.dao.HomeFlowerDao
import by.maxluxs.kr1.model.database.FlowerDatabase
import by.maxluxs.kr1.model.vo.HomeFlower
import by.maxluxs.kr1.model.vo.HomeFlowers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.mapLatest

class HomeFlowersRepository(private val database: FlowerDatabase) {

    private val flowersDao: HomeFlowerDao get() = database.homeFlowerDao()

    fun getFlowers(): Flow<HomeFlowers> = flowersDao.getAll().map {
        if (it.isEmpty()) {
            val plants = Plants.getPlug()
            insertFlowers(plants)
            plants
        } else it
    }

    fun getFlowerById(id: String): Flow<HomeFlower> = flowersDao.loadById(id)

    fun getFlowerByName(name: String): Flow<HomeFlower> = flowersDao.findByName(name)

    suspend fun insertFlower(flower: HomeFlower) = flowersDao.insertAll(flower)

    suspend fun insertFlowers(flowers: List<HomeFlower>) = flowersDao.insertAll(flowers)

    suspend fun deleteFlower(flower: HomeFlower) = flowersDao.delete(flower)

}