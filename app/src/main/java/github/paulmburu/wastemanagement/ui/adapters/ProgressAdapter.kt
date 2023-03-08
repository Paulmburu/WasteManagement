package github.paulmburu.wastemanagement.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import github.paulmburu.wastemanagement.databinding.ItemProgressBinding
import github.paulmburu.wastemanagement.models.WasteTypePresentation


class ProgressAdapter :
    ListAdapter<WasteTypePresentation, ProgressAdapter.ProgressViewHolder>(
        ProgressComparator()
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProgressViewHolder {
        return ProgressViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: ProgressViewHolder, position: Int) {
        val wasteType = getItem(position)
        holder.bind(wasteType)
    }


    class ProgressViewHolder(private val binding: ItemProgressBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(
            wasteTypePresentation: WasteTypePresentation,
        ) {
            with(binding) {
                wasteType = wasteTypePresentation
                executePendingBindings()
            }
        }

        companion object {
            fun create(parent: ViewGroup): ProgressViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemProgressBinding.inflate(layoutInflater, parent, false)

                return ProgressViewHolder(binding)
            }
        }
    }

    class ProgressComparator : DiffUtil.ItemCallback<WasteTypePresentation>() {
        override fun areItemsTheSame(
            oldItem: WasteTypePresentation,
            newItem: WasteTypePresentation
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: WasteTypePresentation,
            newItem: WasteTypePresentation
        ): Boolean {
            return oldItem.id == newItem.id
        }
    }
}