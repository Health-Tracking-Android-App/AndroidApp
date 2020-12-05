package project.st991488104.krutik.fragments.list.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.exercise_row_layout.view.*
import project.st991488104.krutik.data.models.ExerciseData
import project.st991488104.krutik.databinding.ExerciseRowLayoutBinding
import project.st991488104.krutik.fragments.PreferenceProvider
import project.st991488104.krutik.fragments.list.ExerciseListFragmentDirections

class ExerciseListAdapter : RecyclerView.Adapter<ExerciseListAdapter.MyViewHolder>() {

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
       // preferenceProvider = preferenceProvider()
    }
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val selectItem = dataList[position]
        holder.bind(selectItem)
        holder.itemView.row_background.setOnClickListener {
            val action = ExerciseListFragmentDirections.actionExerciseListFragmentToListFragment(dataList[position])
            holder.itemView.findNavController().navigate(action)

            Log.e("EXEID",dataList[position].exerciseId.toString())

            preferenceProvider = PreferenceProvider(holder.itemView.context)
            preferenceProvider.putString("ExerciseID",dataList[position].exerciseId.toString())
//
//            val editor: SharedPreferences.Editor = sharedPreferences.edit()
//            editor.putString("ExeID",dataList[position].exerciseId.toString())
//            editor.apply()
//            Log.e("EXEID1",dataList[position].exerciseId.toString())

        }
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
