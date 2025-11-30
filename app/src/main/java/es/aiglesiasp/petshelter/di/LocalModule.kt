package es.aiglesiasp.petshelter.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import es.aiglesiasp.petshelter.data.datasources.PetLocalDataSource
import es.aiglesiasp.petshelter.framework.datasources.PetLocalDataSourceImpl

@Module
@InstallIn(SingletonComponent::class)
abstract class LocalModule {
    @Binds
    abstract fun providesPetLocalDataSource(impl: PetLocalDataSourceImpl): PetLocalDataSource
}