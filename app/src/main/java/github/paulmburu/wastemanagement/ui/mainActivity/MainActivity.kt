package github.paulmburu.wastemanagement.ui.mainActivity

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.button.MaterialButton
import dagger.hilt.android.AndroidEntryPoint
import github.paulmburu.wastemanagement.R
import github.paulmburu.wastemanagement.databinding.ActivityMainBinding
import github.paulmburu.wastemanagement.mappers.toDomain
import github.paulmburu.wastemanagement.models.WasteTypePresentation
import github.paulmburu.wastemanagement.ui.adapters.OnClickListener
import github.paulmburu.wastemanagement.ui.adapters.ProgressAdapter
import github.paulmburu.wastemanagement.ui.adapters.TipsAdapter
import github.paulmburu.wastemanagement.ui.adapters.WasteTypeRecyclerAdapter
import github.paulmburu.wastemanagement.util.RecyclingTips

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val viewModel: MainViewModel by viewModels()

    private lateinit var binding: ActivityMainBinding
    private lateinit var bottomSheetLayout: View
    private lateinit var sheetBehavior: BottomSheetBehavior<View>
    private lateinit var buttonConfirm: MaterialButton
    private lateinit var progressAdapter: ProgressAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setObservers()
        setupRecyclingTips()
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

        viewModel.progressResult.observe(this) { uiState ->
            when (uiState) {
                is MainViewModel.FetchProgressUiState.Loading -> {
                }
                is MainViewModel.FetchProgressUiState.Failure -> {
                }

                is MainViewModel.FetchProgressUiState.Success -> {
                    displayProgressData(uiState.data)
                }

                is MainViewModel.FetchProgressUiState.Empty -> {
//                    displayEmptyState()
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

    private fun displayProgressData(data: List<WasteTypePresentation>){
        if(data.isNullOrEmpty()){
            binding.recycledProgressView.visibility = View.GONE
        }else {
            binding.recycledProgressView.visibility = View.VISIBLE
            val progressAdapter = ProgressAdapter()
            binding.progressRecyclerView.apply {
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                adapter = progressAdapter
            }

            binding.countTextView.text = "${data.size} / 100 Items"
            binding.recycledProgressBar.progress = data.size
            progressAdapter.submitList(data)
        }
    }


    private fun displayWasteTypesState(wasteTypes: List<WasteTypePresentation>) {

        val wasteManagementAdapter = WasteTypeRecyclerAdapter(
            OnClickListener { wasteTypePresentation ->
                if (sheetBehavior.state != BottomSheetBehavior.STATE_EXPANDED) {
                    sheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED)
                } else {
                    sheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED)
                }

                buttonConfirm.setOnClickListener {
                    viewModel.insertProgress(wasteTypePresentation.toDomain())
                    if (sheetBehavior.state == BottomSheetBehavior.STATE_EXPANDED) sheetBehavior.state =
                        BottomSheetBehavior.STATE_COLLAPSED
                }
            }
        )
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

    private fun setupRecyclingTips() {
        bottomSheetLayout = binding.bottomSheet
        sheetBehavior = BottomSheetBehavior.from(bottomSheetLayout)

        val tipsRecyclerView = bottomSheetLayout.findViewById<RecyclerView>(R.id.tipsRecyclerView)
        buttonConfirm = bottomSheetLayout.findViewById<MaterialButton>(R.id.buttonConfirm)


        val tipsAdapter = TipsAdapter()
        tipsRecyclerView.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = tipsAdapter
        }
        tipsAdapter.submitList(RecyclingTips.tips)
    }

}
