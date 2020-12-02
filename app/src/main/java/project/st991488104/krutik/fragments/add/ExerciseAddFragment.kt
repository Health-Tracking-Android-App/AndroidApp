package project.st991488104.krutik.fragments.add

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_add.*
import kotlinx.android.synthetic.main.fragment_add.view.*
import project.st991488104.krutik.R
import project.st991488104.krutik.data.models.ExerciseData
import project.st991488104.krutik.data.viewmodel.ExerciseViewModel
import project.st991488104.krutik.fragments.SharedViewModel

class ExerciseAddFragment : Fragment() {

    private val mSharedViewModel: SharedViewModel by viewModels()
    private val mExeViewModel: ExerciseViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_exercise_add, container, false)

        setHasOptionsMenu(true)

        return view
    }
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.add_fragment_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.menu_add){
            insertDataToDb()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun insertDataToDb() {
        val mTitle = title_et.text.toString()

        val validation = mSharedViewModel.verifyExerciseDataFromUser(mTitle)
        if(validation){
            // Insert Data to Database
            val newData = ExerciseData(
                0,
                mTitle
            )
            mExeViewModel.insertData(newData)
            Toast.makeText(requireContext(), "Successfully added!", Toast.LENGTH_SHORT).show()
            // Navigate Back
            findNavController().navigate(R.id.action_exerciseAddFragment_to_exerciseListFragment)
        }else{
            Toast.makeText(requireContext(), "Please fill out all fields.", Toast.LENGTH_SHORT).show()
        }
    }
}