package github.paulmburu.wastemanagement.ui.mainActivity

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import github.paulmburu.wastemanagement.databinding.ActivityMainBinding
import github.paulmburu.wastemanagement.models.WasteTypePresentation
import github.paulmburu.wastemanagement.ui.adapters.WasteTypeRecyclerAdapter

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val viewModel: MainViewModel by viewModels()

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setObservers()
    }

    private fun setObservers() {
        viewModel.connectivityStatus.observe(this) {
            when (it) {
                true -> {
                    viewModel.loadNetworkData()
                }
                false -> {
                    viewModel.loadLocalData()
                }
            }
        }

        viewModel.fetchWasteTypeResult.observe(this) { uiState ->
            when (uiState) {
                is MainViewModel.FetchWasteTypesUiState.Loading -> {
                    displayLoadingState()
                }
                is MainViewModel.FetchWasteTypesUiState.Failure -> {
                    displayFailedState()
                }

                is MainViewModel.FetchWasteTypesUiState.Success -> {
                    displayWasteTypesState(uiState.data)
                }

                is MainViewModel.FetchWasteTypesUiState.Empty -> {
                    displayEmptyState()
                }
            }
        }
    }

    private fun displayLoadingState() {
        with(binding) {
            progressBar.isVisible = true
            wasteTypeRecyclerView.isVisible = false
            noDataImageView.isVisible = false
        }
    }

    private fun displayWasteTypesState(wasteTypes: List<WasteTypePresentation>) {
        val wasteManagementAdapter = WasteTypeRecyclerAdapter()
        binding.wasteTypeRecyclerView.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = wasteManagementAdapter
        }
        wasteManagementAdapter.submitList(wasteTypes)

        with(binding) {
            progressBar.isVisible = false
            wasteTypeRecyclerView.isVisible = true
            noDataImageView.isVisible = false
        }
    }


    private fun displayEmptyState() {
        with(binding) {
            noDataImageView.isVisible = true
            progressBar.isVisible = false
            wasteTypeRecyclerView.isVisible = false
        }
    }

    private fun displayFailedState() {
        with(binding) {
            noDataImageView.isVisible = true
            progressBar.isVisible = false
            wasteTypeRecyclerView.isVisible = false
        }
    }

}
