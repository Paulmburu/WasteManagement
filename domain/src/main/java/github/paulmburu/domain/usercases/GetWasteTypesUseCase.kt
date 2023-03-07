package github.paulmburu.domain.usercases

import github.paulmburu.common.Resource
import github.paulmburu.domain.models.WasteType
import github.paulmburu.domain.repository.WasteManagementRepository
import github.paulmburu.domain.usercases.base.BaseUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow

typealias GetWasteTypesBaseUseCase = BaseUseCase<Unit, Flow<Resource<List<WasteType>>>>

class GetWasteTypesUseCase constructor(private val wasteManagementRepository: WasteManagementRepository) :
    GetWasteTypesBaseUseCase {
    override suspend fun invoke(params: Unit): Flow<Resource<List<WasteType>>> = flow {
        val result = wasteManagementRepository.getWasteTypes()
        result.collect { resource ->
            when (resource) {
                is Resource.Success -> {
                    val data = resource.data!!
                    emit(
                        Resource.Success(data)
                    )
                }
                is Resource.Error -> {
                    emit(Resource.Error(message = resource.message))
                }
            }
        }
    }
}