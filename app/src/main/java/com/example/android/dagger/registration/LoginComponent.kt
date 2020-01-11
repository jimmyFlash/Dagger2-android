package com.example.android.dagger.registration

import com.example.android.dagger.di.ActivityScope
import com.example.android.dagger.login.LoginActivity
import dagger.Subcomponent

// Scope annotation that the LoginComponent uses
// Classes annotated with @ActivityScope will have a unique instance in this Component
// We can annotate the LoginComponent with ActivityScope since the component
// will have the same lifetime as LoginActivity.
@ActivityScope
// Definition of a Dagger subcomponent
@Subcomponent
interface LoginComponent {

    // Factory to create instances of LoginComponent
    @Subcomponent.Factory
    interface Factory {
        fun create(): LoginComponent
    }

    // Classes that can be injected by this Component
    fun inject(activity: LoginActivity)
}