package github.paulmburu.wastemanagement.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import github.paulmburu.wastemanagement.databinding.ItemWasteTypeBinding
import github.paulmburu.wastemanagement.models.WasteTypePresentation

class WasteTypeRecyclerAdapter(val clickListener: OnClickListener) :
    ListAdapter<WasteTypePresentation, WasteTypeRecyclerAdapter.WasteTypeViewHolder>(
        WasteTypeComparator()
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WasteTypeViewHolder {
        return WasteTypeViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: WasteTypeViewHolder, position: Int) {
        val wasteType = getItem(position)
        holder.itemView.setOnClickListener {
            clickListener.onClick(wasteType)
        }
        holder.bind(wasteType)
    }


    class WasteTypeViewHolder(private val binding: ItemWasteTypeBinding) :
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
            fun create(parent: ViewGroup): WasteTypeViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemWasteTypeBinding.inflate(layoutInflater, parent, false)

                return WasteTypeViewHolder(binding)
            }
        }
    }

    class WasteTypeComparator : DiffUtil.ItemCallback<WasteTypePresentation>() {
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

class OnClickListener(val clickListener: (wasteTypePresentation: WasteTypePresentation) -> Unit) {
    fun onClick(wasteTypePresentation: WasteTypePresentation) = clickListener(wasteTypePresentation)
}