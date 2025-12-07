package es.aiglesiasp.petshelter.ui.screens.login

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavController
import es.aiglesiasp.petshelter.R
import es.aiglesiasp.petshelter.ui.ScreenAppTheme
import es.aiglesiasp.petshelter.ui.common.LoadingProgressIndicator
import es.aiglesiasp.petshelter.ui.common.PSScaffold
import es.aiglesiasp.petshelter.ui.navigation.Home
import es.aiglesiasp.petshelter.ui.navigation.Register
import es.aiglesiasp.petshelter.ui.screens.common.CustomDialogWithImage
import es.aiglesiasp.petshelter.ui.screens.common.RoundedInputField

@Composable
fun LoginScreen(
    navController: NavController,
    viewModel: LoginViewModel = hiltViewModel(),
) {
    val uiState = viewModel.uiState.collectAsState()

    LaunchedEffect(uiState.value.loginResult) {
        if (uiState.value.loginResult is LoginResult.Success) {
            navController.navigate(Home) {
                popUpTo(0) { inclusive = true }
                launchSingleTop = true
            }
        }
    }

    ScreenAppTheme {
        PSScaffold (
            navController = navController,
            modifier = Modifier.fillMaxSize(),
            topBarTitle = stringResource(id = R.string.app_name),
            showBottomAppBar = false
        ) { paddingValues ->
            Box(modifier = Modifier.fillMaxSize().padding(paddingValues)) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(24.dp, Alignment.CenterVertically)
                ) {
                    LoginTitle()

                    LoginTextFields(
                        email = uiState.value.email,
                        password = uiState.value.password,
                        loginResult = uiState.value.loginResult,
                        onEmailChange = { viewModel.onEmailChange(it) },
                        onPasswordChange = { viewModel.onPasswordChange(it) },
                        onForgotPasswordClick = { viewModel.onForgotPasswordClick() }
                    )

                    LoginButtons(
                        onLoginClick = {
                            viewModel.onLoginClick()
                        },
                        onRegisterClick = {
                            navController.navigate(Register)
                        }
                    )
                }

                if (uiState.value.isLoading) {
                    LoadingProgressIndicator()
                }

                if (uiState.value.showWarningDialog) {
                    CustomDialogWithImage(
                        onConfirm = { viewModel.dismissAlert() }
                    )
                }
            }
        }
    }
}

@Composable
private fun LoginButtons(
    onLoginClick: () -> Unit,
    onRegisterClick: () -> Unit
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        // Log In (verde)
        Button(
            onClick = { onLoginClick() },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            shape = RoundedCornerShape(28.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary
            )
        ) {
            Text(
                text = "Iniciar",
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.SemiBold
                )
            )
        }

        // Sign Up (gris claro)
        Button(
            onClick = { onRegisterClick() },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            shape = RoundedCornerShape(28.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.surfaceVariant,
                contentColor = MaterialTheme.colorScheme.onSurface
            ),
            elevation = ButtonDefaults.buttonElevation(defaultElevation = 0.dp)
        ) {
            Text(
                text = "Registrarse",
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.SemiBold
                )
            )
        }
    }
}

@Composable
private fun LoginTextFields(
    email: String,
    password: String,
    loginResult: LoginResult?,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onForgotPasswordClick: () -> Unit
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        RoundedInputField(
            value = email,
            onValueChange = { onEmailChange(it) },
            placeholder = "Email"
        )
        if (loginResult is LoginResult.EmailNotFound) {
            LoginErrorText("Correo no encontrado")
        }

        RoundedInputField(
            value = password,
            onValueChange = { onPasswordChange(it) },
            placeholder = "Contraseña",
            isPassword = true
        )
        if (loginResult is LoginResult.InvalidPassword) {
            LoginErrorText("Contraseña erronea")
        }

        Text(
            text = "Ha olvidado la contraseña?",
            style = TextStyle(
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.primary,
                textDecoration = TextDecoration.Underline
            ),
            modifier = Modifier
                .align(Alignment.Start)
                .clickable { onForgotPasswordClick() }
        )
    }
}

@Composable
private fun LoginErrorText(text: String) {
    Text(
        text = text,
        color = MaterialTheme.colorScheme.error,
        style = MaterialTheme.typography.bodySmall,
        modifier = Modifier.padding(start = 8.dp)
    )
}

@Composable
private fun LoginTitle() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Bienvenido",
            style = MaterialTheme.typography.headlineMedium.copy(
                fontWeight = FontWeight.Bold
            )
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun LoginScreenPreview() {
    ScreenAppTheme {
        LoginScreen(navController = NavController(LocalContext.current))
    }
}
