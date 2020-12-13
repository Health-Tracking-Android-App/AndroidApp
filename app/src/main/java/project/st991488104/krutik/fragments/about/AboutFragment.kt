package project.st991488104.krutik.fragments.about
//991435185 Nathaniel Kawal
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import project.st991488104.krutik.R
import project.st991488104.krutik.databinding.FragmentAboutBinding

class AboutFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? { // Inflate the layout for this fragment
        val binding : FragmentAboutBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_about, container, false)

        // Add OnClick Handler for Try Again button
        binding.okButton.setOnClickListener{view: View->
            view.findNavController().navigate(R.id.action_aboutFragment_to_exerciseListFragment)}

        return binding.root





    }


}