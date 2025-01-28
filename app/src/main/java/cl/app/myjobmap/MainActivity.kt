package cl.app.myjobmap

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import cl.app.myjobmap.naviagation.NavManager
import cl.app.myjobmap.ui.theme.MyJobMapTheme
import cl.app.myjobmap.viewModel.PhrasesViewModel
import cl.app.myjobmap.viewModel.PostulationViewModel
import com.google.android.gms.ads.MobileAds
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel : PostulationViewModel by viewModels()
        val viewModelPhases : PhrasesViewModel by viewModels()
        enableEdgeToEdge()
        setContent {
            MyJobMapTheme{
                NavManager(viewModel, viewModelPhases)
            }
            MobileAds.initialize(this){}
        }
    }
}
