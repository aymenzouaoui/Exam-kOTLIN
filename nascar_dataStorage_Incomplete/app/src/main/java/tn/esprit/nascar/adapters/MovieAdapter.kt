package tn.esprit.nascar.adapters

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import tn.esprit.nascar.MainActivity
import tn.esprit.nascar.R
import tn.esprit.nascar.databinding.SingleMovieBinding


import tn.esprit.nascar.fragment.UserProfileFragment
import tn.esprit.nascar.models.Movie
import tn.esprit.nascar.models.User
import tn.esprit.nascar.utils.AppDatabase

class MovieAdapter(private val context: Context, private val movieList: MutableList<Movie>) :
    RecyclerView.Adapter<MovieAdapter.UsersHolder>() {
    // Add a click listener interface
    interface OnUserItemClickListener {
        fun onUserItemClick(userId: String)
    }

    private var itemClickListener: OnUserItemClickListener? = null

    fun setOnUserItemClickListener(listener: OnUserItemClickListener) {
        this.itemClickListener = listener
    }


    override fun onBindViewHolder(holder: UsersHolder, position: Int) {
        with(holder) {
            with(movieList[position]) {
                // Access the views directly in UsersHolder without binding
                textView5.text = name
                textView6.text = description
               // Set a click listener for the item
                itemView.setOnClickListener {

                    val movie = movieList[position]
                    val da = AppDatabase.getDatabase(context)



                    // Notify the listener with the user ID when an item is clicked
                    itemClickListener?.onUserItemClick(movie.id.toString())
                    val userProfileFragment = UserProfileFragment().apply {
                        arguments = Bundle().apply {
                            putInt("userId", movie.id)
                            putString("name", movie.name.toString())
                            putString("description", movie.description.toString())

                        }
                    }

                    // Replace the current fragment with UserProfileFragment
                    // Use the fragment manager associated with the current context
                    (context as? MainActivity)?.supportFragmentManager?.beginTransaction()
                        ?.replace(R.id.fragment_container, userProfileFragment)
                        ?.addToBackStack(null)
                        ?.commit()

                    Log.d("idddddddd",movie.id.toString())
                }

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsersHolder {
        val binding = SingleMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UsersHolder(binding)
    }

    override fun getItemCount() = movieList.size

    // Function to update the userList with data from the database
    fun updateUserListFromDatabase(context: Context) {
        val da = AppDatabase.getDatabase(context)
        val moviesFromDatabase = da.movieDao().getAllmovie()

        movieList.clear()
        movieList.addAll(moviesFromDatabase)

        notifyDataSetChanged()
    }
/*
    // Remove the context parameter from deleteUser
    fun deleteUser(position: Int) {
        // Use the context property directly
        val user = movieList[position]
        val da = AppDatabase.getDatabase(context)
        da.userDao().deletUser(user) // Assuming you have a deleteUser method in your UserDao
        // Remove the deleted user from the list
        userList.removeAt(position)

        // Notify the adapter that the data set has changed
        notifyItemRemoved(position)
    }*/


    inner class UsersHolder(binding: SingleMovieBinding) : RecyclerView.ViewHolder(binding.root) {
        // Access the views directly in UsersHolder without binding
        val textView5 = binding.newsTitle
        val textView6 = binding.newsDescription

    }
}
