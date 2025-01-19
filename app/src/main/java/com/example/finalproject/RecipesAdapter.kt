package com.example.finalproject

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecipesAdapter(
    private val recipes: List<RecipeItem>,
    private val onItemClick: (position: Int) -> Unit,
    private val onActionClick: (position: Int, action: String) -> Unit
) :
    RecyclerView.Adapter<RecipesAdapter.RecipeViewHolder>() {
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
                val position = bindingAdapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    onItemClick(recipes[position].recipeId)
                }
            }

            shareIcon.setOnClickListener {
                val position = bindingAdapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    onActionClick(recipes[position].recipeId, "share")
                }
            }

            likeIcon.setOnClickListener {
                val position = bindingAdapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    onActionClick(recipes[position].recipeId, "like")
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recipe_item, parent, false)
        return RecipeViewHolder(view)
    }

    override fun getItemCount(): Int {
        return recipes.size
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        holder.bind(recipes[position])
    }
}