package com.radiusagentassignment.di

import com.radiusagentassignment.api.ApiService
import com.radiusagentassignment.api.repoImpl.MainRepositoryImpl
import com.radiusagentassignment.repository.MainRepository
import com.radiusagentassignment.view.usecase.MainUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    @Singleton
    fun provideMainRepository(
        apiService: ApiService
    ): MainRepository {
        return MainRepositoryImpl(apiService)
    }

    @Provides
    @Singleton
    fun provideMainUseCase(
        mainRepository: MainRepository
    ): MainUseCase {
        return MainUseCase(mainRepository)
    }
}