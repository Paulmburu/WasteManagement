package github.paulmburu.wastemanagement.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import github.paulmburu.domain.repository.WasteManagementRepository
import github.paulmburu.domain.usercases.*

@Module
@InstallIn(ActivityRetainedComponent::class)
object UseCaseModule {
    @Provides
    fun providesFetchWasteTypesUseCase(wasteManagementRepository: WasteManagementRepository): FetchWasteTypesUseCase {
        return FetchWasteTypesUseCase(wasteManagementRepository)
    }

    @Provides
    fun providesGetWasteTypesUseCase(wasteManagementRepository: WasteManagementRepository): GetWasteTypesUseCase {
        return GetWasteTypesUseCase(wasteManagementRepository)
    }

    @Provides
    fun providesInsertWasteTypesUseCaseUseCase(wasteManagementRepository: WasteManagementRepository): InsertWasteTypesUseCase {
        return InsertWasteTypesUseCase(wasteManagementRepository)
    }

    @Provides
    fun providesInsertProgressUseCase(wasteManagementRepository: WasteManagementRepository): InsertProgressUseCase {
        return InsertProgressUseCase(wasteManagementRepository)
    }

    @Provides
    fun providesGetProgressUseCase(wasteManagementRepository: WasteManagementRepository): GetProgressUseCase {
        return GetProgressUseCase(wasteManagementRepository)
    }

}