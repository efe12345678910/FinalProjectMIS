import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.finalprojectefe.Task
import kotlinx.coroutines.launch

class TaskViewModel(application: Application) : AndroidViewModel(application) {
    private val dao = TaskDatabase.getDatabase(application).taskDao()
    val allTasks: LiveData<List<Task>> = dao.getAll()

    fun insert(task: Task) = viewModelScope.launch {
        dao.insert(task)
    }

    fun delete(task: Task) = viewModelScope.launch {
        dao.delete(task)
    }

    fun update(task: Task) = viewModelScope.launch {
        dao.update(task)
    }
}
