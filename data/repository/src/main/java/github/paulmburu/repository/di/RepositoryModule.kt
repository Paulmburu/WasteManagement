package github.paulmburu.repository.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import github.paulmburu.domain.repository.WasteManagementRepository
import github.paulmburu.local.dao.ProgressDao
import github.paulmburu.local.dao.WasteManagementDao
import github.paulmburu.network.api.WasteManagementApi
import github.paulmburu.repository.repository.WasteManagementRepositoryImpl

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    fun providesWasteManagementRepository(
        wasteManagementApi: WasteManagementApi,
        wasteManagementDao: WasteManagementDao,
        progressDao: ProgressDao
    ): WasteManagementRepository {
        return WasteManagementRepositoryImpl(wasteManagementApi, wasteManagementDao, progressDao)
    }
}