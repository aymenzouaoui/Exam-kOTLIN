package tn.esprit.nascar

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Patterns
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import tn.esprit.nascar.api.RetrofitImp
import tn.esprit.nascar.api.UserInterface
import tn.esprit.nascar.databinding.ActivityLoginBinding
import tn.esprit.nascar.models.User
import tn.esprit.nascar.utils.AppDatabase

const val PREF_FILE = "NASCAR_PREF"
const val EMAIL = "EMAIL"
const val PASSWORD = "PASSWORD"
const val IS_REMEMBERED = "IS_REMEMBERED"

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //TODO 4 Create a val mSharedPreferences and initialise it
        val mSharedPreferences: SharedPreferences = getSharedPreferences("pref", MODE_PRIVATE)


        binding.tiEmail.addTextChangedListener(object : TextWatcher {
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

        binding.tiPassword.addTextChangedListener(object : TextWatcher {
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

        //TODO 5 Check if the user is already authenticated and navigate to MainActivity
        if (mSharedPreferences.contains("email")) {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }

        binding.btnLogin.setOnClickListener {
            if (validateEmail() && validatePassword()) {
                //TODO 6 Check if the rememberMe is true then save all the data in the mSharedPreferences
                if (binding.btnRememberMe.isChecked) {

                    mSharedPreferences.edit().apply {
                        putString("email", binding.tiEmail.text.toString())
                        putString("password", binding.tiPassword.text.toString())
                    }.apply()



                }


                val database = AppDatabase.getDatabase(it.context)
              database.userDao().insetUser(
                  User(email = binding.tiEmail.text.toString(),
                      password = binding.tiPassword.text.toString())



              )

                startActivity(Intent(this@LoginActivity, MainActivity::class.java))
                finish()
                RetrofitImp.buildRetrofit().create(UserInterface::class.java).login(
                    User(email = binding.tiEmail.text.toString(),
                        password = binding.tiPassword.text.toString())
                ).enqueue(object : Callback<User>{
                    override fun onResponse(call: Call<User>, response: Response<User>) {
                        if(response.isSuccessful){
                            startActivity(Intent(this@LoginActivity, MainActivity::class.java))
                            finish()
                        }else{
                            Snackbar.make(binding.contextView, response.errorBody()?.string().toString(), Snackbar.LENGTH_SHORT)
                                .show()
                        }
                    }
                    override fun onFailure(call: Call<User>, t: Throwable) {
                        Snackbar.make(binding.contextView, t.message.toString(), Snackbar.LENGTH_SHORT)
                            .show()
                    }

                })



            } else {
                Snackbar.make(binding.contextView, getString(R.string.msg_error_inputs), Snackbar.LENGTH_SHORT)
                    .show()
            }
        }


        }

    private fun validateEmail(): Boolean {
        binding.tiEmailLayout.isErrorEnabled = false

        if (binding.tiEmail.text.toString().isEmpty()) {
            binding.tiEmailLayout.error = getString(R.string.msg_must_not_be_empty)
            binding.tiEmail.requestFocus()
            return false
        } else {
            binding.tiEmailLayout.isErrorEnabled = false
        }

        if (Patterns.EMAIL_ADDRESS.matcher(binding.tiEmail.text.toString()).matches()) {
            binding.tiEmailLayout.error = getString(R.string.msg_check_your_email)
            binding.tiEmail.requestFocus()
            return false
        } else {
            binding.tiEmailLayout.isErrorEnabled = false
        }

        return true
    }

    private fun validatePassword(): Boolean {
        binding.tiPasswordLayout.isErrorEnabled = false

        if (binding.tiPassword.text.toString().isEmpty()) {
            binding.tiPasswordLayout.error = getString(R.string.msg_must_not_be_empty)
            binding.tiPassword.requestFocus()
            return false
        } else {
            binding.tiPasswordLayout.isErrorEnabled = false
        }



        return true
    }

}