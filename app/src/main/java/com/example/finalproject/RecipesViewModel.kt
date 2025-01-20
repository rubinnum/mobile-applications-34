package com.example.finalproject

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.finalproject.RecipeAction.LIKE
import com.example.finalproject.RecipeAction.SHARE
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class RecipesViewModel : ViewModel() {
    private val recipesRepository = RecipeRepository()
    private val _recipesState = MutableStateFlow<List<RecipeItem>>(emptyList())
    val recipesState: StateFlow<List<RecipeItem>> = _recipesState

    init {
        loadRecipes()
    }

    private fun loadRecipes() {
        viewModelScope.launch {
            _recipesState.value = recipesRepository.getRecipes()
        }
    }

    fun onRecipeClicked(recipeId: Int) {
        Log.d("RecipesViewModel", "Recipe with id $recipeId was clicked")
    }

    fun onActionClicked(recipeId: Int, action: RecipeAction) {
        when (action) {
            LIKE -> Log.d("RecipesViewModel", "Like clicked for recipe $recipeId")
            SHARE -> Log.d("RecipesViewModel", "Share clicked for recipe $recipeId")
        }
    }
}