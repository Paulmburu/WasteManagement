package github.paulmburu.domain.fakes

import github.paulmburu.common.Resource
import github.paulmburu.domain.models.WasteType
import github.paulmburu.domain.repository.WasteManagementRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf

class WasteManagementRepositoryFake : WasteManagementRepository {
    private val wasteTypesDatabase = LinkedHashMap<String, WasteType>()
    private val progressDatabase = LinkedHashMap<String, WasteType>()

    override fun fetchWasteTypes(
    ): Flow<Resource<List<WasteType>>> = flow {
        emit(Resource.Success(Data.WasteTypesData.response))
    }


    override suspend fun insertWasteTypes(wasteTypes: List<WasteType>) {
        wasteTypes.forEach {
            wasteTypesDatabase[it.id] = it
        }
    }

    override fun getWasteTypes(): Flow<Resource<List<WasteType>>> {
        return flowOf(Resource.Success(listOf()))
    }

    override suspend fun insertProgressData(wasteType: WasteType) {
        progressDatabase[wasteType.id] = wasteType
    }

    override fun getProgressData(): Flow<Resource<List<WasteType>>> {
        return flowOf(Resource.Success(listOf()))
    }

}
