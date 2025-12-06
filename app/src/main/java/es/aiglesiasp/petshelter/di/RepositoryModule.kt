package es.aiglesiasp.petshelter.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import es.aiglesiasp.petshelter.data.repositories.LoginRepositoryImpl
import es.aiglesiasp.petshelter.data.repositories.PetsRepositoryImpl
import es.aiglesiasp.petshelter.data.repositories.SharedPreferencesRepositoryImpl
import es.aiglesiasp.petshelter.data.repositories.ShelterRepositoryImpl
import es.aiglesiasp.petshelter.domain.repositories.LoginRepository
import es.aiglesiasp.petshelter.domain.repositories.PetsRepository
import es.aiglesiasp.petshelter.domain.repositories.SharedPreferencesRepository
import es.aiglesiasp.petshelter.domain.repositories.ShelterRepository

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindLoginRepository(impl: LoginRepositoryImpl): LoginRepository

    @Binds
    abstract fun bindPetsRepository(impl: PetsRepositoryImpl): PetsRepository

    @Binds
    abstract fun binsSheltersRepository(impl: ShelterRepositoryImpl): ShelterRepository

    @Binds
    abstract fun bindSharedPreferencesRepository(impl: SharedPreferencesRepositoryImpl): SharedPreferencesRepository
}