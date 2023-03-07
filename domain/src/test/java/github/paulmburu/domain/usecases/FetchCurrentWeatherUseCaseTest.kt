package github.paulmburu.domain.usecases

import com.google.common.truth.Truth
import github.paulmburu.common.Resource
import github.paulmburu.domain.fakes.Data
import github.paulmburu.domain.fakes.WasteManagementRepositoryFake
import github.paulmburu.domain.usercases.FetchWasteTypesBaseUseCase
import github.paulmburu.domain.usercases.FetchWasteTypesUseCase
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class FetchWasteTypesUseCaseTest {
    private lateinit var fetchWasteTypesBaseUseCase: FetchWasteTypesBaseUseCase

    @Before
    fun setUp() {
        val wasteManagementRepository = WasteManagementRepositoryFake()
        fetchWasteTypesBaseUseCase = FetchWasteTypesUseCase(
            wasteManagementRepository
        )
    }

    @Test
    fun `When FetchWasteTypes is called, WasteTypes should be returned`() =
        runBlocking {
            fetchWasteTypesBaseUseCase(
            ).collect { resource ->
                when(resource){
                    is Resource.Success -> {
                        Truth.assertThat(resource.data).isEqualTo(Data.WasteTypesData.response)
                    }
                }
            }
        }
}