package com.example.android.dagger.di

import android.content.Context
import com.example.android.dagger.registration.LoginComponent
import com.example.android.dagger.registration.RegistrationComponent
import com.example.android.dagger.splash.SplashActivity
import com.example.android.dagger.user.UserManager
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

/**
 * We want Dagger to create the graph of dependencies of our project,
 * manage them for us and be able to get dependencies from the graph.
 * To make Dagger do it, we need to create an interface and annotate it with @Component.
 * Dagger will create a Container as we would have done with manual dependency injection.
 *
 * @Component will make Dagger generate code with all the dependencies required to
 * satisfy the parameters of the methods it exposes. Inside that interface,
 * we can tell Dagger that RegistrationActivity requests injection.
 *
 * A @Component interface gives the information Dagger needs to generate the graph at compile-time.
 * The parameter of the interface methods define what classes request injection.
 */
// If we annotate a Component with @Singleton, all the classes also annotated with
// it will be scoped to the annotated Component
@Singleton
@Component(modules = [StorageModule::class, AppSubcomponents::class])
interface AppComponent {

    /*
        Context is provided by the Android system and therefore constructed outside of the graph.
        Since Context is already available at the time we'll be creating an instance of the graph,
        we can pass it in
     */
    // Factory to create instances of the AppComponent
    @Component.Factory
    interface Factory {
        // With @BindsInstance, the Context passed in will be available in the graph
        //@BindsInstance tells Dagger that it needs to add that instance in the graph
        // and whenever Context is required, provide that instance.
        fun create(@BindsInstance context: Context): AppComponent
    }

    /*
        There are two different ways to interact with the Dagger graph:

        Declaring a function that returns Unit and takes a class as a parameter allows field
         injection in that class (e.g. fun inject(activity: MainActivity)).
        Declaring a function that returns a type allows retrieving types from the graph
         (e.g. fun registrationComponent(): RegistrationComponent.Factory).
     */

    // Types that can be retrieved from the graph
    // Expose RegistrationComponent factory from the graph
    fun registrationComponent(): RegistrationComponent.Factory
    // For the LoginActivity to be able to access the LoginComponent factory,
    // we have to expose it in the AppComponent interface.
    fun loginComponent(): LoginComponent.Factory

    // Expose UserManager so that MainActivity and SettingsActivity
    // can access a particular instance of UserComponent
    fun userManager(): UserManager

    // Classes that can be injected by this Component
    fun inject(activity: SplashActivity)

}