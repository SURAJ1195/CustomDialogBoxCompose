package com.example.dialogboxcompose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties

@Preview
@Composable
fun CustomDialogBox(onSelect: () -> Unit = {}, onCancel: () -> Unit ={}) {

    val spinnerItems = listOf("Item 1", "Item 2", "Item 3")
    // Selected item state
    var selectedItem by remember { mutableStateOf(spinnerItems[0]) }
    // Dropdown menu expanded state
    var expanded by remember { mutableStateOf(false) }
    Surface(
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Dialog(
                onDismissRequest = onCancel,
                properties = DialogProperties(
                    dismissOnBackPress = true,
                    dismissOnClickOutside = true
                )

            ) {
                Card(
                    modifier = Modifier
                        .size(500.dp)
                        .padding(16.dp),
                    shape = RoundedCornerShape(8.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                ) {
                    Column(modifier = Modifier.fillMaxSize()) {
                        // Close (X) button
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.End
                        ) {
                            Box(modifier = Modifier.wrapContentSize()){
                                IconButton(onClick = {
                                    expanded = true
                                }) {

                                    Icon(
                                        imageVector = Icons.Default.Person,
                                        contentDescription = "Close",
                                        tint = Color.Gray
                                    )
                                }
                                if(expanded){
                                    DropdownMenu(
                                        expanded = expanded,
                                        onDismissRequest = { expanded = false },
                                        modifier = Modifier.wrapContentSize(),

                                        ) {

                                        spinnerItems.forEach { item ->
                                            DropdownMenuItem(
                                                onClick = {
                                                    selectedItem = item
                                                    expanded = false
                                                }
                                            ) {
                                                Text(text = item)
                                            }
                                        }


                                    }
                                }
                            }



                                    IconButton(onClick = onCancel) {
                                        Icon(
                                            imageVector = Icons.Default.Close,
                                            contentDescription = "Close",
                                            tint = Color.Gray
                                        )
                                    }
                                }

                                Spacer(modifier = Modifier.height(16.dp))

                                // Dialog content here
                                Text(
                                    text = "This is the dialog content.",
                                    fontSize = 18.sp,
                                    modifier = Modifier.padding(16.dp)
                                )

                                Spacer(modifier = Modifier.weight(1f))

                                // Select and Cancel buttons
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.End
                                ) {
                                    Button(
                                        onClick = onSelect,
                                        modifier = Modifier.padding(end = 8.dp)
                                    ) {
                                        Text(text = "Select")
                                    }
                                    Button(
                                        onClick = onCancel,
                                        modifier = Modifier.padding(end = 8.dp)
                                    ) {
                                        Text(text = "Cancel")
                                    }
                                }
                            }
                        }
                    }
                }

            }

        }

        @Composable
        fun ShowDialog() {
            val showDialog = remember { mutableStateOf(false) }

            Button(onClick = { showDialog.value = true }) {
                Text(text = "Show")
            }
            if (showDialog.value) {
                CustomDialogBox {
                    showDialog.value = false
                }
            }


        }


