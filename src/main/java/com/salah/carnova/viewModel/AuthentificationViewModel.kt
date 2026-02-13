package com.salah.carnova.viewModel
import android.app.Activity
import android.content.Intent
import androidx.activity.result.IntentSenderRequest
import androidx.credentials.CredentialManager
import androidx.credentials.GetCredentialRequest
import androidx.credentials.GetCredentialResponse
import androidx.credentials.exceptions.GetCredentialException
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.auth.api.identity.Identity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.salah.carnova.domain.AuthState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AuthentificationViewModel: ViewModel() {
    private val auth = FirebaseAuth.getInstance()

    private val _state = MutableStateFlow(AuthState())
    val state: StateFlow<AuthState> = _state

    fun registerUser(email: String, password: String) {
        _state.value = AuthState(isLoading = true)
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                _state.value = if (task.isSuccessful) {
                    AuthState(isSuccess = true)
                } else {
                    AuthState(errorMessage = task.exception?.message)
                }
            }
    }

    fun loginUser(email: String, password: String) {
        _state.value = AuthState(isLoading = true)
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                _state.value = if (task.isSuccessful) {
                    AuthState(isSuccess = true)
                } else {
                    AuthState(errorMessage = task.exception?.message)
                }
            }
    }

    // --- Google Login ---
    fun getGoogleIntent(activity: Activity, onResult: (IntentSenderRequest?) -> Unit) {
        val oneTapClient = Identity.getSignInClient(activity)
        val signInRequest = com.google.android.gms.auth.api.identity.BeginSignInRequest.builder()
            .setGoogleIdTokenRequestOptions(
                com.google.android.gms.auth.api.identity.BeginSignInRequest.GoogleIdTokenRequestOptions.builder()
                    .setSupported(true)
                    .setServerClientId("TON_CLIENT_ID_WEB_FIREBASE")
                    .setFilterByAuthorizedAccounts(false)
                    .build()
            )
            .build()

        oneTapClient.beginSignIn(signInRequest)
            .addOnSuccessListener { result ->
                onResult(IntentSenderRequest.Builder(result.pendingIntent.intentSender).build())
            }
            .addOnFailureListener {
                onResult(null)
            }
    }

    fun handleGoogleResult(
        activity: Activity,
        data: Intent?,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {
        try {
            val credential = Identity.getSignInClient(activity).getSignInCredentialFromIntent(data)
            val idToken = credential.googleIdToken

            if (idToken != null) {
                val firebaseCredential = GoogleAuthProvider.getCredential(idToken, null)
                auth.signInWithCredential(firebaseCredential)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            onSuccess()
                        } else {
                            onError(task.exception?.message ?: "Erreur de connexion à Firebase")
                        }
                    }
            } else {
                onError("Token Google introuvable")
            }
        } catch (e: Exception) {
            onError("Erreur lors du traitement du résultat Google : ${e.message}")
        }
    }


}