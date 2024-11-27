package cl.app.myjobmap

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import cl.app.myjobmap.naviagation.NavManager
import cl.app.myjobmap.ui.theme.MyJobMapTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyJobMapTheme{
                NavManager()
            }
        }
    }
}
