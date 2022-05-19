package com.example.koinQualifierIssue

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.koinQualifierIssue.ui.theme.KoinQualifierIssueTheme
import org.koin.android.ext.android.getKoin
import org.koin.androidx.scope.ScopeActivity
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class TestActivity : ScopeActivity() {

    private val vm: TestViewModel by viewModel { parametersOf("This is just the argument!") }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KoinQualifierIssueTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                   Column(verticalArrangement = Arrangement.spacedBy(20.dp)) {

                           Text(text = "Qualified Text: ${getKoin().get<String>(TEST_QUALIFIER)}")
                       Text("Argument Text: ${vm.argString}")
                       Text(text = "Resolved Incorrectly Text: ${vm.resolvedIncorrectlyText}", color = Color.Red)
                       Text(text = "Resolved Correctly Text: ${vm.resolvedCorrectlyText}", color = Color.Green)

                   }
                }
            }
        }

    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    KoinQualifierIssueTheme {
        Greeting("Android")
    }
}