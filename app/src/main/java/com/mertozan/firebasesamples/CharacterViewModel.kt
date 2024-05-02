package com.mertozan.firebasesamples

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.toObjects
import com.google.firebase.ktx.Firebase

class CharacterViewModel : ViewModel() {

    private val _characterUiState = MutableLiveData<CharacterUiState>()
    val characterUiState: LiveData<CharacterUiState>
        get() = _characterUiState

    private val firestore = Firebase.firestore

    init {
        getCharactersFromFirestore()
    }

    fun addUserToFirestore(character: Character) {
        firestore.collection("characters")
            .document(character.name)
            .set(character)
            .addOnSuccessListener {
                getCharactersFromFirestore()
            }
            .addOnFailureListener {
                _characterUiState.value = CharacterUiState(
                    characters = emptyList(),
                    isLoading = false,
                    error = it.message.toString()
                )
            }
    }

    private fun getCharactersFromFirestore() {
        firestore.collection("characters")
            .get()
            .addOnSuccessListener { result ->
                val characters = result.toObjects<Character>()
                _characterUiState.value = CharacterUiState(
                    characters = characters,
                    isLoading = false,
                    error = ""
                )
            }
            .addOnFailureListener {
                _characterUiState.value = CharacterUiState(
                    characters = emptyList(),
                    isLoading = false,
                    error = it.message.toString()
                )
            }
    }

    fun deleteCharacterFromFirestore(character: Character) {
        firestore.collection("characters")
            .document(character.name)
            .delete()
            .addOnSuccessListener {
                getCharactersFromFirestore()
            }
            .addOnFailureListener {
                _characterUiState.value = CharacterUiState(
                    characters = emptyList(),
                    isLoading = false,
                    error = it.message.toString()
                )
            }
    }

    data class CharacterUiState(
        val characters: List<Character>,
        val isLoading: Boolean,
        val error: String
    )

}