package project.st991488104.krutik.fragments.list.adapter
//Krutik Parikh | 991488104

import androidx.recyclerview.widget.DiffUtil
import project.st991488104.krutik.data.models.ExerciseData

class ExerciseDiffUtil(
    private val oldList: List<ExerciseData>,
    private val newList: List<ExerciseData>
): DiffUtil.Callback() {

    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] === newList[newItemPosition]
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].exerciseId == newList[newItemPosition].exerciseId
                && oldList[oldItemPosition].name == newList[newItemPosition].name
    }
}