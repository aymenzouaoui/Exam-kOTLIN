package tn.esprit.nascar.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import tn.esprit.nascar.adapters.MovieAdapter
import tn.esprit.nascar.databinding.FragmentMovieListBinding



class UserListFragment : Fragment() {
    lateinit var binding: FragmentMovieListBinding
    private lateinit var userAdapter: MovieAdapter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMovieListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialize the UserAdapter
        userAdapter = MovieAdapter(requireContext(), mutableListOf())

        // Set up RecyclerView
        binding.rvBookmarks.layoutManager = LinearLayoutManager(requireContext())
        binding.rvBookmarks.adapter = userAdapter


        // Call the function to update the user list from the database
        userAdapter.updateUserListFromDatabase(requireContext())
    }


}