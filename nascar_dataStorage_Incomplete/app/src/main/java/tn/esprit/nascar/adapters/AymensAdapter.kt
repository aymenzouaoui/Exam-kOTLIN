package tn.esprit.nascar.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import tn.esprit.nascar.databinding.SingleaAymenItemBinding

import tn.esprit.nascar.models.Aymen
import tn.esprit.nascar.models.Movie
import tn.esprit.nascar.utils.AppDatabase


class AymensAdapter(private val context: Context, private val newsList: MutableList<Aymen>) : RecyclerView.Adapter<AymensAdapter.NewsHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsHolder {
        val binding = SingleaAymenItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NewsHolder(binding)
    }

    override fun onBindViewHolder(holder: NewsHolder, position: Int) {
        holder.setData(newsList[position])
    }

    override fun getItemCount() = newsList.size

    inner class NewsHolder(val binding: SingleaAymenItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun setData(news: Aymen){
            with(news){
                binding.nomid.text = nom
                binding.poidid.text = poid.toString()


            }
            binding.btnRemove.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    deleteUser(position)
                }
            }
        }

    }
    fun updateUserListFromDatabase(context: Context) {
        val da = AppDatabase.getDatabase(context)
        val moviesFromDatabase = da.aymenDao().getAllUser()

        newsList.clear()
        newsList.addAll(moviesFromDatabase)

        notifyDataSetChanged()

    }
    fun deleteUser(position: Int) {
        val database = AppDatabase.getDatabase(context)
        val aymenToDelete = newsList[position]
        database.aymenDao().deletUser(aymenToDelete)
        newsList.removeAt(position)
        notifyItemRemoved(position)
    }

}