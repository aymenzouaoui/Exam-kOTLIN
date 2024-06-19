package tn.esprit.nascar

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import tn.esprit.nascar.adapters.AymensAdapter

import tn.esprit.nascar.databinding.FragmentAymenListBinding
import tn.esprit.nascar.databinding.FragmentMovieListBinding


class AymenListFragment : Fragment() {

    lateinit var binding: FragmentAymenListBinding
    private lateinit var userAdapter: AymensAdapter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAymenListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialize the UserAdapter
        userAdapter = AymensAdapter( requireContext(),mutableListOf())

        // Set up RecyclerView
        binding.rv.layoutManager = LinearLayoutManager(requireContext())
        binding.rv.adapter = userAdapter


        // Call the function to update the user list from the database
        userAdapter.updateUserListFromDatabase(requireContext())
    }


}