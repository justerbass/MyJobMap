package cl.app.myjobmap.naviagation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import cl.app.myjobmap.view.MainView
import cl.app.myjobmap.view.SplashScreen


@Composable
fun NavManager(){
    val navControler = rememberNavController()

    NavHost(navController = navControler, startDestination = Screen.SplashScreen.route){

        composable(Screen.SplashScreen.route){
            SplashScreen(navControler)
        }

        composable(Screen.MainView.route){
            MainView(navControler)
        }
    }
}