package cl.app.myjobmap.naviagation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import cl.app.myjobmap.view.Answer
import cl.app.myjobmap.view.MainView
import cl.app.myjobmap.view.NewJob
import cl.app.myjobmap.view.SplashScreen
import cl.app.myjobmap.viewModel.PostulationViewModel


@Composable
fun NavManager(viewModel: PostulationViewModel){
    val navControler = rememberNavController()

    NavHost(navController = navControler, startDestination = Screen.SplashScreen.route){

        composable(Screen.SplashScreen.route){
            SplashScreen(navControler)
        }

        composable(Screen.MainView.route){
            MainView(navControler, viewModel)
        }

        composable(Screen.NewJob.route){
            NewJob(navControler, viewModel)
        }

        composable(Screen.Answer.route){
            Answer(navControler, viewModel)
        }
    }
}