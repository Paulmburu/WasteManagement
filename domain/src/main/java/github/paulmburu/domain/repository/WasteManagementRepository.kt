package github.paulmburu.domain.repository

import github.paulmburu.common.Resource
import github.paulmburu.domain.models.WasteType
import kotlinx.coroutines.flow.Flow

interface WasteManagementRepository {
    fun fetchWasteTypes(): Flow<Resource<List<WasteType>>>

    suspend fun insertWasteTypes(wasteTypes: List<WasteType>)

    fun getWasteTypes(): Flow<Resource<List<WasteType>>>

    suspend fun insertProgressData(wasteTypes: WasteType)

    fun getProgressData(): Flow<Resource<List<WasteType>>>


}

