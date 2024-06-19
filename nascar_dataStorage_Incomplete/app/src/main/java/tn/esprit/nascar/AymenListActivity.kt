package tn.esprit.nascar

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.commit
import tn.esprit.nascar.databinding.ActivityAymenListBinding

class AymenListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAymenListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAymenListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Load AymenListFragment into this activity
        supportFragmentManager.commit {
            replace(R.id.fragment_container1, AymenListFragment())
        }
    }
}