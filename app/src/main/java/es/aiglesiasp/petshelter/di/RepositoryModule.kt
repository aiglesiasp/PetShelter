package es.aiglesiasp.petshelter.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import es.aiglesiasp.petshelter.data.repositories.PetsRepositoryImpl
import es.aiglesiasp.petshelter.domain.repositories.PetsRepository

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindPetsRepository(impl: PetsRepositoryImpl): PetsRepository

}