package by.maxluxs.kr1.di

import android.content.Context
import by.maxluxs.kr1.model.database.DataBaseFactory
import by.maxluxs.kr1.model.database.FlowerDatabase
import by.maxluxs.kr1.repositories.HomeFlowersRepository
import by.maxluxs.kr1.repositories.RepositoriesFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object FlowersModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): FlowerDatabase =
        DataBaseFactory.createFlowerDataBase(context)

    @Singleton
    @Provides
    fun provideRepository(database: FlowerDatabase): HomeFlowersRepository =
        RepositoriesFactory.create(database)

}