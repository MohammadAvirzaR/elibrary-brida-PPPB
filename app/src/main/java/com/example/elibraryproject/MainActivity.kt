package com.example.elibraryproject

import BookRepository


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.elibraryproject.data.api.ApiClient

import com.example.elibraryproject.ui.components.BottomBar
import com.example.elibraryproject.ui.navigation.AppNavGraph
import com.example.elibraryproject.viewmodel.BookViewModel
import com.example.elibraryproject.viewmodel.BookViewModelFactory


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()

            val api = ApiClient.openLibraryApi
            val repo = BookRepository(api)

            val bookViewModel: BookViewModel = viewModel(
                factory = BookViewModelFactory(repo)
            )

            Scaffold(

                bottomBar = {
                    BottomBar(navController)
                }
            ) { innerPadding ->
                AppNavGraph(
                    navController = navController,
                    bookViewModel = bookViewModel,
                    modifier = Modifier.padding(innerPadding)
                )
            }
        }
    }
}

@Composable
fun AppHeader() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 24.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        // LOGO
        Image(
            painter = painterResource(id = R.drawable.logo_brin),
            contentDescription = "Logo",
            modifier = Modifier.size(40.dp)
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


    }
}
