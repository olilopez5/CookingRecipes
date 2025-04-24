package com.example.cookingrecipes.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.cookingrecipes.R
import com.example.cookingrecipes.data.Recipe
import com.example.cookingrecipes.databinding.ItemRecipeBinding
import com.squareup.picasso.Picasso

class RecipeAdapter(var items: List<Recipe>, val onClick: (Int) -> Unit) : RecyclerView.Adapter<RecipeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val binding = ItemRecipeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RecipeViewHolder(binding)
    }


    override fun getItemCount(): Int =  items.size

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {

        val recipe = items[position]
        holder.render(recipe)

        holder.itemView.setOnClickListener {
            onClick(position)
        }

    }
    fun updateItems(items: List<Recipe>) {
        this.items = items
        notifyDataSetChanged()
    }
}

class RecipeViewHolder(private val binding: ItemRecipeBinding) : RecyclerView.ViewHolder(binding.root) {

    fun render(recipe: Recipe) {
        binding.textViewName.text = recipe.name
        Picasso.get().load(recipe.image).into(binding.imageView)
    }
}

