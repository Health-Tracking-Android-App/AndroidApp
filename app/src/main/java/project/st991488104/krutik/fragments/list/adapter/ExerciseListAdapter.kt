package project.st991488104.krutik.fragments.list.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.fragment.app.viewModels
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.exercise_row_layout.view.*
import project.st991488104.krutik.data.models.ExerciseData
import project.st991488104.krutik.data.viewmodel.ExerciseViewModel
import project.st991488104.krutik.databinding.ExerciseRowLayoutBinding
import project.st991488104.krutik.fragments.PreferenceProvider
import project.st991488104.krutik.fragments.list.ExerciseListFragmentDirections
import androidx.lifecycle.Observer

class ExerciseListAdapter(private val mExerciseViewModel: ExerciseViewModel) : RecyclerView.Adapter<ExerciseListAdapter.MyViewHolder>() {

    var dataList = emptyList<ExerciseData>()
    lateinit var preferenceProvider: PreferenceProvider


    // lateinit var context: Context
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

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val selectItem = dataList[position]
        holder.bind(selectItem)
        holder.itemView.row_background.setOnClickListener {
            val action = ExerciseListFragmentDirections.actionExerciseListFragmentToListFragment(dataList[position])
            holder.itemView.findNavController().navigate(action)

            preferenceProvider = PreferenceProvider(holder.itemView.context)
            preferenceProvider.putString("ExerciseID",dataList[position].exerciseId.toString())
        }
        holder.itemView.appCompatImageView.setOnClickListener {
            mExerciseViewModel.deleteItem(dataList[position])
        }
        mExerciseViewModel.getTask(dataList[position].exerciseId).observe(holder.itemView.context as LifecycleOwner, Observer {
            Log.e("data",it.toString())
            holder.itemView.getTask.text = "Task - "+it.toString()
        })
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
