package com.example.finalproject

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.finalproject.RecipeAction.LIKE
import com.example.finalproject.RecipeAction.SHARE

class RecipesAdapter(
    private val onItemClick: (position: Int) -> Unit,
    private val onActionClick: (position: Int, action: RecipeAction) -> Unit
) : ListAdapter<RecipeItem, RecipesAdapter.RecipeViewHolder>(RecipeDiffCallback()) {
    inner class RecipeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val recipeImage: ImageView = itemView.findViewById(R.id.recipe_image)
        private val recipeTitle: TextView = itemView.findViewById(R.id.recipe_title)
        private val recipeDescription: TextView = itemView.findViewById(R.id.recipe_description)
        private val shareIcon: ImageView = itemView.findViewById(R.id.share_icon)
        private val likeIcon: ImageView = itemView.findViewById(R.id.like_icon)

        fun bind(recipe: RecipeItem) {
            recipeImage.setImageResource(recipe.imageResId)
            recipeTitle.text = recipe.title
            recipeDescription.text = recipe.description

            itemView.setOnClickListener {
                onItemClick(recipe.recipeId)
            }

            shareIcon.setOnClickListener {
                onActionClick(recipe.recipeId, SHARE)
            }

            likeIcon.setOnClickListener {
                onActionClick(recipe.recipeId, LIKE)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recipe_item, parent, false)
        return RecipeViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

private class RecipeDiffCallback : DiffUtil.ItemCallback<RecipeItem>() {
    override fun areItemsTheSame(oldItem: RecipeItem, newItem: RecipeItem): Boolean {
        return oldItem.recipeId == newItem.recipeId
    }

    override fun areContentsTheSame(oldItem: RecipeItem, newItem: RecipeItem): Boolean {
        return oldItem == newItem
    }
}