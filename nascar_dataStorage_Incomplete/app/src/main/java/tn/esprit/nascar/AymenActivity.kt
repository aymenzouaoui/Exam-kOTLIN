package tn.esprit.nascar

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import tn.esprit.nascar.databinding.ActivityAymenBinding
import tn.esprit.nascar.models.Aymen
import tn.esprit.nascar.utils.AppDatabase

class AymenActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAymenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityAymenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button.setOnClickListener {
            val database = AppDatabase.getDatabase(it.context)
            database.aymenDao().insetUser(
                Aymen(
                    nom = binding.tinom.text.toString(),
                    poid = binding.tipoids.text.toString().toInt()
                )
            )
// Start AymenListActivity
            val intent = Intent(this, AymenListActivity::class.java)
            startActivity(intent)
            finish() // Close the current activity

            /*
            // Replace current fragment with AymenListFragment
            supportFragmentManager.commit {
                replace(R.id.fragment_container1, AymenListFragment())
                addToBackStack(null)
            }*/
        }

        // Optionally, load a default fragment if needed
        if (savedInstanceState == null) {
            supportFragmentManager.commit {
                replace(R.id.fragment_container, AymenListFragment())
            }
        }
    }
}
