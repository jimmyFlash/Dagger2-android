package com.example.android.dagger.settings

import com.example.android.dagger.main.MainActivity
import com.example.android.dagger.user.LoggedUserScope
import dagger.Subcomponent

/**
 * We have to attach the lifetime of this Component to something that knows when the user logs
 * in and out. In our case, UserManager does it. UserManager handles registrations,
 * log in and log out attempts. It makes sense for the UserComponent instance to be there.
 */
// Scope annotation that the UserComponent uses
// Classes annotated with @LoggedUserScope will have a unique instance in this Component
@LoggedUserScope
// Definition of a Dagger subcomponent
@Subcomponent
interface UserComponent {

    // Factory to create instances of UserComponent
    @Subcomponent.Factory
    interface Factory {
        fun create(): UserComponent
    }

    // Classes that can be injected by this Component
    fun inject(activity: MainActivity)

    fun inject(activity: SettingsActivity)
}