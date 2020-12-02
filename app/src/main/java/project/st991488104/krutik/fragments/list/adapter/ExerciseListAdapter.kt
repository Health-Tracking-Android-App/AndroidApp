package project.st991488104.krutik.fragments.list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import project.st991488104.krutik.data.models.ExerciseData
import project.st991488104.krutik.databinding.ExerciseRowLayoutBinding

class ExerciseListAdapter : RecyclerView.Adapter<ExerciseListAdapter.MyViewHolder>() {

    var dataList = emptyList<ExerciseData>()

    class MyViewHolder(private val binding: ExerciseRowLayoutBinding) : RecyclerView.ViewHolder(binding.root){

        fun bind(exerciseData: ExerciseData){
            binding.exerciseData = exerciseData
            binding.executePendingBindings()
        }
        companion object{
            fun from(parent: ViewGroup): MyViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ExerciseRowLayoutBinding.inflate(layoutInflater, parent, false)
                return MyViewHolder(
                    binding
                )
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder.from(
            parent
        )
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val selectedItem = dataList[position]
        holder.bind(selectedItem)
    }

    fun setData(exerciseData: List<ExerciseData>){
        val exerciseDiffUtil =
            ExerciseDiffUtil(
                dataList,
                exerciseData
            )
        val exerciseDiffUtilResult = DiffUtil.calculateDiff(exerciseDiffUtil)
        this.dataList = exerciseData
        exerciseDiffUtilResult.dispatchUpdatesTo(this)
    }

}