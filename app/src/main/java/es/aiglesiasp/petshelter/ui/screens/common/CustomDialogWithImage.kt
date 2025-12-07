package es.aiglesiasp.petshelter.ui.screens.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import es.aiglesiasp.petshelter.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomDialogWithImage(
    onConfirm: () -> Unit,
) {
    androidx.compose.material3.BasicAlertDialog(
        onDismissRequest = onConfirm
    ) {
        Surface(
            shape = MaterialTheme.shapes.medium,
            tonalElevation = 6.dp
        ) {
            Column(
                modifier = Modifier
                    .padding(24.dp)
                    .width(IntrinsicSize.Min),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                // 🔹 Título
                Text(
                    text = "ATENCIÓN",
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.error
                )

                Spacer(modifier = Modifier.height(12.dp))

                // 🔹 Texto
                Text(
                    text = "Esta página esta en mantenimiento. Disculpen las molestias",
                    style = MaterialTheme.typography.bodyMedium,
                )

                Spacer(modifier = Modifier.height(16.dp))

                // 🔹 Imagen
                Image(
                    painter = painterResource(id = R.drawable.under_construccion),
                    contentDescription = null,
                    modifier = Modifier
                        .size(120.dp)
                )

                Spacer(modifier = Modifier.height(24.dp))

                // 🔹 Botones
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Button(
                        onClick = onConfirm,
                        modifier = Modifier.padding(end = 8.dp)
                    ) {
                        Text("Aceptar")
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun CustomDialogWithImage_Preview() {
    CustomDialogWithImage(
        onConfirm = {}
    )
}
