package com.example.marketplacesiswa

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.marketplacesiswa.ui.MainScreen
import com.example.marketplacesiswa.ui.theme.MarketplaceTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContent {

            MarketplaceTheme {

                MainScreen()
            }
        }
    }
}