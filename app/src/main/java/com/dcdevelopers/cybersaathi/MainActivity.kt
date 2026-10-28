package com.dcdevelopers.cybersaathi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.dcdevelopers.cybersaathi.data.repository.ProgressRepository
import com.dcdevelopers.cybersaathi.navigation.CyberSaathiAppNavigation

class MainActivity : ComponentActivity() {

    private lateinit var progressRepository: ProgressRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        progressRepository = ProgressRepository(applicationContext)

        setContent {
            CyberSaathiAppNavigation(progressRepository = progressRepository)
        }
    }
}
