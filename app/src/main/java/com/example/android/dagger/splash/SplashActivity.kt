package com.example.android.dagger.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.android.dagger.MyApplication
import com.example.android.dagger.R
import com.example.android.dagger.login.LoginActivity
import com.example.android.dagger.main.MainActivity
import com.example.android.dagger.registration.RegistrationActivity

import javax.inject.Inject

class SplashActivity : AppCompatActivity() {

    @Inject
    lateinit var splashViewModel: SplashViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        (application as MyApplication).appComponent.inject(this)

        splashViewModel.checkUserSate()

        splashViewModel.userState.observe(this, Observer<UserSate>{state->
            when(state){
                is NotLoggedIn -> {
                    gotoActivityaftrTime(LoginActivity::class.java)
                }
                is NotRegistered -> {
                    gotoActivityaftrTime(RegistrationActivity::class.java)
                }
                is LoggedIn -> {
                    gotoActivityaftrTime(MainActivity::class.java)
                }
            }
        })
    }

    fun <T> gotoActivityaftrTime(activty : Class<T>){
        findViewById<TextView>(R.id.welcomeTxt).text = splashViewModel.welcomeText
        var delay =  Handler().postDelayed( {
            startActivity(Intent(this, activty))
            finish()
        }, 5000)
    }

}

sealed class UserSate
object LoggedIn : UserSate()
object NotLoggedIn : UserSate()
object NotRegistered : UserSate()