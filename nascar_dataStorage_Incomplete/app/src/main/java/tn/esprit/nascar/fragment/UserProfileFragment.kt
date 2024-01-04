package tn.esprit.nascar.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import tn.esprit.nascar.databinding.FragmentUserProfileBinding
import tn.esprit.nascar.models.User

class UserProfileFragment : Fragment() {

    private lateinit var binding: FragmentUserProfileBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentUserProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Retrieve user details from arguments
        val userId = arguments?.getInt("userId")
        val login = arguments?.getString("login")
        val password = arguments?.getString("password")

        // Display user details in the UI
        displayUserDetails(User(userId ?: 0, login ?: "", password ?: ""))
    }

    private fun displayUserDetails(user: User) {
        // Update UI with user details
        binding.textViewUserId.text = user.id.toString()
        binding.textViewLogin.text = user.email
        binding.textViewPassword.text = user.password
    }
}
