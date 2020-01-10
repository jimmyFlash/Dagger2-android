package com.example.android.dagger.di

import com.example.android.dagger.registration.RegistrationActivity
import dagger.Component

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

@Component
interface AppComponent {

    //we're telling Dagger that RegistrationActivity requests injection and that it has to
    // provide what the Activity is injecting (i.e. RegistrationViewModel)
    fun inject(activity: RegistrationActivity)
}