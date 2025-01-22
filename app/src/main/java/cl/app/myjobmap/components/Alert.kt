package cl.app.myjobmap.components


import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Composable
fun Alert (
    title: String,
    msg: String,
    confirmText: String,
    onDismissClick: () -> Unit,
    onConfirmClick: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismissClick,
        title = { Text(text = title, fontWeight = FontWeight.Bold,) },
        text = { Text(text = msg,
            fontSize = 20.sp
                )},
        confirmButton = {
            TextButton(onClick = onConfirmClick) {
                Text(text = confirmText,
                    fontSize = 25.sp
                    )
            }
        },

        containerColor = MaterialTheme.colorScheme.tertiary,
        textContentColor = MaterialTheme.colorScheme.onSecondary,
        titleContentColor = MaterialTheme.colorScheme.primary

    )
}

