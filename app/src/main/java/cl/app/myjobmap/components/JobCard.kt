package cl.app.myjobmap.components

import androidx.compose.material3.Card
import androidx.compose.runtime.Composable

@Composable
fun JobCard(
    jobTitle: String,
    companyName: String,
    recruiterName: String,
    date: String,
    answer: String,
    onClick : () -> Unit,
){
    Card (
        onClick = onClick,

    ){

    }
}