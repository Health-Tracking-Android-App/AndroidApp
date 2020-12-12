package project.st991488104.krutik.fragments.register

import android.accounts.Account
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import kotlinx.android.synthetic.main.fragment_register.*
import project.st991488104.krutik.R
import project.st991488104.krutik.data.models.AccountData
import project.st991488104.krutik.data.viewmodel.AccountViewModel
import project.st991488104.krutik.databinding.FragmentRegisterBinding
import project.st991488104.krutik.fragments.PreferenceProvider


class RegisterFragment : Fragment() {

    lateinit var preferenceProvider: PreferenceProvider
    private val mAccViewModel: AccountViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val binding: FragmentRegisterBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_register, container, false
        )

        binding.btnLogin.setOnClickListener { view: View ->
            view.findNavController()
                .navigate(R.id.action_registerFragment_to_loginFragment)
        }

        binding.btnSignup.setOnClickListener { view: View ->


            var email = editEmail.text.toString();

            var password = editPassword.text.toString();

            var confirmpass = editConfirmpass.text.toString();


            mAccViewModel.existEmail(email).observe(viewLifecycleOwner, Observer {
                Log.e("Data", it.toString())
                if (it == 1) {
                    Toast.makeText(requireContext(), "Account exists", Toast.LENGTH_SHORT).show()
                } else if (password != confirmpass) {
                    Toast.makeText(
                        requireContext(),
                        "Password do not match",
                        Toast.LENGTH_SHORT
                    ).show()
                } else if (email.isEmpty() || password.isEmpty() || confirmpass.isEmpty() || !email.contains(
                        '@'
                    )
                ) {

                    Toast.makeText(requireContext(), "Invalid input", Toast.LENGTH_LONG).show()

                } else {

                    // Initialize a new account
                    val account = AccountData(
                        0,
                        email = email,
                        password = password


                    )

                    mAccViewModel.insertData(account)


                    mAccViewModel.loadEmail(email, password)
                        .observe(viewLifecycleOwner, Observer { accountnum ->
                            Log.e("Data", accountnum.toString())
                            if (accountnum == 1) {

                                mAccViewModel.checkEmail(email)
                                    .observe(viewLifecycleOwner, Observer {

                                        preferenceProvider =
                                            PreferenceProvider(requireActivity().applicationContext)
                                        preferenceProvider.putString("LoginID", it.toString())


                                        Toast.makeText(
                                            requireContext(),
                                            "Welcome",
                                            Toast.LENGTH_SHORT
                                        )
                                            .show()

                                        view.findNavController()
                                            .navigate(R.id.action_registerFragment_to_exerciseListFragment)

                                    })
                            }
                        })


                }

            }
            )
        }
        return binding.root
    }
}