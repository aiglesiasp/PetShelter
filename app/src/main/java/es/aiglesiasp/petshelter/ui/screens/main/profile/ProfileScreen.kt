@file:OptIn(ExperimentalMaterial3Api::class)

package es.aiglesiasp.petshelter.ui.screens.main.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavController
import es.aiglesiasp.petshelter.R
import es.aiglesiasp.petshelter.framework.database.LoginLocal
import es.aiglesiasp.petshelter.ui.ScreenAppTheme
import es.aiglesiasp.petshelter.ui.common.PSScaffold
import es.aiglesiasp.petshelter.ui.navigation.Login
import es.aiglesiasp.petshelter.ui.screens.common.CustomDialogWithImage

@Composable
fun ProfileScreen(
    navController: NavController,
    vm: ProfileViewModel = hiltViewModel(),
) {
    val uiState = vm.uiState.collectAsState()

    ScreenAppTheme {
        PSScaffold(
            navController = navController,
            modifier = Modifier.fillMaxSize(),
            topBarTitle = stringResource(id = R.string.app_name),
        ) { paddingValues ->
            ProfileBody(
                modifier = Modifier.padding(paddingValues),
                user = uiState.value.user,
                onEditProfileClick = { vm.showWarningDialog() },
                onLogoutClick = {
                    vm.onLogoutClick()
                },
                onFaqClick = { vm.showWarningDialog() },
                onContactClick = { vm.showWarningDialog() },
            )

            if (uiState.value.showLogoutDialog) {
                SimpleConfirmDialog(
                    title = "AVISO",
                    message = "¿Estas seguro que quieres cerrar sesión?",
                    onConfirm = {
                        vm.onConfirmLogout()
                        navController.navigate(Login)
                    },
                    onDismiss = {
                        vm.dismissAlertLogout()
                    }
                )
            }

            if (uiState.value.showWarningDialog) {
                CustomDialogWithImage {
                    vm.dismissWarningDialog()
                }
            }
        }
    }
}

@Composable
private fun ProfileBody(
    modifier: Modifier = Modifier,
    user: LoginLocal?,
    onEditProfileClick: () -> Unit,
    onLogoutClick: () -> Unit,
    onFaqClick: () -> Unit,
    onContactClick: () -> Unit,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier = Modifier.height(24.dp))

        // Avatar
        user?.let { AvatarImageProfile(it.nombre) }

        Spacer(modifier = Modifier.height(16.dp))

        // Nombre
        user?.let { NameTextProfile(it.nombre) }

        // Email
        user?.let { EmailTextProfile(it.email) }

        Spacer(modifier = Modifier.height(32.dp))

        // ----- Sección Cuenta -----
        SectionTitle(text = "Cuenta")

        SettingsRow(
            text = "Editar perfil",
            onClick = onEditProfileClick
        )

        SettingsRow(
            text = "Cerrar sesión",
            onClick = onLogoutClick
        )

        Spacer(modifier = Modifier.height(24.dp))

        // ----- Sección Ayuda -----
        SectionTitle(text = "Ayuda")

        SettingsRow(
            text = "Preguntas freqüentes",
            onClick = onFaqClick
        )

        SettingsRow(
            text = "Contacta",
            onClick = onContactClick
        )
    }
}

@Composable
private fun EmailTextProfile(userEmail: String) {
    Text(
        text = userEmail,
        style = MaterialTheme.typography.bodyMedium.copy(
            color = MaterialTheme.colorScheme.primary
        ),
        maxLines = 1,
        overflow = TextOverflow.Ellipsis
    )
}

@Composable
private fun NameTextProfile(userName: String) {
    Text(
        text = userName,
        style = MaterialTheme.typography.headlineSmall.copy(
            fontWeight = FontWeight.Bold
        )
    )
}

@Composable
private fun AvatarImageProfile(userName: String) {
    Box(
        modifier = Modifier
            .size(120.dp)
            .clip(CircleShape)
            .background(MaterialTheme.colorScheme.surfaceVariant),
        contentAlignment = Alignment.Center
    ) {
        // Aquí luego puedes poner tu Image(...)
        Text(
            text = userName.firstOrNull()?.toString() ?: "",
            style = MaterialTheme.typography.headlineLarge.copy(
                fontWeight = FontWeight.Bold
            )
        )
    }
}

@Composable
private fun SectionTitle(text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography.titleMedium.copy(
            fontWeight = FontWeight.Bold
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    )
}

@Composable
private fun SettingsRow(
    text: String,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.weight(1f)
        )
        Icon(
            imageVector = Icons.Default.KeyboardArrowRight,
            contentDescription = null
        )
    }
}

@Composable
fun SimpleConfirmDialog(
    title: String,
    message: String,
    onConfirm: () -> Unit,
    onDismiss: () -> Unit,
    onCloseRequest: () -> Unit = onDismiss
) {
    AlertDialog(
        onDismissRequest = onCloseRequest,
        title = {
            Text(text = title)
        },
        text = {
            Text(text = message)
        },
        confirmButton = {
            TextButton(onClick = onConfirm) {
                Text("Aceptar")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancelar")
            }
        }
    )
}
