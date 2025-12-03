package es.aiglesiasp.petshelter.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import es.aiglesiasp.petshelter.data.datasources.LoginLocalDataSource
import es.aiglesiasp.petshelter.data.datasources.PetLocalDataSource
import es.aiglesiasp.petshelter.data.datasources.ShelterLocalDataSource
import es.aiglesiasp.petshelter.framework.datasources.LoginLocalDataSourceImpl
import es.aiglesiasp.petshelter.framework.datasources.PetLocalDataSourceImpl
import es.aiglesiasp.petshelter.framework.datasources.ShelterLocalDataSourceImpl

@Module
@InstallIn(SingletonComponent::class)
abstract class LocalModule {
    @Binds
    abstract fun providesLoginLocalDataSource(impl: LoginLocalDataSourceImpl): LoginLocalDataSource

    @Binds
    abstract fun providesPetLocalDataSource(impl: PetLocalDataSourceImpl): PetLocalDataSource

    @Binds
    abstract fun providesShelterLocalDataSource(impl: ShelterLocalDataSourceImpl): ShelterLocalDataSource
}