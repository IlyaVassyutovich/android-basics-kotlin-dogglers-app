/*
* Copyright (C) 2021 The Android Open Source Project.
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*     http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/
package com.example.dogglers.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.dogglers.R
import com.example.dogglers.const.Layout
import com.example.dogglers.data.DataSource
import com.example.dogglers.model.Dog

/**
 * Adapter to inflate the appropriate list item layout and populate the view with information
 * from the appropriate data source
 */
class DogCardAdapter(
    private val context: Context,
    private val layout: Int
) : RecyclerView.Adapter<DogCardAdapter.DogCardViewHolder>() {
    private val _dogs = DataSource.dogs

    /**
     * Initialize view elements
     */
    class DogCardViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val imageDog = view.findViewById<ImageView>(R.id.image_dog)
        private val textDogName = view.findViewById<TextView>(R.id.text_dog_name)
        private val textDogAge = view.findViewById<TextView>(R.id.text_dog_age)
        private val textDogHobbies = view.findViewById<TextView>(R.id.text_dog_hobbies)

        fun setDogData(dog: Dog) {
            imageDog.setImageResource(dog.imageResourceId)
            textDogName.text = dog.name
            // TODO: Extract formatted string into resources somehow
            textDogAge.text = "Age: ${dog.age}"
            textDogHobbies.text = "Hobbies: ${dog.hobbies}"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DogCardViewHolder {
        val layoutId = when (layout) {
            Layout.GRID -> R.layout.grid_list_item
            Layout.VERTICAL -> R.layout.vertical_horizontal_list_item
            Layout.HORIZONTAL -> R.layout.vertical_horizontal_list_item
            else -> throw RuntimeException("Unknown layout selection ($layout).")
        }

        return parent.context
            .let { LayoutInflater.from(it) }
            .inflate(layoutId, parent, false)
            .let { DogCardViewHolder(it) }
    }

    override fun getItemCount(): Int {
        return _dogs.size
    }

    override fun onBindViewHolder(holder: DogCardViewHolder, position: Int) {
        val currentDog = _dogs[position]
        holder.setDogData(currentDog)
    }
}
