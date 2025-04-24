package ir.fatemelyasi.compose.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlin.Unit

@Composable
fun ArticleActivity(
    navigateToSecondScreen: () -> Unit,
    popUpToFirstScreen: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
            .background(MaterialTheme.colorScheme.background)
    ) {
        Icon(
            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
            contentDescription = "back",
            modifier = Modifier
                .padding(
                    top = 40.dp
                )
                .clickable {
                    popUpToFirstScreen()
                })
        Spacer(modifier = Modifier.height(10.dp))

        messages.forEach { message ->
            ArticleItems(
                navigateToSecondScreen = {
                    navigateToSecondScreen
                }, text = message
            )

        }
    }

}