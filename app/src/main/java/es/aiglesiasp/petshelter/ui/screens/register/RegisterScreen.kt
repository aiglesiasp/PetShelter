package es.aiglesiasp.petshelter.ui.screens.register

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowDropUp
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavController
import es.aiglesiasp.petshelter.ui.ScreenAppTheme
import es.aiglesiasp.petshelter.ui.common.PSScaffold
import es.aiglesiasp.petshelter.ui.navigation.Login
import es.aiglesiasp.petshelter.ui.screens.common.RoundedInputField

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(
    navController: NavController,
    viewModel: RegisterViewModel = hiltViewModel(),
) {
    val uiState = viewModel.uiState.collectAsState()

    LaunchedEffect(uiState.value.isRegisterSuccess) {
        if (uiState.value.isRegisterSuccess) {
            navController.navigate(Login)
        }
    }

    ScreenAppTheme {
        PSScaffold (
            navController = navController,
            modifier = Modifier.fillMaxSize(),
            topBarTitle = "Registrarse",
            showTopAppBarArrowBack = true,
            showBottomAppBar = false
        ) { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(horizontal = 24.dp, vertical = 24.dp),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Top
            ) {
                // Formulario
                Column(
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    NameTextField(
                        name = uiState.value.name,
                        onNameChange = { viewModel.onNameChange(it) }
                    )

                    EmailTextField(
                        email = uiState.value.email,
                        onEmailChange = { viewModel.onEmailChange(it) }
                    )

                    RoleSelector(
                        role = uiState.value.role,
                        expanded = uiState.value.expanded,
                        onRoleChange = { viewModel.onRoleChange(it) },
                        onExpandedChange = { viewModel.onExpandedChange(it) }
                    )

                    PasswordTextField(
                        password = uiState.value.password,
                        onPasswordChange = { viewModel.onPasswordChange(it) }
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))

                // Botón Sign Up
                RegisterButton(
                    onRegisterClick = { viewModel.onRegisterClick() }
                )

                Spacer(modifier = Modifier.weight(1f))

                // Texto inferior
                AccountRow(
                    onAccountClick = {
                        navController.navigate(Login) {
                            popUpTo(Login) { inclusive = true }
                        }
                    }
                )
            }
        }
    }
}

@Composable
private fun RegisterButton(
    onRegisterClick: () -> Unit
) {
    Button(
        onClick = { onRegisterClick() },
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp),
        shape = RoundedCornerShape(28.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primary
        )
    ) {
        Text(
            text = "Registrase",
            style = MaterialTheme.typography.titleMedium.copy(
                fontWeight = FontWeight.SemiBold
            )
        )
    }
}

@Composable
private fun AccountRow(
    onAccountClick: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Ya tiene una cuenta? ",
            style = MaterialTheme.typography.bodyMedium
        )
        Text(
            text = "Iniciar sesión",
            style = MaterialTheme.typography.bodyMedium.copy(
                color = MaterialTheme.colorScheme.primary,
                textDecoration = TextDecoration.Underline
            ),
            modifier = Modifier.clickable { onAccountClick() }
        )
    }
}

@Composable
private fun PasswordTextField(
    password: String,
    onPasswordChange: (String) -> Unit
) {
    Text(text = "Contraseña", style = MaterialTheme.typography.bodyLarge)
    RoundedInputField(
        value = password,
        onValueChange = { onPasswordChange(it) },
        placeholder = "Añade tu contraseña",
        isPassword = true
    )
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun RoleSelector(
    role: String,
    expanded: Boolean,
    onRoleChange: (String) -> Unit,
    onExpandedChange: (Boolean) -> Unit
) {
    Text(text = "Rol", style = MaterialTheme.typography.bodyLarge)
    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { onExpandedChange(it) }
    ) {
        RoundedInputField(
            value = role,
            onValueChange = { },
            placeholder = "Seleccione un rol",
            modifier = Modifier
                .menuAnchor()
                .fillMaxWidth(),
            readOnly = true,
            trailingIcon = {
                Icon(
                    imageVector = if (expanded) Icons.Default.ArrowDropUp else Icons.Default.ArrowDropDown,
                    contentDescription = "Seleccione un rol"
                )
            }
        )

        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { onExpandedChange(false) }
        ) {
            DropdownMenuItem(
                text = { Text("Adoptante") },
                onClick = {
                    onRoleChange("Adoptante")
                    onExpandedChange(false)
                }
            )
            DropdownMenuItem(
                text = { Text("Refugio") },
                onClick = {
                    onRoleChange("Refugio")
                    onExpandedChange(false)
                }
            )
        }
    }
}

@Composable
private fun EmailTextField(
    email: String,
    onEmailChange: (String) -> Unit
) {
    Text(text = "Correo", style = MaterialTheme.typography.bodyLarge)
    RoundedInputField(
        value = email,
        onValueChange = { onEmailChange(it) },
        placeholder = "Añade tu correo"
    )
}

@Composable
private fun NameTextField(
    name: String,
    onNameChange: (String) -> Unit
) {
    Text(text = "Nombre", style = MaterialTheme.typography.bodyLarge)
    RoundedInputField(
        value = name,
        onValueChange = { onNameChange(it) },
        placeholder = "Introduce su nombre"
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun RegisterScreenPreview() {
    MaterialTheme {
        RegisterScreen(navController = NavController(LocalContext.current))
    }
}
