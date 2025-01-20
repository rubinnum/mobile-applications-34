package com.example.finalproject

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.finalproject.RecipeAction.LIKE
import com.example.finalproject.RecipeAction.SHARE
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class RecipesViewModel : ViewModel() {
    private val recipesRepository = RecipeRepository()
    private val _allRecipes = MutableStateFlow<List<RecipeItem>>(emptyList())
    private val _searchQuery = MutableStateFlow("")

    val recipesState: StateFlow<List<RecipeItem>> = combine(
        _allRecipes, _searchQuery
    ) { recipes, query ->
        when {
            query.length < 3 -> recipes
            else -> recipes.filter { recipe ->
                recipe.title.contains(query, ignoreCase = true)
                        || recipe.description.contains(query, ignoreCase = true)
            }
        }
    }.stateIn(
        viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList()
    )

    init {
        loadRecipes()
    }

    private fun loadRecipes() {
        viewModelScope.launch {
            _allRecipes.value = recipesRepository.getRecipes()
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