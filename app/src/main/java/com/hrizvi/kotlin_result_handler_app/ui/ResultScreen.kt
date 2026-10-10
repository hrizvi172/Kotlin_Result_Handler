package com.hrizvi.kotlin_result_handler_app.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hrizvi.kotlin_result_handler_app.model.UiState

@Composable
fun ResultScreen(
    state: UiState,
    onRetry: () -> Unit,
    onTriggerError: () -> Unit
) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "OOP + Sealed Classes Demo",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(Modifier.height(24.dp))

            // Exhaustive `when` over sealed class
            when (state) {
                is UiState.Loading -> LoadingView()
                is UiState.Success -> SuccessView(state)
                is UiState.Error -> ErrorView(state)
            }

            Spacer(Modifier.height(32.dp))
            Button(onClick = onRetry) { Text("Reload (Success)") }
            Spacer(Modifier.height(8.dp))
            OutlinedButton(onClick = onTriggerError) {
                Text("Trigger Error (id=99)")
            }
        }
    }
}

@Composable
private fun LoadingView() {
    CircularProgressIndicator()
    Spacer(Modifier.height(12.dp))
    Text("Loading...", fontSize = 18.sp)
}

@Composable
private fun SuccessView(state: UiState.Success) {
    val user = state.user
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFE8F5E9)
        )
    ) {
        Column(Modifier.padding(16.dp)) {
            Text("✅ SUCCESS", color = Color(0xFF2E7D32), fontWeight = FontWeight.Bold)
            Spacer(Modifier.height(8.dp))
            // Polymorphic call via interface property
            Text(user.displayName, fontWeight = FontWeight.SemiBold)
            Spacer(Modifier.height(4.dp))
            Text(user.describe())
        }
    }
}

@Composable
private fun ErrorView(state: UiState.Error) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFFFEBEE)
        )
    ) {
        Column(Modifier.padding(16.dp)) {
            Text("❌ ERROR", color = Color(0xFFC62828), fontWeight = FontWeight.Bold)
            Spacer(Modifier.height(8.dp))
            Text(state.message)
            state.cause?.let {
                Spacer(Modifier.height(4.dp))
                Text(
                    text = "Cause: ${it.javaClass.simpleName}",
                    fontSize = 12.sp,
                    color = Color.DarkGray
                )
            }
        }
    }
}