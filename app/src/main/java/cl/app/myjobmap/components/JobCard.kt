package cl.app.myjobmap.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun JobCard(
    jobTitle: String,
    companyName: String,
    recruiterName: String,
    date: String,
    answer: String,
    onClick : () -> Unit,
    backColor : Color
){
    Card (
        onClick = onClick,
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = backColor,
            contentColor = MaterialTheme.colorScheme.primary
        )
    ){
        Row (
            modifier = Modifier
                .fillMaxSize()
                .padding(5.dp),
            verticalAlignment = Alignment.CenterVertically
        ){
            Box(modifier = Modifier.padding(10.dp)
            ){
                Icon(imageVector = Icons.Default.Edit, contentDescription = null,
                    tint = MaterialTheme.colorScheme.onSecondary)
            }

            Column (
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                Text(
                    text = jobTitle
                )
                Separation()
                Text(
                    text = date
                )
                Separation()
                Text(
                    text = companyName
                )
                Separation()
                Text(
                    text = "$ $recruiterName"
                )
                Separation()
                Text(
                    text = answer
                )
                Separation()
            }
        }

    }
}