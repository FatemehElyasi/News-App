package ir.fatemelyasi.news.view.components

import android.R.attr.text
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import ir.fatemelyasi.news.view.ui.theme.LocalCustomColors
import ir.fatemelyasi.news.view.utils.OfflineErrorAnimation
import ir.fatemelyasi.news.R
import java.nio.file.WatchEvent

@Composable
fun OfflineErrorComponent(
    isLoading: Boolean,
    onRetry: () -> Unit
) {
    val colors = LocalCustomColors.current

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        OfflineErrorAnimation()

        Text(
            modifier = Modifier.padding(top = 16.dp),
            text = stringResource(
                id = R.string
                    .offline_error_message
            ),
            style = TextStyle(
                color = colors.onPrimary,
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
            )
        )

        Button(
            modifier = Modifier.padding(top = 40.dp),
            onClick = onRetry,
            enabled = !isLoading
        ) {
            Text("Retry Again")
        }
    }
}
