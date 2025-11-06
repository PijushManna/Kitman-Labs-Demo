package com.kitman.ui.login

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@Composable
fun LoginScreen(
    onSuccessfulLogin: () -> Unit
) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val isUserNameValid by derivedStateOf { username.length > 3 }
    val isPasswordValid by derivedStateOf { password.length > 3 }
    val snackbarHostState = remember { SnackbarHostState() }
    val loginViewModel: LoginViewModel = koinViewModel()
    val loginResponse by loginViewModel.loginState.collectAsState()
    val scope = rememberCoroutineScope()
    val focusManager = LocalFocusManager.current


    LaunchedEffect(loginResponse) {
        when  {
            loginResponse.isLoading -> {
                snackbarHostState.showSnackbar("Authenticating Details")
            }
            loginResponse.isSuccess -> {
                snackbarHostState.showSnackbar("Success : ${loginResponse.message}")
                scope.launch {
                    delay(1000L)
                    onSuccessfulLogin()
                }
            }
            loginResponse.isError -> {
                snackbarHostState.showSnackbar("Error : ${loginResponse.message}")
            }
        }
    }

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) {
        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
        ) {
            Spacer(Modifier.height(60.dp))
            Text("Login", modifier = Modifier.padding(horizontal = 24.dp, vertical = 16.dp), fontSize = 36.sp)
            Spacer(Modifier.height(20.dp))
            Surface(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 24.dp),
                shape = RoundedCornerShape(16.dp),
                border = BorderStroke(1.dp, Color.Black),
                shadowElevation = 4.dp,
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                ) {
                    Spacer(Modifier.height(16.dp))
                    TextField(
                        modifier = Modifier.fillMaxWidth(),
                        value = username,
                        onValueChange = { username = it },
                        label = { Text("Username") },
                    )
                    AnimatedVisibility(!isUserNameValid && username.isNotEmpty()) {
                        Spacer(modifier = Modifier.height(8.dp))
                        Text("Username must be greater than 3 characters" , color = Color.Red)
                    }

                    Spacer(Modifier.height(16.dp))
                    TextField(
                        modifier = Modifier.fillMaxWidth(),
                        value = password,
                        onValueChange = { password = it },
                        label = { Text("Password") },
                        visualTransformation = PasswordVisualTransformation(),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
                    )
                    AnimatedVisibility(!isPasswordValid && password.isNotEmpty()) {
                        Spacer(modifier = Modifier.height(8.dp))
                        Text("Password must be greater than 3 characters" , color = Color.Red)
                    }
                    Spacer(Modifier.height(24.dp))
                    Row(horizontalArrangement = Arrangement.End, verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth()) {
                        Button(onClick = {
                            loginViewModel.login(username, password)
                            focusManager.clearFocus()
                        }, enabled = isUserNameValid && isPasswordValid) {
                            Text("Login")
                        }
                    }
                }
            }
        }
    }
}