package project.st991488104.krutik.fragments.list.adapter
//Krutik Parikh | 991488104

import androidx.recyclerview.widget.DiffUtil
import project.st991488104.krutik.data.models.ToDoData

class ToDoDiffUtil(
    private val oldList: List<ToDoData>,
    private val newList: List<ToDoData>
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
        return oldList[oldItemPosition].taskId == newList[newItemPosition].taskId
                && oldList[oldItemPosition].title == newList[newItemPosition].title
                && oldList[oldItemPosition].description == newList[newItemPosition].description
                && oldList[oldItemPosition].priority == newList[newItemPosition].priority
                && oldList[oldItemPosition].exerciseId == newList[newItemPosition].exerciseId
    }
}