package com.example.marketplacesiswa.ui

import com.example.marketplacesiswa.model.Product
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {

    var currentScreen by remember { mutableStateOf("home") }

    val productList = remember {
        mutableStateListOf<Product>()
    }

    val snackbarHostState = remember { SnackbarHostState() }

    val scope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        if (productList.isEmpty()) {

            productList.add(
                Product(
                    name = "Brownies Lumer",
                    price = "15000",
                    description = "Cokelat melimpah."
                )
            )

            productList.add(
                Product(
                    name = "Kaos Custom",
                    price = "85000",
                    description = "Bahan adem."
                )
            )
        }
    }

    Scaffold(

        snackbarHost = {
            SnackbarHost(snackbarHostState)
        },

        topBar = {

            CenterAlignedTopAppBar(

                title = {

                    Text(
                        text = if (currentScreen == "add")
                            "Tambah Produk"
                        else
                            "MarketSiswa",

                        fontWeight = FontWeight.Bold
                    )
                },

                navigationIcon = {

                    if (currentScreen == "add") {

                        IconButton(
                            onClick = {
                                currentScreen = "home"
                            }
                        ) {

                            Icon(
                                Icons.Default.ArrowBack,
                                contentDescription = "Back"
                            )
                        }
                    }
                }
            )
        },

        bottomBar = {

            NavigationBar {

                NavigationBarItem(
                    selected = currentScreen == "home",

                    onClick = {
                        currentScreen = "home"
                    },

                    label = {
                        Text("Home")
                    },

                    icon = {
                        Icon(Icons.Default.Home, null)
                    }
                )

                NavigationBarItem(
                    selected = currentScreen == "profile",

                    onClick = {
                        currentScreen = "profile"
                    },

                    label = {
                        Text("Profile")
                    },

                    icon = {
                        Icon(Icons.Default.Person, null)
                    }
                )
            }
        },

        floatingActionButton = {

            if (currentScreen == "home") {

                ExtendedFloatingActionButton(

                    onClick = {
                        currentScreen = "add"
                    }

                ) {

                    Icon(Icons.Default.Add, null)

                    Spacer(modifier = Modifier.width(8.dp))

                    Text("Jual")
                }
            }
        }

    ) { innerPadding ->

        Box {

            when (currentScreen) {

                "home" -> {
                    HomeScreen(
                        products = productList
                    )
                }

                "add" -> {

                    AddProductScreen(

                        onProductAdded = { product ->

                            productList.add(0, product)

                            scope.launch {

                                currentScreen = "home"

                                snackbarHostState.showSnackbar(
                                    "Produk berhasil ditambahkan!"
                                )
                            }
                        }
                    )
                }

                "profile" -> {
                    ProfileScreen()
                }
            }
        }
    }
}