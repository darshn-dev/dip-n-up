package dev.darshn.dipnup

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import dev.darshn.dipnup.presentation.companylist.CompanyItem
import dev.darshn.dipnup.ui.theme.DipNUpTheme

class HomeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DipNUpTheme {
                // A surface container using the 'background' color from the theme
               // CompanyItem()
            }
        }
    }
}