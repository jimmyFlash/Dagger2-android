import com.example.android.dagger.storage.FakeStorage
import com.example.android.dagger.storage.Storage
import dagger.Binds
import dagger.Module

/**
 * We have to configure MyTestApplication to use Dagger. For integration tests,
 * a good practice is to create a TestApplicationComponent meant for testing.
 * Production and testing use a different component configuration.
 * What is the difference between our test configuration and our production configuration?
 * Instead of using a SharedPreferencesStorage in UserManager, we want to use a FakeStorage.
 * What's producing SharedPreferencesStorage? StorageModule.
 *
 * We have to swap the StorageModule for a different one that uses FakeStorage.
 * Since this is only required for instrumentation tests,
 * we create this new class in the androidTest folder
 */

// Overrides StorageModule in android tests
@Module
abstract class TestStorageModule {

    // Makes Dagger provide FakeStorage when a Storage type is requested
    @Binds
    abstract fun provideStorage(storage: FakeStorage): Storage
}