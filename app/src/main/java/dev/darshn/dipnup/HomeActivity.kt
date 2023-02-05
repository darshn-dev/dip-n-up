package dev.darshn.dipnup

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Surface
import dagger.hilt.android.AndroidEntryPoint
import dev.darshn.dipnup.presentation.companylist.CompanyListScreen
import dev.darshn.dipnup.ui.theme.DipNUpTheme

@AndroidEntryPoint
class HomeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DipNUpTheme {
                Surface() {
                    CompanyListScreen()
                }
            }
        }
    }
}