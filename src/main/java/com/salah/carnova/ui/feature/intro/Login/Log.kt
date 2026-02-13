package com.salah.carnova.ui.auth

import android.app.Activity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.salah.carnova.viewModel.AuthentificationViewModel
import kotlinx.coroutines.launch

@Composable
fun AuthScreen(
    viewModel: AuthentificationViewModel = viewModel(),
    onLoginSuccess: () -> Unit
) {
    val state by viewModel.state.collectAsState()
    val context = LocalContext.current
    val activity = context as Activity
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    // Launcher pour la connexion Google
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartIntentSenderForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            viewModel.handleGoogleResult(
                activity = activity,
                data = result.data,
                onSuccess = { onLoginSuccess() },
                onError = { message ->
                    scope.launch {
                        snackbarHostState.showSnackbar(message)
                    }
                }
            )
        }
    }

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        modifier = Modifier.fillMaxSize(),
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "Connexion", style = MaterialTheme.typography.headlineMedium)
                Spacer(modifier = Modifier.height(24.dp))

                OutlinedTextField(
                    value = email,
                    onValueChange = { email = it },
                    label = { Text("Email") },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = password,
                    onValueChange = { password = it },
                    label = { Text("Mot de passe") },
                    visualTransformation = PasswordVisualTransformation(),
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = { viewModel.loginUser(email, password) },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Se connecter")
                }

                Spacer(modifier = Modifier.height(8.dp))

                Button(
                    onClick = { viewModel.registerUser(email, password) },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Créer un compte")
                }

                Spacer(modifier = Modifier.height(16.dp))

                // --- Connexion Google ---
                Button(
                    onClick = {
                        viewModel.getGoogleIntent(activity) { request: IntentSenderRequest? ->
                            request?.let { launcher.launch(it) }
                        }
                    },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer
                    )
                ) {
                    Text("Se connecter avec Google")
                }

                if (state.isLoading) {
                    Spacer(modifier = Modifier.height(16.dp))
                    CircularProgressIndicator()
                }

                state.errorMessage?.let {
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(text = it, color = MaterialTheme.colorScheme.error)
                }
            }
        }
    }

    if (state.isSuccess) {
        onLoginSuccess()
    }
}
