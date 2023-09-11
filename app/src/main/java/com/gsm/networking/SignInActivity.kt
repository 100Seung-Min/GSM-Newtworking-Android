package com.gsm.networking

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions

class SignInActivity : ComponentActivity() {
    private lateinit var googleSignInClient: GoogleSignInClient
    private val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
        .requestServerAuthCode("")
        .requestEmail()
        .build()
    private val signInLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
        val result = GoogleSignIn.getSignedInAccountFromIntent(it.data).result
        if (result.email?.endsWith("@gsm.hs.kr") == true) {

        } else {
            googleSignInClient.signOut()
            Toast.makeText(this, "@gsm.hs.kr 계정으로만 접속 가능합니다.", Toast.LENGTH_SHORT).show()
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        googleSignInClient = GoogleSignIn.getClient(this, gso)
        setContent {
            signInLauncher.launch(googleSignInClient.signInIntent)
        }
    }
}