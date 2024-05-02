package com.mertozan.firebasesamples

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mertozan.firebasesamples.databinding.CardItemBinding

class CharacterAdapter(
    val onDeleteClick: (Character) -> Unit
) : ListAdapter<Character, CharacterAdapter.ViewHolder>(CharacterDiffUtil()) {

    inner class ViewHolder(private val binding: CardItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Character) {
            with(binding) {
                textViewName.text = item.name
                textViewSurname.text = item.surname
                textViewAge.text = item.age

                imageButtonDelete.setOnClickListener {
                    onDeleteClick(item)
                }

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = CardItemBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}