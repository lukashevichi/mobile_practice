package by.maxluxs.kr1.repositories

import by.maxluxs.kr1.model.database.FlowerDatabase

object RepositoriesFactory {

    fun create(database: FlowerDatabase) = HomeFlowersRepository(database)

}