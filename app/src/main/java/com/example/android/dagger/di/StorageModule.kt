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
}