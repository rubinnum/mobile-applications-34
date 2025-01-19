package com.example.finalproject

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class RecipesFragment : Fragment(R.layout.recipes_fragment) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recipesRepository = RecipeRepository()
        val recyclerView = view.findViewById<RecyclerView>(R.id.recipes_recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = RecipesAdapter(recipesRepository.getRecipes())
    }
}