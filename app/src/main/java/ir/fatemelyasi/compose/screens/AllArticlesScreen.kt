package ir.fatemelyasi.compose.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ir.fatemelyasi.compose.data.MessageViewEntity
import kotlin.Unit

@Composable
fun AllArticlesScreen(
    navigateToSecondScreen: () -> Unit,
    popUpToFirstScreen: () -> Unit,
    messageViewEntities: List<MessageViewEntity>,
) {
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
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

        messageViewEntities.forEach { message ->
            ArticleItems(
                navigateToSecondScreen = {
                    navigateToSecondScreen
                },
                messageItem = message
            )

        }
    }

}