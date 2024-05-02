package com.mertozan.firebasesamples

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.mertozan.firebasesamples.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: CharacterAdapter
    private val viewModel by viewModels<CharacterViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = CharacterAdapter {
            viewModel.deleteCharacterFromFirestore(it)
        }

        viewModel.characterUiState.observe(this) {
            adapter.submitList(it.characters)
            binding.recyclerView.adapter = adapter
        }

        with(binding) {
            buttonSend.setOnClickListener {
                viewModel.addUserToFirestore(
                    Character(
                        name = textInputEditTextName.text.toString(),
                        surname = textInputEditTextSurname.text.toString(),
                        age = textInputEditTextAge.text.toString()
                    )
                )
            }
        }
    }
}