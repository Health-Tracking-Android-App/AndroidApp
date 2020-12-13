package project.st991488104.krutik.data.viewmodel
//Krutik Parikh | 991488104

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import project.st991488104.krutik.data.Database.HealthCareDatabase
import project.st991488104.krutik.data.models.ExerciseData
import project.st991488104.krutik.data.repository.ExerciseRepository

class ExerciseViewModel(application: Application) : AndroidViewModel(application) {

    private val exerciseDao = HealthCareDatabase.getDatabase(
        application
    ).exerciseDao()
    private val repository: ExerciseRepository

    val getAllData: LiveData<List<ExerciseData>>

    init {
        repository = ExerciseRepository(exerciseDao)
        getAllData = repository.getAllData

    }

    fun insertData(exerciseData: ExerciseData) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertData(exerciseData)
        }
    }

    fun updateData(exerciseData: ExerciseData) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateData(exerciseData)
        }
    }

    fun deleteItem(exerciseData: ExerciseData) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteItem(exerciseData)
        }
    }

    fun deleteAll() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAll()
        }
    }
    fun getAllDataID(accountid: Int): LiveData<List<ExerciseData>>{
        return repository.getAllDataID(accountid)

    }

    fun getTask(exerciseId: Int): LiveData<Int>{
        return repository.getTask(exerciseId)
    }
}