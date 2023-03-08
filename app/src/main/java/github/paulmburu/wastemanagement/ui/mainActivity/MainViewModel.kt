package github.paulmburu.wastemanagement.ui.mainActivity

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import github.paulmburu.common.Resource
import github.paulmburu.domain.models.WasteType
import github.paulmburu.domain.usercases.FetchWasteTypesUseCase
import github.paulmburu.domain.usercases.GetProgressUseCase
import github.paulmburu.domain.usercases.GetWasteTypesUseCase
import github.paulmburu.domain.usercases.InsertProgressUseCase
import github.paulmburu.wastemanagement.mappers.toPresentation
import github.paulmburu.wastemanagement.models.WasteTypePresentation
import github.paulmburu.wastemanagement.util.ConnectivityProvider
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val fetchWasteTypesUseCase: FetchWasteTypesUseCase,
    private val getWasteTypesUseCase: GetWasteTypesUseCase,
    private val insertProgressUseCase: InsertProgressUseCase,
    private val getProgressUseCase: GetProgressUseCase,
    connectivityProvider: ConnectivityProvider,
) : ViewModel() {

    private val mutableFetchWasteTypeResult = MutableLiveData<FetchWasteTypesUiState>()
    val fetchWasteTypeResult: LiveData<FetchWasteTypesUiState>
        get() = mutableFetchWasteTypeResult

    private val mutableProgressResult = MutableLiveData<FetchProgressUiState>()
    val progressResult: LiveData<FetchProgressUiState>
        get() = mutableProgressResult

    val mutableConnectivityStatus = MutableLiveData<Boolean>()
    val connectivityStatus: LiveData<Boolean>
        get() = mutableConnectivityStatus

    init {
        mutableConnectivityStatus.value = connectivityProvider.isNetworkAvailable()
        getProgress()
    }

    fun loadNetworkData() = viewModelScope.launch {
        fetchWasteTypesFromInternet()
    }

    fun loadLocalData() {
        viewModelScope.launch {
            getWasteTypesFromDatabase()
        }

    }

    private suspend fun fetchWasteTypesFromInternet() {
        fetchWasteTypesUseCase().onStart {
            mutableFetchWasteTypeResult.value = FetchWasteTypesUiState.Loading
        }.collect { resource ->
            when (resource) {
                is Resource.Success -> {
                    if (resource.data == null) {
                        mutableFetchWasteTypeResult.value = FetchWasteTypesUiState.Empty
                    } else {
                        mutableFetchWasteTypeResult.value = FetchWasteTypesUiState.Success(
                            data = resource.data!!.map { it.toPresentation() }
                        )
                    }
                }
                is Resource.Error -> {
                    mutableFetchWasteTypeResult.value = FetchWasteTypesUiState.Failure(
                        message = resource.message.toString()
                    )
                }
            }
        }
    }


    private suspend fun getWasteTypesFromDatabase() {
        getWasteTypesUseCase(Unit).onStart {
            mutableFetchWasteTypeResult.value = FetchWasteTypesUiState.Loading
        }.collect { resource ->
            when (resource) {
                is Resource.Success -> {
                    if (resource.data == null) {
                        mutableFetchWasteTypeResult.value = FetchWasteTypesUiState.Empty
                    } else {
                        mutableFetchWasteTypeResult.value = FetchWasteTypesUiState.Success(
                            data = resource.data!!.map { it.toPresentation() }
                        )
                    }
                }

                is Resource.Error -> {
                    mutableFetchWasteTypeResult.value = FetchWasteTypesUiState.Failure(
                        message = resource.message.toString()
                    )
                }
            }

        }
    }

    fun insertProgress(wasteType: WasteType) {
        viewModelScope.launch {
            insertProgressUseCase(wasteType)
            getProgress()
        }
    }

    fun getProgress() {
        viewModelScope.launch {
            getProgressUseCase(Unit).onStart {
                mutableProgressResult.value = FetchProgressUiState.Loading
            }.collect { resource ->
                when (resource) {
                    is Resource.Success -> {
                        if (resource.data == null) {
                            mutableProgressResult.value = FetchProgressUiState.Empty
                        } else {
                            mutableProgressResult.value = FetchProgressUiState.Success(
                                data = resource.data!!.map { it.toPresentation() }
                            )
                        }
                    }

                    is Resource.Error -> {
                        mutableProgressResult.value = FetchProgressUiState.Failure(
                            message = resource.message.toString()
                        )
                    }
                }

            }
        }
    }

    sealed class FetchWasteTypesUiState {
        object Loading : FetchWasteTypesUiState()
        object Empty : FetchWasteTypesUiState()
        data class Failure(val message: String) : FetchWasteTypesUiState()
        data class Success(val data: List<WasteTypePresentation>) :
            FetchWasteTypesUiState()

    }

    sealed class FetchProgressUiState {
        object Loading : FetchProgressUiState()
        object Empty : FetchProgressUiState()
        data class Failure(val message: String) : FetchProgressUiState()
        data class Success(val data: List<WasteTypePresentation>) :
            FetchProgressUiState()

    }
}