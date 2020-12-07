package project.st991488104.krutik.fragments.list

import android.os.Bundle
import android.view.*
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
import androidx.recyclerview.widget.RecyclerView


class ExerciseListFragment : Fragment() {

    private val mExerciseViewModel: ExerciseViewModel by viewModels()
    private val mSharedViewModel: SharedViewModel by viewModels()

    private var _binding: FragmentExerciseListBinding? = null
    private val binding get() = _binding!!

    private val adapter: ExerciseListAdapter by lazy { ExerciseListAdapter(mExerciseViewModel) }


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
        // Observe LiveData
        mExerciseViewModel.getAllData.observe(viewLifecycleOwner, Observer { data ->
            mSharedViewModel.checkIfExerciseDatabaseEmpty(data)
            adapter.setData(data)
        })

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
        deleteItem(recyclerView)
    }

    private fun deleteItem(recyclerView: RecyclerView) {

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}