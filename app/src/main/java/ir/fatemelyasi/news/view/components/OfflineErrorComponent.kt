package ir.fatemelyasi.news.view.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ir.fatemelyasi.news.R
import ir.fatemelyasi.news.view.ui.theme.LocalCustomColors
import ir.fatemelyasi.news.view.utils.OfflineErrorAnimation

@Composable
fun OfflineErrorComponent(
    isLoading: Boolean,
    onRetry: () -> Unit
) {
    val colors = LocalCustomColors.current

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier
                .align(Alignment.Center)
                .padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OfflineErrorAnimation()

            Text(
                text = stringResource(id = R.string.offline_error_message),
                style = TextStyle(
                    color = colors.onPrimary,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                ),
                modifier = Modifier.padding(top = 16.dp)
            )
        }

        Button(
            onClick = onRetry,
            enabled = !isLoading,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 24.dp, end = 20.dp, start = 20.dp).fillMaxWidth()
        ) {
            Text(text = "Retry Again")
        }
    }
}
