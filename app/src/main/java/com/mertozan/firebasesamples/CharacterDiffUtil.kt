package com.mertozan.firebasesamples

import androidx.recyclerview.widget.DiffUtil

class CharacterDiffUtil : DiffUtil.ItemCallback<Character>() {
    override fun areItemsTheSame(oldItem: Character, newItem: Character): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Character, newItem: Character): Boolean {
        return oldItem.name == newItem.name
    }
}
