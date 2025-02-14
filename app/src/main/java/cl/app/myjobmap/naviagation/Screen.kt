package cl.app.myjobmap.naviagation

sealed class Screen ( val route: String){
    object SplashScreen: Screen("splash_screen")
    object MainView: Screen("main_view")
    object NewJob: Screen("new_job")
    object Answer: Screen("answer")
    object UpdateAnswer: Screen("update_answer")
    object Interview: Screen("interview")

}