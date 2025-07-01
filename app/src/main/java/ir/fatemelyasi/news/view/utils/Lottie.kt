package ir.fatemelyasi.news.view.utils

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.airbnb.lottie.compose.*
import androidx.compose.ui.unit.dp
import ir.fatemelyasi.news.R

@Composable
fun OfflineErrorAnimation() {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.offline_animation1))
    val progress by animateLottieCompositionAsState(
        composition,
        iterations = LottieConstants.IterateForever
    )

    LottieAnimation(
        composition = composition,
        progress = { progress },
        modifier = Modifier
            .size(200.dp)
            .padding(16.dp)
    )
}
