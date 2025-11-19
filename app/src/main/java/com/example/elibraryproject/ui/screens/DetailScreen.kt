package com.example.elibraryproject.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.yourapp.data.dummyBooks

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    navController: NavController,
    bookId: Int
) {
    // Cari buku berdasarkan ID
    val book = dummyBooks.find { it.id == bookId }

    if (book == null) {
        // Kalau buku gak ketemu
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text("Buku tidak ditemukan", style = MaterialTheme.typography.titleLarge)
        }
        return
    }

    var isFavorite by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { },
                navigationIcon = {
                    IconButton(
                        onClick = { navController.navigateUp() },
                        modifier = Modifier
                            .padding(8.dp)
                            .size(40.dp)
                            .background(
                                color = MaterialTheme.colorScheme.surface.copy(alpha = 0.9f),
                                shape = CircleShape
                            )
                    ) {
                        Icon(
                            Icons.Default.ArrowBack,
                            contentDescription = "Back",
                            tint = MaterialTheme.colorScheme.onSurface
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Transparent
                )
            )
        }
    ) { padding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(padding)
        ) {

            // HERO
            AsyncImage(
                model = book.imageUrl,
                contentDescription = book.title,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(400.dp)
                    .clip(RoundedCornerShape(bottomStart = 32.dp, bottomEnd = 32.dp))
                    .background(Color.LightGray)
            )

            Spacer(modifier = Modifier.height(24.dp))

            // CONTENT SECTION
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp)
            ) {

                // TITLE
                Text(
                    text = book.title,
                    style = MaterialTheme.typography.headlineMedium.copy(
                        fontWeight = FontWeight.Bold,
                        fontSize = 28.sp
                    ),
                    color = MaterialTheme.colorScheme.onBackground
                )

                Spacer(modifier = Modifier.height(8.dp))

                // AUTHOR
                Text(
                    text = "by ${book.author}",
                    style = MaterialTheme.typography.bodyLarge.copy(
                        fontSize = 16.sp
                    ),
                    color = Color.Gray
                )

                Spacer(modifier = Modifier.height(24.dp))

                // ACTION BUTTONS
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    // BUTTON BACA
                    Button(
                        onClick = { /* Handle baca */ },
                        modifier = Modifier
                            .weight(1f)
                            .height(52.dp),
                        shape = RoundedCornerShape(16.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.primary
                        )
                    ) {
                        Text(
                            text = "Baca Sekarang",
                            style = MaterialTheme.typography.titleMedium.copy(
                                fontWeight = FontWeight.SemiBold
                            )
                        )
                    }

                    // FAVORITE BUTTON
                    FilledIconButton(
                        onClick = { isFavorite = !isFavorite },
                        modifier = Modifier.size(52.dp),
                        shape = RoundedCornerShape(16.dp),
                        colors = IconButtonDefaults.filledIconButtonColors(
                            containerColor = if (isFavorite)
                                Color(0xFFFF4444)
                            else
                                MaterialTheme.colorScheme.surfaceVariant
                        )
                    ) {
                        Icon(
                            Icons.Default.Favorite,
                            contentDescription = "Favorite",
                            tint = if (isFavorite) Color.White else Color.Gray
                        )
                    }

                    // SHARE BUTTON
                    FilledIconButton(
                        onClick = { /* Handle share */ },
                        modifier = Modifier.size(52.dp),
                        shape = RoundedCornerShape(16.dp),
                        colors = IconButtonDefaults.filledIconButtonColors(
                            containerColor = MaterialTheme.colorScheme.surfaceVariant
                        )
                    ) {
                        Icon(
                            Icons.Default.Share,
                            contentDescription = "Share",
                            tint = Color.Gray
                        )
                    }
                }

                Spacer(modifier = Modifier.height(32.dp))

                // DESCRIPTION SECTION
                Text(
                    text = "Tentang Buku",
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp
                    )
                )

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = getBookDescription(book.title),
                    style = MaterialTheme.typography.bodyLarge.copy(
                        lineHeight = 24.sp,
                        fontSize = 15.sp
                    ),
                    color = Color.Gray
                )

                Spacer(modifier = Modifier.height(32.dp))

                // METADATA CARDS
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    MetadataCard(
                        label = "Kategori",
                        value = "Programming",
                        modifier = Modifier.weight(1f)
                    )
                    MetadataCard(
                        label = "Tahun",
                        value = "2023",
                        modifier = Modifier.weight(1f)
                    )
                    MetadataCard(
                        label = "Halaman",
                        value = "432",
                        modifier = Modifier.weight(1f)
                    )
                }

                Spacer(modifier = Modifier.height(40.dp))
            }
        }
    }
}

@Composable
fun MetadataCard(
    label: String,
    value: String,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f)
        ),
        elevation = CardDefaults.cardElevation(0.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = value,
                style = MaterialTheme.typography.titleLarge.copy(
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                ),
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = label,
                style = MaterialTheme.typography.bodySmall,
                color = Color.Gray
            )
        }
    }
}

// Dummy generator
fun getBookDescription(title: String): String {
    return when {
        title.contains("Clean Code") ->
            "Buku ini mengajarkan prinsip-prinsip fundamental dalam menulis kode yang bersih, mudah dibaca, dan mudah dipelihara. Robert C. Martin membagikan pengalaman bertahun-tahun dalam pengembangan perangkat lunak profesional."

        title.contains("Atomic Habits") ->
            "James Clear mengungkap rahasia membangun kebiasaan baik dan menghilangkan kebiasaan buruk melalui perubahan kecil yang memberikan hasil luar biasa. Sebuah panduan praktis untuk transformasi diri."

        title.contains("Pragmatic") ->
            "Panduan komprehensif untuk menjadi programmer yang lebih efektif. Mencakup best practices, tips, dan teknik yang telah terbukti dari developer berpengalaman."

        else ->
            "Buku ini menyajikan materi berkualitas tinggi dengan pendekatan yang sistematis dan mudah dipahami. Cocok untuk pemula maupun profesional yang ingin meningkatkan kemampuan mereka dalam bidang teknologi dan pengembangan diri."
    }
}