package com.android.tiktaktoe.ui.game

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.tiktaktoe.databinding.ItemBoxBinding
import com.android.tiktaktoe.domain.model.Player

class FieldAdapter(
    private var list: List<Player?> = emptyList(),
    val callback: (Int) -> Unit,
) : RecyclerView.Adapter<FieldAdapter.BoxViewHolder>() {

    init {
        setHasStableIds(true)
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    // TODO: need to implement using Diff Utils
    @SuppressLint("NotifyDataSetChanged")
    fun setList(list: List<Player?>){
        this.list = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BoxViewHolder {
        val binding = ItemBoxBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BoxViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BoxViewHolder, position: Int) {
        val player: Player? = list[position]
        holder.bind(player, position)
    }

    inner class BoxViewHolder(
        private val binding: ItemBoxBinding,
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(player: Player?, position: Int) {
            binding.text.text = player?.toString() ?: ""
            binding.text.setOnClickListener {
                callback.invoke(position)
            }
        }
    }

    override fun getItemCount(): Int = list.size
}