package ir.fatemelyasi.news.view.screens.signUpScreen

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ir.fatemelyasi.news.R
import ir.fatemelyasi.news.view.components.OutlinedTextField
import ir.fatemelyasi.news.view.ui.theme.LocalCustomColors
import org.koin.compose.viewmodel.koinViewModel


@Composable
fun SignUpScreen(
    viewModel: SignUpScreenViewModel = koinViewModel(),
    navigateToLogInScreen: () -> Unit,
    navigateToDashboardScreen: () -> Unit,
) {
    val scrollState = rememberScrollState()
    val context = LocalContext.current

    val name by viewModel::name
    val email by viewModel::email
    val password by viewModel::password
    val rePassword by viewModel::rePassword

    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.simple_black),
            contentDescription = "Login",
            modifier = Modifier
                .fillMaxSize()
                .blur(8.dp),
            contentScale = ContentScale.Crop
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Box(
            modifier = Modifier
                .padding(28.dp)
                .alpha(0.7f)
                .clip(
                    CutCornerShape(
                        topStart = 10.dp,
                        topEnd = 10.dp,
                        bottomStart = 10.dp,
                        bottomEnd = 10.dp
                    )
                )
                .background(MaterialTheme.colorScheme.background)
                .wrapContentHeight()
        ) {
            Column(
                modifier = Modifier
                    .padding(48.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {

                SignUpHeader()
                Spacer(modifier = Modifier.height(20.dp))
                SignUpFields(
                    name = name,
                    email = email,
                    password = password,
                    rePassword = rePassword,
                    onEmailChange = { viewModel.onEmailChange(it) },
                    onPasswordChange = { viewModel.onPasswordChange(it) },
                    onForgotPasswordClick = { },
                    onNameChange = { viewModel.onNameChange(it) },
                    onRePasswordChange = { viewModel.onRePasswordChange(it) },
                )
                SignUpFooter(
                    onSignUpClick = {
                        viewModel.onSignUpClick(
                            onSuccess = {
                                navigateToDashboardScreen()
                            },
                            onError = {
                                Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
                            }
                        )
                    },
                    onSignInClick = {
                        navigateToLogInScreen()
                    }
                )
            }
        }
    }


}

@Composable
fun SignUpHeader() {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = "Welcome ", fontSize = 36.sp, fontWeight = FontWeight.ExtraBold)
        Text(text = "Sign Up to continue", fontSize = 18.sp, fontWeight = FontWeight.SemiBold)
    }
}

@Composable
fun SignUpFields(
    name: String,
    email: String,
    password: String,
    rePassword: String,
    onNameChange: (String) -> Unit,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onRePasswordChange: (String) -> Unit,
    onForgotPasswordClick: () -> Unit
) {
    Column {
        OutlinedTextField(
            value = name,
            label = "Name",
            placeholder = "Enter your Full Name",
            onValueChange = onNameChange,
            leadingIcon = {
                Icon(Icons.Default.AccountCircle, contentDescription = "Name")
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            )
        )

        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(
            value = email,
            label = "Email",
            placeholder = "Enter your email address",
            onValueChange = onEmailChange,
            leadingIcon = {
                Icon(Icons.Default.Email, contentDescription = "Email")
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next
            )
        )

        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(
            value = password,
            label = "Password",
            placeholder = "Enter your password",
            onValueChange = onPasswordChange,
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Go
            ),
            leadingIcon = {
                Icon(Icons.Default.Lock, contentDescription = "Password")
            }
        )

        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(
            value = rePassword,
            label = "confirm Password",
            placeholder = "Enter your password ",
            onValueChange = onRePasswordChange,
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Go
            ),
            leadingIcon = {
                Icon(Icons.Default.Lock, contentDescription = "RePassword")
            }
        )
        TextButton(onClick = onForgotPasswordClick, modifier = Modifier.align(Alignment.End)) {
            Text(text = "Forgot Password?")
        }
    }
}

@Composable
fun SignUpFooter(
    onSignInClick: () -> Unit,
    onSignUpClick: () -> Unit,
) {
    val colors = LocalCustomColors.current
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Button(
            onClick = { onSignUpClick() },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = colors.onSurface,
                contentColor = colors.onSecondaryContainer
            )
        ) {
            Text(text = "Sign Up")
        }
        TextButton(onClick = onSignInClick) {
            Text(text = "have an account, click here")
        }
    }
}

