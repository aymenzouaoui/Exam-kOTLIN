package tn.esprit.nascar.fragment
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import tn.esprit.nascar.adapters.BookmarksAdapter
import tn.esprit.nascar.databinding.FragmentProfileBinding
import tn.esprit.nascar.utils.AppDatabase

class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(layoutInflater)

        // TODO: 13 Get all events from the database and create the BookmarksAdapter and assign it to the recyclerView rvBookmarks
        // TODO: 4 Create a val mSharedPreferences and initialize it
        val mSharedPreferences: SharedPreferences = requireActivity().getSharedPreferences("pref", AppCompatActivity.MODE_PRIVATE)

        val eventsList = AppDatabase.getDatabase(requireContext()).eventDao().getAllEvent()
        val bookmarksAdapter = BookmarksAdapter(eventsList)
        binding.rvBookmarks.adapter = bookmarksAdapter
        binding.rvBookmarks.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.txtEmail.text = mSharedPreferences.getString("email", "")

        return binding.root
    }
}
