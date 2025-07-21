package ir.fatemelyasi.news.view.screens.offlineScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rxjava3.subscribeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ir.fatemelyasi.news.R
import ir.fatemelyasi.news.view.screens.dashboardScreen.DashboardScreenViewModel
import ir.fatemelyasi.news.view.ui.theme.LocalCustomColors
import ir.fatemelyasi.news.view.utils.OfflineErrorAnimation
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun OfflineScreen(
    viewModel: DashboardScreenViewModel = koinViewModel()
) {
    val colors = LocalCustomColors.current

    val loading by viewModel.loading.subscribeAsState(initial = false)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        OfflineErrorAnimation()
        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = stringResource(id = R.string.offline_error_message),
            style = TextStyle(
                color = colors.onPrimary,
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
            )
        )

        Spacer(modifier = Modifier.height(40.dp))

        Button(
            onClick = {
                viewModel.fetchNewsItems()
            },
            enabled = !loading
        ) {
            Text("Retry Again")
        }
    }
}
