package com.example.elibraryproject.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.example.elibraryproject.R

@Composable
fun AppHeader(
    searchQuery: String,
    onQueryChange: (String) -> Unit,
    onSearch: () -> Unit,
    onLogoClick: () -> Unit = {},
    onKatalogClick: () -> Unit = {}
) {
    val keyboardController = LocalSoftwareKeyboardController.current

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        // LOGO
        Image(
            painter = painterResource(R.drawable.logo_brin),
            contentDescription = "Logo",
            modifier = Modifier
                .size(36.dp)
                .clickable { onLogoClick() }
        )

        Spacer(modifier = Modifier.width(12.dp))

        // SEARCH BAR
        OutlinedTextField(
            value = searchQuery,
            onValueChange = onQueryChange,
            placeholder = { Text("Cari buku...") },
            modifier = Modifier
                .weight(1f)
                .height(46.dp),
            shape = RoundedCornerShape(10.dp),
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Search
            ),
            keyboardActions = KeyboardActions(
                onSearch = {
                    keyboardController?.hide()
                    onSearch()
                }
            )
        )

        Spacer(modifier = Modifier.width(12.dp))

        // ICON KATALOG
        Icon(
            imageVector = Icons.Default.List,
            contentDescription = "Katalog",
            modifier = Modifier
                .size(28.dp)
                .clickable { onKatalogClick() }
        )
    }
}
