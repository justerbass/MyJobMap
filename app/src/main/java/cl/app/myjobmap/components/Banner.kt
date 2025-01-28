package cl.app.myjobmap.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.viewinterop.AndroidView
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView

@Composable
fun BannerAd(width: Int, height: Int, adunitId: String){

    AndroidView(
        factory ={
                context ->
            val adView = AdView(context)
            adView.setAdSize(AdSize(width, height))
            adView.apply {
                adUnitId = adunitId
                loadAd(AdRequest.Builder().build())
            }
        }
    )
}