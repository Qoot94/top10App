package com.example.myapplication

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.ItemRowBinding


class RVAdapter(
    private val container: ArrayList<Top>
) :
    RecyclerView.Adapter<RVAdapter.CelebViewHolder>() {
    class CelebViewHolder(val binding: ItemRowBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CelebViewHolder {
        return CelebViewHolder(
            ItemRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: CelebViewHolder, position: Int) {
        val cards = container[position]
        holder.binding.apply {
            tvTitle.text = cards.title
        }
    }

    override fun getItemCount(): Int = container.size
}
