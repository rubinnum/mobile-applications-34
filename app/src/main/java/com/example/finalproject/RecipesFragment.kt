package com.example.finalproject

import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.SearchView
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
        setupRecyclerView(view)
        setupSearchView(view)
        observeRecipes()
    }

    private fun setupRecyclerView(view: View) {
        val recipesAdapter = RecipesAdapter(onItemClick = { recipeId ->
            viewModel.onRecipeClicked(recipeId)
        }, onActionClick = { recipeId, action ->
            viewModel.onActionClicked(recipeId, action)
        })

        view.findViewById<RecyclerView>(R.id.recipes_recycler_view).apply {
            layoutManager = LinearLayoutManager(context)
            adapter = recipesAdapter
        }
    }

    private fun setupSearchView(view: View) {
        val searchView = view.findViewById<SearchView>(R.id.search_recipes)

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                viewModel.updateSearchQuery(newText)
                return true
            }
        })
    }

    private fun observeRecipes() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.recipesState.collect { recipes ->
                (view?.findViewById<RecyclerView>(R.id.recipes_recycler_view)?.adapter as RecipesAdapter).submitList(
                    recipes
                )
            }
        }
    }
}