package com.example.finalproject

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.launch

class RecipesFragment : Fragment(R.layout.recipes_fragment) {
    private val viewModel by viewModels<RecipesViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recipesAdapter = RecipesAdapter(
            onItemClick = { recipeId ->
                viewModel.onRecipeClicked(recipeId)
            },
            onActionClick = { recipeId, action ->
                viewModel.onActionClicked(recipeId, action)
            }
        )

        view.findViewById<RecyclerView>(R.id.recipes_recycler_view).apply {
            layoutManager = LinearLayoutManager(context)
            adapter = recipesAdapter
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.recipesState.collect { recipes ->
                recipesAdapter.submitList(recipes)
            }
        }
    }
}