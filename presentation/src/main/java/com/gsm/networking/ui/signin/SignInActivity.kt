package com.gsm.networking.ui.signin

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.gsm.networking.BuildConfig
import com.gsm.networking.R
import com.gsm.networking.ui.main.MainActivity
import com.gsm.networking.ui.theme.GSMNetworkingTheme
import com.gsm.networking.viewmodel.SignInViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignInActivity : ComponentActivity() {
    private val signInViewModel by viewModels<SignInViewModel>()
    private lateinit var googleSignInClient: GoogleSignInClient
    private val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
        .requestServerAuthCode(BuildConfig.CLIENT_ID)
        .requestEmail()
        .build()
    private val signInLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == -1) {
                val result = GoogleSignIn.getSignedInAccountFromIntent(it.data).result
                if (result.email?.endsWith("@gsm.hs.kr") == true) {
                    result.serverAuthCode?.let { signInViewModel.signIn(it) }
                } else {
                    googleSignInClient.signOut()
                    Toast.makeText(this, "@gsm.hs.kr 계정으로만 접속 가능합니다.", Toast.LENGTH_SHORT).show()
                }
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var isUnAuthorization = false
        observeLoginState {
            isUnAuthorization = true
        }
        googleSignInClient = GoogleSignIn.getClient(this, gso)
        setContent {
            GSMNetworkingTheme {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(painter = painterResource(id = R.drawable.ic_gsm_networking), contentDescription = "gsmNetworking")
                    if (isUnAuthorization) {
                        Spacer(modifier = Modifier.height(80.dp))
                        GoogleSignInButton {
                            signInLauncher.launch(googleSignInClient.signInIntent)
                        }
                        Spacer(modifier = Modifier.height(40.dp))
                        Text(text = "*GSM 계정으로만 접속 가능합니다.", color = Color(0xFF828387), style = GSMNetworkingTheme.typography.subtitle)
                    }
                }
            }
        }
    }

    private fun observeLoginState(unAuthorizationAction: () -> Unit) =
        signInViewModel.signInEntity.observe(this) {
            if (it == null) {
                unAuthorizationAction()
            } else {
                startActivity(
                    Intent(this, MainActivity::class.java).apply {
                        addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
                        putExtra("accessToken", it.accessToken)
                        putExtra("refreshToken", it.refreshToken)
                    }
                )
                finish()
            }
        }
}

@Composable
private fun GoogleSignInButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .background(color = Color(0xFFF5F6F8), shape = RoundedCornerShape(40.dp))
            .padding(vertical = 12.dp, horizontal = 20.dp)
            .clickable(onClick = onClick),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            modifier = Modifier.size(24.dp),
            painter = painterResource(id = R.drawable.ic_google),
            contentDescription = "googleIcon"
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = "구글로 로그인", color = Color(0xFF828387), style = GSMNetworkingTheme.typography.button.copy(fontSize = 17.sp))
    }
}