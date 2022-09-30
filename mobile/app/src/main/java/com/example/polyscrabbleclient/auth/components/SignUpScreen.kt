package com.example.polyscrabbleclient.auth.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.LayoutCoordinates
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.polyscrabbleclient.NavPage
import com.example.polyscrabbleclient.auth.model.AuthSignUpSate
import com.example.polyscrabbleclient.auth.viewmodel.AuthServerError
import com.example.polyscrabbleclient.auth.viewmodel.AuthValidation
import com.example.polyscrabbleclient.auth.viewmodel.SignUpViewModel
import com.example.polyscrabbleclient.ui.theme.create_account
import com.example.polyscrabbleclient.ui.theme.signUp
import org.intellij.lang.annotations.JdkConstants

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SignUpScreen(navController: NavController, viewModel: SignUpViewModel) {
    val keyboardController = LocalSoftwareKeyboardController.current
    val inputFocusRequester = LocalFocusManager.current

    Box(Modifier.clickable { keyboardController?.hide() ; inputFocusRequester.clearFocus() }) {
        Row(verticalAlignment = Alignment.Top, horizontalArrangement = Arrangement.Start) {
            Icon(imageVector = Icons.Default.ArrowBack,
                contentDescription = null,
                modifier = Modifier
                    .padding(10.dp)
                    .clickable {
                        viewModel.reset()
                        navController.navigate(NavPage.Login.label) {
                            popUpTo(NavPage.SignUp.label) {
                                inclusive = true
                            }
                            launchSingleTop = true
                        }
                    })
            SignUpContent(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                authState = viewModel.signUp.collectAsState().value,
                handleEvent = viewModel::handleSignUpEvent,
                serverError = viewModel.errors.observeAsState().value,
                isCreated = viewModel.isCreated.collectAsState().value,
                isInProcess = viewModel.isInProcess.collectAsState().value,
                onLogin = {
                    navController.navigate(NavPage.Login.label) {
                        popUpTo(NavPage.SignUp.label) {
                            inclusive = true
                        }
                        launchSingleTop = true
                    }
                }
            )
        }
    }
}

@Composable
fun SignUpContent(
    modifier: Modifier=Modifier,
    authState: AuthSignUpSate,
    serverError: List<AuthServerError>?,
    isCreated: Boolean,
    isInProcess: Boolean,
    handleEvent: (event: SignUpViewModel.AuthEvent)-> Unit,
    onLogin: () -> Unit
) {
    SignUpForm(
        email = authState.email,
        password = authState.password,
        username = authState.name,
        serverError = serverError,
        isCreated = isCreated,
        onEmailChanged = { email ->
            handleEvent(
                SignUpViewModel.AuthEvent.EmailChanged(email)
            )
        },
        onPasswordChanged = { password ->
            handleEvent(
                SignUpViewModel.AuthEvent.PasswordChanged(password)
            )
        },
        onUsernameChanged = { username ->
            handleEvent(
                SignUpViewModel.AuthEvent.UsernameChanged(username)
            )
        },
        onCreate = { handleEvent(SignUpViewModel.AuthEvent.CreateAccount) },
        onLogin = { onLogin() },
        isInProcess = isInProcess
    )

}

//@Preview(showBackground = true)
@Composable
fun SignUpForm(
    modifier: Modifier = Modifier,
    email: String,
    password: String,
    username: String,
    onEmailChanged: (email: String) -> Unit,
    onPasswordChanged: (password: String) -> Unit,
    onUsernameChanged:(username: String) -> Unit,
    onCreate: () -> Unit,
    onLogin: () -> Unit,
    serverError: List<AuthServerError>?,
    isCreated: Boolean,
    isInProcess : Boolean
) {
    val missingEmailError = remember { mutableStateOf(false) }
    val missingPasswordError = remember { mutableStateOf(false) }
    val missingNameError = remember { mutableStateOf(false) }

    fun hasEmptySpace(): Boolean {
        return AuthValidation.hasAtLeastOneEmptyField(
            email = email,
            password = password,
            name = null
        )
    }

    Box(
        Modifier
            .fillMaxWidth()
            .fillMaxHeight(), contentAlignment = Alignment.Center
    ) {
        Card(
            Modifier.width(350.dp)
        ) {
            Column(
                Modifier.padding(10.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(create_account, fontSize = 25.sp)
                Spacer(modifier = Modifier.height(15.dp))

                Column() {
                    UserNameInput(name = username,
                        onUsernameChanged = {
                            onUsernameChanged(it); missingNameError.value = false
                        },
                        serverError = serverError?.find { error -> error.label == AuthServerError.NameAlreadyTaken.label },
                        missingFieldError = missingNameError.value
                    )
                    EmailInput(
                        email = email,
                        onEmailChanged = { onEmailChanged(it); missingEmailError.value = false },
                        serverError = serverError?.find { error -> error.label == AuthServerError.EmailAlreadyTaken.label },
                        missingFieldError = missingEmailError.value
                    )
                    PasswordInput(
                        password = password,
                        onPasswordChanged = {
                            onPasswordChanged(it); missingPasswordError.value = false
                        },
                        serverError = null, // No error from server
                        missingFieldError = missingPasswordError.value,
                        onCreation = true // Flag form
                    )
                }

                Button(
                    enabled= !isInProcess, onClick = {
                    if (!hasEmptySpace()) {
                        onCreate()
                        if (isCreated) {
                            onLogin()
                        }
                    } else {
                        if (email.isEmpty()) {
                            missingEmailError.value = true
                        }
                        if (password.isEmpty()) {
                            missingPasswordError.value = true
                        }
                        if (username.isEmpty()) {
                            missingNameError.value = true
                        }
                    }
                }) {
                    Text(signUp)
                }

            }
        }
    }
}
