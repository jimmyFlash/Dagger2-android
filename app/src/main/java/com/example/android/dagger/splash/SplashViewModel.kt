package com.example.android.dagger.splash

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.android.dagger.user.UserManager
import javax.inject.Inject

class SplashViewModel @Inject constructor(private val userManager: UserManager){

    private val _userState = MutableLiveData<UserSate>()
    val userState: LiveData<UserSate>
        get() = _userState

    var welcomeText: String = "Welcome"
        get() = field
        set(msg : String){field = msg}

    fun checkUserSate() {
        if (!userManager.isUserLoggedIn()) {
            if (!userManager.isUserRegistered()) {
                welcomeText = "Hello new user, \nyou need to register 1st, plz wait"
                _userState.value = NotRegistered
            } else {
                welcomeText = "Welcome back, \nyou need to login, plz wait"
                _userState.value = NotLoggedIn
            }
        }else{
            welcomeText = "Welcome back, \nredirecting..., plz wait"
            _userState.value = LoggedIn
        }
    }

}