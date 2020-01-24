package com.example.android.dagger.di

import com.example.android.dagger.storage.SharedPreferencesStorage
import com.example.android.dagger.storage.Storage
import dagger.Binds
import dagger.Module


/**
 * Another way to tell Dagger how to provide instances of a type is with information
 * in Dagger Modules.
 * A Dagger Module is a class that is annotated with @Module.
 * There, you can define how to provide dependencies with the @Provides or @Binds annotations.
 *
 * The way we tell Dagger how to provide Storage is different because Storage is an interface.
 * We need to tell Dagger what implementation of Storage we want to use: SharedPreferencesStorage
 */

// Because of @Binds, StorageModule needs to be an abstract class
@Module
abstract class StorageModule {

//   Makes Dagger provide SharedPreferencesStorage when a Storage type is requested
//    @Binds must annotate an abstract function (since it's abstract,
//    it doesn't contain any code and the class needs to be abstract too).
//    The return type of the abstract function is the interface we want to provide an implementation
//    for (i.e. Storage). The implementation is specified by adding a unique parameter with the
//    interface implementation type (i.e. SharedPreferencesStorage).
    @Binds
    abstract fun provideStorage(storage: SharedPreferencesStorage): Storage


    /*
        Different implementation using @Provides:

        // @Provides tell Dagger how to create instances of the type that this function
        // returns (i.e. Storage).
        // Function parameters are the dependencies of this type (i.e. Context).

        //You can use the @Provides annotation in Dagger modules to tell Dagger how to provide:

        // Implementations of an interface (although @Binds is recommended because it
        // - generates less code and therefore it's more efficient).
        // - Classes that your project doesn't own (e.g. instances of Retrofit).

        @Provides
        fun provideStorage(context: Context): Storage {
            // Whenever Dagger needs to provide an instance of type Storage,
            // this code (the one inside the @Provides method) will be run.
            return SharedPreferencesStorage(context)
        }


     */
}