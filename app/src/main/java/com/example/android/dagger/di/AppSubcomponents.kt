package com.example.android.dagger.di

import com.example.android.dagger.registration.LoginComponent
import com.example.android.dagger.registration.RegistrationComponent
import com.example.android.dagger.settings.UserComponent
import dagger.Module

/**
 * we have to make AppComponent know that RegistrationComponent is its
 * subcomponent so that it can generate code for that. We need to create a Dagger module to do this
 */


// This module tells AppComponent which are its subcomponents
@Module(subcomponents = [RegistrationComponent::class, LoginComponent::class, UserComponent::class])
class AppSubcomponents {}