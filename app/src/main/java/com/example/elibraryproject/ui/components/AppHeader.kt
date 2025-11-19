package com.example.elibraryproject.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.elibraryproject.R

@Composable
fun AppHeader(
    onLogoClick: () -> Unit = {},
    onKatalogClick: () -> Unit = {}
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        // LOGO
        Image(
            painter = painterResource(id = R.drawable.logo_brin),
            contentDescription = "Logo",
            modifier = Modifier
                .size(40.dp)
                .clickable { onLogoClick() }
        )

        // SEARCH BAR
        TextField(
            value = "",
            onValueChange = {},
            placeholder = { Text("Search...") },
            singleLine = true,
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 16.dp)
                .height(42.dp),
            shape = RoundedCornerShape(12.dp)
        )

        // ICON KATALOG
        Icon(
            Icons.Default.List,
            contentDescription = "Katalog",
            modifier = Modifier
                .size(28.dp)
                .clickable { onKatalogClick() }
        )
    }
}

