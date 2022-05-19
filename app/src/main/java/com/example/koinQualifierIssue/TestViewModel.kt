package com.example.koinQualifierIssue

import androidx.lifecycle.ViewModel

class TestViewModel(val argString: String, private val fakeUseCase: FakeUseCase): ViewModel() {
    val resolvedIncorrectlyText: String get() = fakeUseCase.incorrectResolution
    val resolvedCorrectlyText: String get() = fakeUseCase.correctResolution
}
