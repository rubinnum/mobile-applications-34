package com.example.finalproject

class RecipesRepository {
    fun getRecipes(): List<RecipeItem> {
        return listOf(
            RecipeItem(1, R.drawable.karaage, "Black Karaage with Curry Bento", "This Japanese modern izakaya dish features crispy black ka.."),
            RecipeItem(2, R.drawable.udon, "Seafood Udon", "A Japanese-style dish that’s quick and easy to prepare.."),
            RecipeItem(3, R.drawable.ramen, "Tonkotsu Ramen", "Is a Japanese noodle soup dish that originated in Fukuoka, Fu.."),
            RecipeItem(4, R.drawable.takoyaki, "Takoyaki", "Is a Japanese snack that originated in Osaka, Japan. It is a ball-shaped cake made fr.."),
            RecipeItem(5, R.drawable.tempura, "Tempura", "Is a popular Japanese dish that consists of seafood, vegetable.."),
            RecipeItem(6, R.drawable.shrimp, "Yakitori Shrimp", "Is a Japanese dish that consists of skewered and grilled chicken. However, it ca.."),
        )
    }
}