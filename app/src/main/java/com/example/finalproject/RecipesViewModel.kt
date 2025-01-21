package com.example.finalproject

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.finalproject.RecipeAction.LIKE
import com.example.finalproject.RecipeAction.SHARE
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.launch

class RecipesViewModel : ViewModel() {
    private val recipesRepository = RecipeRepository()
    private val _allRecipes = MutableStateFlow<List<RecipeItem>>(emptyList())
    private val _searchQuery = MutableStateFlow("")
    private val _uiState = MutableStateFlow<RecipesUiState>(RecipesUiState.Loading)
    val uiState: StateFlow<RecipesUiState> = _uiState.asStateFlow()


    init {
        viewModelScope.launch {
            delay(2000)
            _allRecipes.value = recipesRepository.getRecipes()

            combine(
                _allRecipes,
                _searchQuery.debounce(300)
            ) { recipes, query ->
                _uiState.value = RecipesUiState.Loading
                delay(2000)

                val filteredRecipes = when {
                    query.length < 3 -> recipes
                    else -> recipes.filter { recipe ->
                        recipe.title.contains(query, ignoreCase = true) ||
                                recipe.description.contains(query, ignoreCase = true)
                    }
                }
                _uiState.value = RecipesUiState.Success(filteredRecipes)
            }.collect {}
        }
    }

    fun updateSearchQuery(query: String) {
        _searchQuery.value = query
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