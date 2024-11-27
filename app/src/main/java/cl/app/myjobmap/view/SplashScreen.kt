package cl.app.myjobmap.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import cl.app.myjobmap.R
import cl.app.myjobmap.naviagation.Screen
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navControler: NavController){

    LaunchedEffect(key1 = true){
        delay(3000)
        navControler.navigate(Screen.MainView.route)

    }

    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        Image(painter = painterResource(id = R.drawable.splash),
            contentDescription = R.string.Splash.toString(),
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
    }
}