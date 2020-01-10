/*
 * Copyright (C) 2019 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.dagger

import android.app.Application
import com.example.android.dagger.di.AppComponent
import com.example.android.dagger.di.DaggerAppComponent
import com.example.android.dagger.storage.SharedPreferencesStorage
import com.example.android.dagger.user.UserManager

open class MyApplication : Application() {

    /*
    create a Dagger graph that lives in your Application class because you want an instance of
    the graph to be in memory as long as the app is running. In this way, the graph is attached
    to the app's lifecycle. In our case, we also want to have the application Context available
    in the graph. As advantages, the graph is available to other Android framework classes
    (that can access with their Context) and it's also good for testing since you can use a
    custom Application class in tests
     */
    // Instance of the AppComponent that will be used by all the Activities in the project
    val appComponent: AppComponent by lazy {
        /*
            Dagger generated a class called DaggerAppComponent containing the implementation
            of the AppComponent graph when we built the project. Since we defined a Component
            Factory with the @Component.Factory annotation, we can call .factory()
            that is a static method of DaggerAppComponent. With that, we can now call the create
            method we defined inside the factory where we pass the Context
            (in this case applicationContext) in.
         */
        // Creates an instance of AppComponent using its Factory constructor
        // We pass the applicationContext that will be used as Context in the graph
        DaggerAppComponent.factory().create(applicationContext)
    }

    open val userManager by lazy {
        UserManager(SharedPreferencesStorage(this))
    }
}
