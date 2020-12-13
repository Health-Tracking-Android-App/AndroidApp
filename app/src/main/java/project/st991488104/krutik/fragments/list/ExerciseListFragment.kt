package project.st991488104.krutik.fragments.list
//Krutik Parikh | 991488104

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import jp.wasabeef.recyclerview.animators.SlideInUpAnimator
import project.st991488104.krutik.data.viewmodel.ExerciseViewModel
import project.st991488104.krutik.databinding.FragmentExerciseListBinding
import project.st991488104.krutik.fragments.SharedViewModel
import project.st991488104.krutik.fragments.list.adapter.ExerciseListAdapter
import project.st991488104.krutik.utils.hideKeyboard
import androidx.lifecycle.Observer
import androidx.navigation.ui.NavigationUI
import androidx.recyclerview.widget.RecyclerView
import project.st991488104.krutik.R
import project.st991488104.krutik.fragments.PreferenceProvider


class ExerciseListFragment : Fragment() {

    private val mExerciseViewModel: ExerciseViewModel by viewModels()
    private val mSharedViewModel: SharedViewModel by viewModels()

    private var _binding: FragmentExerciseListBinding? = null
    private val binding get() = _binding!!

    private val adapter: ExerciseListAdapter by lazy { ExerciseListAdapter(mExerciseViewModel) }

    lateinit var preferenceProvider: PreferenceProvider

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Data binding
        _binding = FragmentExerciseListBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.mSharedViewModel = mSharedViewModel

        // Setup RecyclerView
        setupRecyclerview()

        //getting data from shared preference - ExerciseListAdapter
        preferenceProvider = PreferenceProvider(requireActivity().applicationContext)
        var  accID = preferenceProvider.getString("LoginID","")!!.toInt()



        // Observe LiveData
        mExerciseViewModel.getAllDataID(accID).observe(viewLifecycleOwner, Observer { data ->
            mSharedViewModel.checkIfExerciseDatabaseEmpty(data)
            adapter.setData(data)
        })

        setHasOptionsMenu(true)

        hideKeyboard(requireActivity())

        return binding.root
    }

    private fun setupRecyclerview() {
        val recyclerView = binding.exRecyclerView
        recyclerView.adapter = adapter
        recyclerView.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        recyclerView.itemAnimator = SlideInUpAnimator().apply {
            addDuration = 300
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_exercise_fragment_menu, menu)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_delete_all -> confirmRemoval()
             }
        return super.onOptionsItemSelected(item)
    }

    private fun confirmRemoval() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes") { _, _ ->
            mExerciseViewModel.deleteAll()
            Toast.makeText(
                requireContext(),
                "Successfully Removed Everything!",
                Toast.LENGTH_SHORT
            ).show()
        }
        builder.setNegativeButton("No") { _, _ -> }
        builder.setTitle("Delete everything?")
        builder.setMessage("Are you sure you want to remove everything?")
        builder.create().show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}