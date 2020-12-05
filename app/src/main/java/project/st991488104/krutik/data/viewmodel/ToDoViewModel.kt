package project.st991488104.krutik.data.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import project.st991488104.krutik.data.Database.HealthCareDatabase
import project.st991488104.krutik.data.models.ToDoData
import project.st991488104.krutik.data.repository.ToDoRepository

class ToDoViewModel(application: Application) : AndroidViewModel(application) {

    private val toDoDao = HealthCareDatabase.getDatabase(
        application
    ).toDoDao()
    private val repository: ToDoRepository

    init {
        repository = ToDoRepository(toDoDao)
    }

    fun getAllData(exerciseId: Int): LiveData<List<ToDoData>>{
        return repository.getAllData(exerciseId)
    }

    fun insertData(toDoData: ToDoData) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertData(toDoData)
        }
    }

    fun updateData(toDoData: ToDoData) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateData(toDoData)
        }
    }

    fun deleteItem(toDoData: ToDoData) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteItem(toDoData)
        }
    }

    fun deleteAll() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAll()
        }
    }

    fun searchDatabase(searchQuery: String, exerciseId: Int): LiveData<List<ToDoData>>{
        return repository.searchDatabase(searchQuery,exerciseId)
    }

    fun sortByHighPriority(exerciseId: Int): LiveData<List<ToDoData>>{
        return repository.sortByHighPriority(exerciseId)
    }

    fun sortByLowPriority(exerciseId: Int): LiveData<List<ToDoData>>{
        return repository.sortByHighPriority(exerciseId)
    }
}