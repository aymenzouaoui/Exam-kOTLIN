package tn.esprit.nascar

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Patterns
import com.google.android.material.snackbar.Snackbar
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import tn.esprit.nascar.api.RetrofitImp
import tn.esprit.nascar.api.UserInterface
import tn.esprit.nascar.databinding.ActivityAddmovieBinding
import tn.esprit.nascar.models.Movie

import tn.esprit.nascar.models.User
import tn.esprit.nascar.utils.AppDatabase

class addmovie : AppCompatActivity() {

    private lateinit var binding: ActivityAddmovieBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddmovieBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //TODO 4 Create a val mSharedPreferences and initialise it
        val mSharedPreferences: SharedPreferences = getSharedPreferences("pref", MODE_PRIVATE)


        binding.tiname.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                return
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                validateEmail()
            }

            override fun afterTextChanged(s: Editable?) {
                return
            }
        })

        binding.tiDescreption.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                return
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                validatePassword()
            }

            override fun afterTextChanged(s: Editable?) {
                return
            }
        })



        binding.btnLogin.setOnClickListener {
            if (validateEmail() && validatePassword()) {
                //TODO 6 Check if the rememberMe is true then save all the data in the mSharedPreferences



                val database = AppDatabase.getDatabase(it.context)
                database.movieDao().insetMovie(
                    Movie(name = binding.tiname.text.toString(),
                        description = binding.tiDescreption.text.toString())



                )

                startActivity(Intent(this@addmovie, MainActivity::class.java))
                finish()



            } else {
                Snackbar.make(binding.contextView, getString(R.string.msg_error_inputs), Snackbar.LENGTH_SHORT)
                    .show()
            }
        }


    }

    private fun validateEmail(): Boolean {
        binding.tiEmailLayout.isErrorEnabled = false

        if (binding.tiname.text.toString().isEmpty()) {
            binding.tiEmailLayout.error = getString(R.string.msg_must_not_be_empty)
            binding.tiname.requestFocus()
            return false
        } else {
            binding.tiEmailLayout.isErrorEnabled = false
        }

        if (Patterns.EMAIL_ADDRESS.matcher(binding.tiname.text.toString()).matches()) {
            binding.tiEmailLayout.error = getString(R.string.msg_check_your_email)
            binding.tiname.requestFocus()
            return false
        } else {
            binding.tiEmailLayout.isErrorEnabled = false
        }

        return true
    }

    private fun validatePassword(): Boolean {
        binding.tiPasswordLayout.isErrorEnabled = false

        if (binding.tiDescreption.text.toString().isEmpty()) {
            binding.tiPasswordLayout.error = getString(R.string.msg_must_not_be_empty)
            binding.tiDescreption.requestFocus()
            return false
        } else {
            binding.tiPasswordLayout.isErrorEnabled = false
        }

        if (binding.tiDescreption.text.toString().length < 6) {
            binding.tiPasswordLayout.error = getString(R.string.msg_check_your_characters)
            binding.tiDescreption.requestFocus()
            return false
        } else {
            binding.tiPasswordLayout.isErrorEnabled = false
        }

        return true
    }

}