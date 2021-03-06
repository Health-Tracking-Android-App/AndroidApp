package project.st991488104.krutik.fragments.login
//991435185 Nathaniel Kawal
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import kotlinx.android.synthetic.main.fragment_login.*
import project.st991488104.krutik.MainActivity
import project.st991488104.krutik.R
import project.st991488104.krutik.data.viewmodel.AccountViewModel
import project.st991488104.krutik.databinding.FragmentLoginBinding
import project.st991488104.krutik.fragments.PreferenceProvider

class LoginFragment : Fragment() {

    private val mAccViewModel: AccountViewModel by viewModels()

    lateinit var preferenceProvider: PreferenceProvider

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val binding: FragmentLoginBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_login, container, false
        )



        binding.btnLogin.setOnClickListener { view: View ->

            val email = editTextEmail.text.toString()
            val password = editTextPassword.text.toString()


            mAccViewModel.loadEmail(email, password).observe(viewLifecycleOwner, Observer { accountnum ->
                if (accountnum == 1) {

                    mAccViewModel.checkEmail(email).observe(viewLifecycleOwner, Observer {

                        preferenceProvider = PreferenceProvider(requireActivity().applicationContext)
                        preferenceProvider.putString("LoginID",it.toString())

                        view.findNavController()
                            .navigate(R.id.action_loginFragment_to_exerciseListFragment)


                        Toast.makeText(requireContext(), "Welcome", Toast.LENGTH_SHORT).show()

                    })


                } else if (email.isEmpty() || password.isEmpty()) {
                    Toast.makeText(requireContext(), "Fields are invalid", Toast.LENGTH_SHORT)
                        .show()
                } else {
                    Toast.makeText(
                        requireContext(),
                        "This account does not exist",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            })

        }


        binding.btnRegister.setOnClickListener { view: View ->  //Navigate to Register
            view.findNavController()
                .navigate(R.id.action_loginFragment_to_registerFragment)

        }

        (activity as MainActivity).setDrawer_Locked();

        return binding.root



    }

    override fun onDestroyView() {
        super.onDestroyView()
        (activity as MainActivity).setDrawer_Unlocked();
    }

}
