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
    private val context: Context?,
    private val layout: Int
): RecyclerView.Adapter<DogCardAdapter.DogCardViewHolder>() {


    val data: List<Dog> = DataSource.dogs
    /**
     * Initialize view elements
     */
    class DogCardViewHolder(view: View?): RecyclerView.ViewHolder(view!!) {

        val image: ImageView = view!!.findViewById(R.id.image_item)
        val name: TextView = view!!.findViewById(R.id.name_item)
        val age: TextView = view!!.findViewById(R.id.age_item)
        val hobby: TextView = view!!.findViewById(R.id.hobby_item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DogCardViewHolder {

        val resource = when (layout){
            Layout.VERTICAL, Layout.HORIZONTAL-> R.layout.vertical_horizontal_list_item
            else -> R.layout.grid_list_item
        }

        val inflater = LayoutInflater.from(parent.context).inflate(resource, parent, false)
        return DogCardViewHolder(inflater)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: DogCardViewHolder, position: Int) {
        val dataItem = data[position]
        val resources = context?.resources
        holder.apply {
            this.image.setImageResource(dataItem.imageResourceId)
            this.name.text = dataItem.name
            this.age.text = dataItem.age
            this.hobby.text = resources?.getString(R.string.dog_hobbies, dataItem.hobbies)
        }
    }
}
