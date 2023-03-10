package github.paulmburu.wastemanagement.ui.mainactivity

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth
import github.paulmburu.domain.usercases.*
import github.paulmburu.wastemanagement.ui.mainActivity.MainViewModel
import github.paulmburu.wastemanagement.util.ConnectivityProvider
import github.paulmburu.wastemanagement.util.MainCoroutineRule
import github.paulmburu.wastemanagement.util.getOrAwaitValue
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MainViewModelTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @RelaxedMockK
    private lateinit var fetchWasteTypesUseCase: FetchWasteTypesUseCase

    @RelaxedMockK
    private lateinit var getWasteTypesUseCase: GetWasteTypesUseCase

    @RelaxedMockK
    private lateinit var insertProgressUseCase: InsertProgressUseCase

    @RelaxedMockK
    private lateinit var getProgressUseCase: GetProgressUseCase

    @RelaxedMockK
    private lateinit var connectivityProvider: ConnectivityProvider

    private lateinit var viewModel : MainViewModel

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        viewModel = MainViewModel(
            fetchWasteTypesUseCase,
            getWasteTypesUseCase,
            insertProgressUseCase,
            getProgressUseCase,
            connectivityProvider
        )
    }

    @Test
    fun `when viewmodel is initialized, the current connectivity status is false`() {
        viewModel.loadNetworkData()
        val value = viewModel.mutableConnectivityStatus.getOrAwaitValue()
        Truth.assertThat(value).isEqualTo(false)
    }
}