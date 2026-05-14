package cz.cvut.fel.flagie.ui.screens.setting

import android.Manifest
import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import cz.cvut.fel.flagie.notification.NotificationHelper

@Composable
fun SettingScreen(viewModel: SettingViewModel) {
    val isDarkMode by viewModel.isDarkMode.collectAsState()
    val context = LocalContext.current

    val notificationHelper = remember { NotificationHelper(context) }

    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            notificationHelper.scheduleDailyReminder()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        Text("Settings", style = MaterialTheme.typography.headlineMedium)

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("Enable Dark Mode")
            Switch(
                checked = isDarkMode,
                onCheckedChange = { viewModel.toggleDarkMode(it) }
            )
        }

        HorizontalDivider()

        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text("Notifications", style = MaterialTheme.typography.titleMedium)
            Text(
                "Get a daily reminder to practice your flag knowledge.",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Button(
                onClick = {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                        permissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
                    } else {
                        notificationHelper.scheduleDailyReminder()
                    }
                },
                modifier = Modifier.padding(top = 8.dp)
            ) {
                Text("Schedule Daily Reminder")
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        Text(
            text = "App Version: 1.0.0",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.outline,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
    }
}