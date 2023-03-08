package github.paulmburu.wastemanagement.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import github.paulmburu.wastemanagement.databinding.ItemRecyclingTipBinding
import github.paulmburu.wastemanagement.models.TipPresentation

class TipsAdapter : ListAdapter<TipPresentation, TipsAdapter.TipsViewHolder>(
    TipsComparator()
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TipsViewHolder {
        return TipsViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: TipsViewHolder, position: Int) {
        val tip = getItem(position)
        holder.bind(tip)
    }


    class TipsViewHolder(private val binding: ItemRecyclingTipBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(
            tip: TipPresentation,
        ) {
            with(binding) {
                tipPresentation = tip
                executePendingBindings()
            }
        }

        companion object {
            fun create(parent: ViewGroup): TipsViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemRecyclingTipBinding.inflate(layoutInflater, parent, false)

                return TipsViewHolder(binding)
            }
        }
    }

    class TipsComparator : DiffUtil.ItemCallback<TipPresentation>() {
        override fun areItemsTheSame(
            oldItem: TipPresentation,
            newItem: TipPresentation
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: TipPresentation,
            newItem: TipPresentation
        ): Boolean {
            return oldItem.id == newItem.id
        }
    }
}