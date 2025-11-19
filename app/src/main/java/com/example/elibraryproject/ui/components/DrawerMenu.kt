package com.example.elibraryproject.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun DrawerMenu() {
    Column(modifier = Modifier.padding(16.dp)) {
        Text("Home")
        Spacer(Modifier.height(8.dp))
        Text("Categories")
        Spacer(Modifier.height(8.dp))
        Text("Profile")
    }
}
