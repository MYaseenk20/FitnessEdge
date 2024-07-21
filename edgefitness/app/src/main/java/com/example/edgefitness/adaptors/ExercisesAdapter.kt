package com.example.edgefitness.adaptors

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.edgefitness.R
import com.example.edgefitness.models.response.exerciesResult

class ExercisesAdapter(private var exercises: List<exerciesResult>) :
    RecyclerView.Adapter<ExercisesAdapter.ExerciseViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExerciseViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_exercise, parent, false)
        return ExerciseViewHolder(view)
    }

    override fun onBindViewHolder(holder: ExerciseViewHolder, position: Int) {
        val exercise = exercises[position]
        holder.bind(exercise)
    }

    override fun getItemCount(): Int {
        return exercises.size
    }

    inner class ExerciseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val nameTextView: TextView = itemView.findViewById(R.id.nameTextView)
        private val createdAtTextView: TextView = itemView.findViewById(R.id.createdAtTextView)
        private val updatedAtTextView: TextView = itemView.findViewById(R.id.updatedAtTextView)

        fun bind(exercise: exerciesResult) {
            nameTextView.text = exercise.name
            createdAtTextView.text = "Created At: ${exercise.created_at}"
            updatedAtTextView.text = "Updated At: ${exercise.updated_at}"
            // Handle other fields as needed
        }
    }

    fun updateData(newList: List<exerciesResult>) {
        exercises = newList
        notifyDataSetChanged()
    }
}
