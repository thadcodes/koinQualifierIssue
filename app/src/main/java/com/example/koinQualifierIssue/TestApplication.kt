package com.example.koinQualifierIssue

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.core.parameter.parametersOf
import org.koin.core.qualifier.named
import org.koin.dsl.module

class TestApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@TestApplication)
            modules(module {

                single(TEST_QUALIFIER) {
                    "This is what should print"
                }
                scope<TestActivity> {
                    scoped {
                        FakeUseCase(
                            incorrectResolution = get(TEST_QUALIFIER),
                            correctResolution = get(TEST_QUALIFIER) { parametersOf() },
                        )
                    }
                    viewModel { params ->
                        TestViewModel(argString = params.get(), get())
                    }
                }

            })
        }
    }
}


val TEST_QUALIFIER = named("TESTING")