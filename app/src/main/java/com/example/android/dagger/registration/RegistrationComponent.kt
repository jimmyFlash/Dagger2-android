package com.example.android.dagger.registration

import com.example.android.dagger.di.ActivityScope
import com.example.android.dagger.registration.enterdetails.EnterDetailsFragment
import com.example.android.dagger.registration.termsandconditions.TermsAndConditionsFragment
import dagger.Subcomponent

/*
Problem :
different instances of RegistrationViewModel are being injected in RegistrationActivity,
EnterDetailsFragment, and TermsAndConditionsFragment.
However, that's not what we want. We want the same instance to be injected
for the Activity and Fragments.

What if we annotate RegistrationViewModel with @Singleton?
That would solve the problem for now but it will create problems in the future:

We wouldn't want an instance of RegistrationViewModel to be in memory all the time
after the flow has finished.
We want different instances of RegistrationViewModel for different registration flows.
If the user registers and unregisters, we don't want the data from the previous
registration to be present.
We want the registration Fragments to reuse the same ViewModel coming from the Activity,
but if the Activity changes, we want a different instance. We need to scope RegistrationViewModel
to RegistrationActivity, for that, we can create a new Component for the registration flow and
scope the ViewModel to that new registration Component. We use Dagger subcomponents
 */

/**
 * Subcomponents are components that inherit and extend the object graph of a parent component.
 * Thus, all objects provided in the parent component will be provided in the subcomponent too.
 * In this way, an object from a subcomponent can depend on an object provided by the parent component.
 */
// Scope annotation that the RegistrationComponent uses
// Classes annotated with @ActivityScope will have a unique instance in this Component
@ActivityScope
// Definition of a Dagger subcomponent
@Subcomponent
//Every time that an instance of RegistrationComponent provides an instance of RegistrationViewModel
// , it will be the same one.
interface RegistrationComponent {

    // Factory to create instances of RegistrationComponent
    @Subcomponent.Factory
    interface Factory {
        fun create(): RegistrationComponent
    }

    //Add the inject methods from AppComponent that are specific to Registration,
    // i.e. RegistrationActivity, EnterDetailsFragment, and TermsAndConditionsFragment.
    // Classes that can be injected by this Component

    // we're telling Dagger that RegistrationActivity requests injection and that it has to
    // provide what the Activity is injecting (i.e. RegistrationViewModel)
    fun inject(activity: RegistrationActivity)
    fun inject(fragment: EnterDetailsFragment)
    fun inject(fragment: TermsAndConditionsFragment)
}