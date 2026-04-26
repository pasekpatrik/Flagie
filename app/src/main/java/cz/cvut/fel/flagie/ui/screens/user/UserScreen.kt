package cz.cvut.fel.flagie.ui.screens.user

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import cz.cvut.fel.flagie.ui.screens.login.LoginViewModel

@Composable
fun UserScreen(
    viewModel: LoginViewModel,
    onDeleteSuccess: () -> Unit
) {
    val users by viewModel.users.collectAsState()
    val currentUser = users.firstOrNull()

    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var age by remember { mutableStateOf("") }
    var isEditing by remember { mutableStateOf(false) }


    LaunchedEffect(currentUser) {
        currentUser?.let {
            firstName = it.firstName
            lastName = it.lastName
            age = it.age.toString()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "Profile",
            style = MaterialTheme.typography.headlineLarge,
            color = MaterialTheme.colorScheme.primary
        )

        if (currentUser == null) {
            Text("No user found")
        } else {
            OutlinedTextField(
                value = firstName,
                onValueChange = { firstName = it },
                label = { Text("First name") },
                enabled = isEditing,
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = lastName,
                onValueChange = { lastName = it },
                label = { Text("Last name") },
                enabled = isEditing,
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = age,
                onValueChange = { if (it.all { c -> c.isDigit() }) age = it },
                label = { Text("Age") },
                enabled = isEditing,
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = {
                    if (isEditing) {
                        viewModel.updateUser(
                            id = currentUser.id,
                            firstName = firstName,
                            lastName = lastName,
                            age = age.toIntOrNull() ?: 0
                        )
                        isEditing = false
                    } else {
                        isEditing = true
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(if (isEditing) "Save" else "Edit")
            }

            if (!isEditing) {
                OutlinedButton(
                    onClick = {
                        viewModel.deleteUser(currentUser)
                        onDeleteSuccess()
                    },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.outlinedButtonColors(
                        contentColor = MaterialTheme.colorScheme.error
                    )
                ) {
                    Text("Delete Account")
                }
            }

            if (isEditing) {
                TextButton(onClick = {
                    isEditing = false
                    firstName = currentUser.firstName
                    lastName = currentUser.lastName
                    age = currentUser.age.toString()
                }) {
                    Text("Cancel")
                }
            }
        }
    }
}