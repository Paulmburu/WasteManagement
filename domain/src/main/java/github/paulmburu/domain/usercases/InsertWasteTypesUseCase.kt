package github.paulmburu.domain.usercases

import github.paulmburu.domain.models.WasteType
import github.paulmburu.domain.repository.WasteManagementRepository
import github.paulmburu.domain.usercases.base.BaseUseCase

typealias InsertWasteTypesBaseUseCase = BaseUseCase<List<WasteType>, Unit>

class InsertWasteTypesUseCase(private val wasteManagementRepository: WasteManagementRepository) :
    InsertWasteTypesBaseUseCase {
    override suspend fun invoke(params: List<WasteType>) {
        wasteManagementRepository.insertWasteTypes(params)
    }
}