package github.paulmburu.domain.usercases

import github.paulmburu.domain.models.WasteType
import github.paulmburu.domain.repository.WasteManagementRepository
import github.paulmburu.domain.usercases.base.BaseUseCase


typealias InsertProgressBaseUseCase = BaseUseCase<WasteType, Unit>

class InsertProgressUseCase(private val wasteManagementRepository: WasteManagementRepository) :
    InsertProgressBaseUseCase {
    override suspend fun invoke(params: WasteType) {
        wasteManagementRepository.insertProgressData(params)
    }
}