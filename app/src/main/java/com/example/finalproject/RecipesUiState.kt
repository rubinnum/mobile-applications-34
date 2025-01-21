package com.example.finalproject

sealed class RecipesUiState {
    data object Loading : RecipesUiState()
    data class Success(val recipes: List<RecipeItem>) : RecipesUiState()
}