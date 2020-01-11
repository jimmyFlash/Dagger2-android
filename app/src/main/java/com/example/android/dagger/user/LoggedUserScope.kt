package com.example.android.dagger.user

import javax.inject.Scope

/**
 * We want UserDataRepository to be scoped to UserComponent so that both MainActivity and
 * SettingsActivity can share the same instance of it.
 *
 * Since we've been using the scope annotation @ActivityScope to annotate components
 * that have the Activity managing its lifetime, we need a scope that can cover
 * multiple activities but not all the application, we don't have anything like that
 * yet so we need to create a new scope.
 *
 * Since this scope covers the lifetime when the user is logged in, we can call it LoggedUserScope.
 */
@Scope
@MustBeDocumented
@Retention(value = AnnotationRetention.RUNTIME)
annotation class LoggedUserScope