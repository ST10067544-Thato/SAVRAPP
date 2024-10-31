package com.example.savr.ui.screens.analysis

import androidx.compose.runtime.*
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.*
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.ui.*
import androidx.compose.ui.draw.*
import androidx.compose.ui.graphics.*
import androidx.compose.ui.unit.*
import androidx.compose.ui.layout.*
import androidx.compose.ui.tooling.preview.Preview
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.coil.CoilImage

@Composable
fun Analysis() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(
                color = Color(0xFFFFFFFF),
            )
    ) {
        Column(
            modifier = Modifier
                .clip(shape = RoundedCornerShape(40.dp))
                .fillMaxWidth()
                .weight(1f)
                .background(
                    color = Color(0xFFFF8D3C), shape = RoundedCornerShape(40.dp)
                )
                .padding(top = 9.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(bottom = 38.dp, start = 37.dp, end = 37.dp)
                    .fillMaxWidth()
            ) {
                Text(
                    "16:04",
                    color = Color(0xFFFFFFFF),
                    fontSize = 13.sp,
                    modifier = Modifier
                        .padding(end = 4.dp)
                        .fillMaxWidth()
                )
                CoilImage(
                    imageModel = { "https://i.imgur.com/1tMFzp8.png" },
                    imageOptions = ImageOptions(contentScale = ContentScale.Crop),
                    modifier = Modifier
                        .padding(end = 5.dp)
                        .width(13.dp)
                        .height(11.dp)
                )
                Column(
                    modifier = Modifier
                        .padding(end = 5.dp)
                        .border(
                            width = 2.dp,
                            color = Color(0xFFFFFFFF),
                            shape = RoundedCornerShape(58.dp)
                        )
                        .clip(shape = RoundedCornerShape(58.dp))
                        .width(15.dp)
                        .height(8.dp)
                        .background(
                            color = Color(0xFFFFFFFF), shape = RoundedCornerShape(58.dp)
                        )
                ) {}
                CoilImage(
                    imageModel = { "https://i.imgur.com/1tMFzp8.png" },
                    imageOptions = ImageOptions(contentScale = ContentScale.Crop),
                    modifier = Modifier
                        .width(17.dp)
                        .height(9.dp)
                )
            }
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(bottom = 23.dp, start = 36.dp, end = 36.dp)
                    .fillMaxWidth()
            ) {
                //Back Button
                IconButton(
                    onClick = { }, modifier = Modifier
                        .width(19.dp)
                        .height(16.dp)
                ) {
                    CoilImage(
                        imageModel = { "https://i.imgur.com/1tMFzp8.png" },
                        imageOptions = ImageOptions(contentScale = ContentScale.Crop),
                    )
                }
                Text(
                    "Analysis",
                    color = Color(0xFFFFFFFF),
                    fontSize = 20.sp,
                )
                OutlinedButton(
                    onClick = { },
                    border = BorderStroke(0.dp, Color.Transparent),
                    colors = ButtonDefaults.outlinedButtonColors(Color.Transparent),
                    contentPadding = PaddingValues(),
                    modifier = Modifier
                        .clip(shape = RoundedCornerShape(25.dp))
                        .width(30.dp)
                        .background(
                            color = Color(0xFFFFF4EC), shape = RoundedCornerShape(25.dp)
                        )
                ) {
                    //App Notifications Button
                    Column(
                        modifier = Modifier.padding(horizontal = 8.dp)
                    ) {
                        IconButton(
                            onClick = { },
                            modifier = Modifier
                                .padding(top = 5.dp)
                                .height(18.dp)
                                .fillMaxWidth()
                        ) {
                            CoilImage(
                                imageModel = { "https://i.imgur.com/1tMFzp8.png" },
                                imageOptions = ImageOptions(contentScale = ContentScale.Crop),
                            )
                        }
                    }
                }
            }

            //Bar Chart Filter Buttons Section
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        color = Color(0xFFFFFFFF),
                    )
                    .padding(top = 35.dp)
            ) {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .padding(bottom = 30.dp, start = 36.dp, end = 36.dp)
                        .clip(shape = RoundedCornerShape(22.dp))
                        .fillMaxWidth()
                        .background(
                            color = Color(0xFFFFF4EC), shape = RoundedCornerShape(22.dp)
                        )
                        .padding(vertical = 5.dp, horizontal = 4.dp)
                ) {
                    OutlinedButton(
                        onClick = { },
                        border = BorderStroke(0.dp, Color.Transparent),
                        colors = ButtonDefaults.outlinedButtonColors(Color.Transparent),
                        contentPadding = PaddingValues(),
                        modifier = Modifier
                            .clip(shape = RoundedCornerShape(19.dp))
                            .width(80.dp)
                            .background(
                                color = Color(0xFFFF8D3C), shape = RoundedCornerShape(19.dp)
                            )
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier.padding(vertical = 19.dp)
                        ) {
                            Text(
                                "Daily",
                                color = Color(0xFFFFFFFF),
                                fontSize = 15.sp,
                            )
                        }
                    }
                    OutlinedButton(
                        onClick = { },
                        border = BorderStroke(0.dp, Color.Transparent),
                        colors = ButtonDefaults.outlinedButtonColors(Color.Transparent),
                        contentPadding = PaddingValues(),
                        modifier = Modifier
                            .clip(shape = RoundedCornerShape(19.dp))
                            .width(80.dp)
                            .background(
                                color = Color(0xFFFFF4EC), shape = RoundedCornerShape(19.dp)
                            )
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier.padding(vertical = 19.dp)
                        ) {
                            Text(
                                "Weekly",
                                color = Color(0xFF052224),
                                fontSize = 15.sp,
                            )
                        }
                    }
                    OutlinedButton(
                        onClick = { },
                        border = BorderStroke(0.dp, Color.Transparent),
                        colors = ButtonDefaults.outlinedButtonColors(Color.Transparent),
                        contentPadding = PaddingValues(),
                        modifier = Modifier
                            .clip(shape = RoundedCornerShape(10.dp))
                            .width(80.dp)
                            .background(
                                color = Color(0xFFFFF4EC), shape = RoundedCornerShape(10.dp)
                            )
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier.padding(vertical = 19.dp)
                        ) {
                            Text(
                                "Monthly",
                                color = Color(0xFF052224),
                                fontSize = 15.sp,
                            )
                        }
                    }
                    OutlinedButton(
                        onClick = { },
                        border = BorderStroke(0.dp, Color.Transparent),
                        colors = ButtonDefaults.outlinedButtonColors(Color.Transparent),
                        contentPadding = PaddingValues(),
                        modifier = Modifier
                            .clip(shape = RoundedCornerShape(19.dp))
                            .width(80.dp)
                            .background(
                                color = Color(0xFFFFF4EC), shape = RoundedCornerShape(19.dp)
                            )
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier.padding(vertical = 20.dp)
                        ) {
                            Text(
                                "Year",
                                color = Color(0xFF052224),
                                fontSize = 15.sp,
                            )
                        }
                    }
                }

                //Bar Chart Section
                Column(
                    modifier = Modifier
                        .padding(bottom = 32.dp, start = 36.dp, end = 36.dp)
                        .clip(shape = RoundedCornerShape(50.dp))
                        .height(347.dp)
                        .fillMaxWidth()
                        .background(
                            color = Color(0xFFFFF4EC), shape = RoundedCornerShape(50.dp)
                        )
                        .padding(top = 18.dp, bottom = 28.dp, end = 30.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .padding(bottom = 14.dp, start = 31.dp)
                            .fillMaxWidth()
                    ) {
                        Text(
                            "income & Expenses",
                            color = Color(0xFF093030),
                            fontSize = 15.sp,
                            modifier = Modifier
                                .padding(end = 4.dp)
                                .fillMaxWidth()
                        )
                        OutlinedButton(
                            onClick = { },
                            border = BorderStroke(0.dp, Color.Transparent),
                            colors = ButtonDefaults.outlinedButtonColors(Color.Transparent),
                            contentPadding = PaddingValues(),
                            modifier = Modifier
                                .clip(shape = RoundedCornerShape(12.dp))
                                .width(32.dp)
                                .background(
                                    color = Color(0xFFFF8D3C), shape = RoundedCornerShape(12.dp)
                                )
                        ) {
                            Column(
                                modifier = Modifier.padding(horizontal = 8.dp)
                            ) {
                                IconButton(
                                    onClick = { },
                                    modifier = Modifier
                                        .padding(top = 6.dp)
                                        .height(15.dp)
                                        .fillMaxWidth()
                                ) {
                                    CoilImage(
                                        imageModel = { "https://i.imgur.com/1tMFzp8.png" },
                                        imageOptions = ImageOptions(contentScale = ContentScale.Crop),
                                    )
                                }
                            }
                        }
                    }
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .padding(bottom = 18.dp, start = 31.dp)
                            .fillMaxWidth()
                    ) {
                        Text(
                            "15k",
                            color = Color(0xFF6CB5FD),
                            fontSize = 14.sp,
                            modifier = Modifier.padding(end = 1.dp)
                        )
                        Column(
                            modifier = Modifier
                                .width(278.dp)
                                .height(1.dp)
                        ) {}
                    }
                    Box(
                        modifier = Modifier.padding(bottom = 5.dp, start = 31.dp)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Column(
                                modifier = Modifier.width(18.dp)
                            ) {
                                Text(
                                    "10k",
                                    color = Color(0xFF6CB5FD),
                                    fontSize = 14.sp,
                                    modifier = Modifier.padding(bottom = 19.dp)
                                )
                                Text(
                                    "5k",
                                    color = Color(0xFF6CB5FD),
                                    fontSize = 14.sp,
                                    modifier = Modifier.padding(bottom = 19.dp)
                                )
                                Text(
                                    "1k",
                                    color = Color(0xFF6CB5FD),
                                    fontSize = 14.sp,
                                )
                            }
                        }
                        Column(
                            modifier = Modifier
                                .offset(x = 0.dp, y = 0.dp)
                                .align(Alignment.BottomEnd)
                                .width(295.dp)
                                .height(1.dp)
                        ) {}
                    }
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .padding(start = 54.dp)
                            .height(300.dp)
                            .fillMaxWidth()
                    ) {
                        Text(
                            "Mon",
                            color = Color(0xFF0E3E3E),
                            fontSize = 14.sp,
                            modifier = Modifier.padding(end = 21.dp)
                        )
                        Text(
                            "Tue",
                            color = Color(0xFF0E3E3E),
                            fontSize = 14.sp,
                            modifier = Modifier.padding(end = 19.dp)
                        )
                        Text(
                            "Wed",
                            color = Color(0xFF0E3E3E),
                            fontSize = 14.sp,
                            modifier = Modifier.padding(end = 18.dp)
                        )
                        Text(
                            "Thu",
                            color = Color(0xFF0E3E3E),
                            fontSize = 14.sp,
                            modifier = Modifier.padding(end = 24.dp)
                        )
                        Text(
                            "Fri",
                            color = Color(0xFF0E3E3E),
                            fontSize = 14.sp,
                            modifier = Modifier.padding(end = 27.dp)
                        )
                        Text(
                            "Sat",
                            color = Color(0xFF0E3E3E),
                            fontSize = 14.sp,
                            modifier = Modifier.padding(end = 22.dp)
                        )
                        Text(
                            "Sun",
                            color = Color(0xFF0E3E3E),
                            fontSize = 14.sp,
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                }

                //Summary of spends from bar chart selected period
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .padding(bottom = 12.dp, start = 108.dp, end = 108.dp)
                        .fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier
                            .border(
                                width = 2.dp,
                                color = Color(0xFFFF8D3C),
                                shape = RoundedCornerShape(6.dp)
                            )
                            .clip(shape = RoundedCornerShape(6.dp))
                            .width(25.dp)
                            .padding(horizontal = 7.dp)
                    ) {
                        CoilImage(
                            imageModel = { "https://i.imgur.com/1tMFzp8.png" },
                            imageOptions = ImageOptions(contentScale = ContentScale.Crop),
                            modifier = Modifier
                                .padding(top = 7.dp)
                                .height(12.dp)
                                .fillMaxWidth()
                        )
                    }
                    Column(
                        modifier = Modifier
                            .border(
                                width = 2.dp,
                                color = Color(0xFF0068FF),
                                shape = RoundedCornerShape(6.dp)
                            )
                            .clip(shape = RoundedCornerShape(6.dp))
                            .width(25.dp)
                            .padding(horizontal = 7.dp)
                    ) {
                        CoilImage(
                            imageModel = { "https://i.imgur.com/1tMFzp8.png" },
                            imageOptions = ImageOptions(contentScale = ContentScale.Crop),
                            modifier = Modifier
                                .padding(top = 6.dp)
                                .height(12.dp)
                                .fillMaxWidth()
                        )
                    }
                }
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .padding(bottom = 5.dp, start = 90.dp, end = 90.dp)
                        .fillMaxWidth()
                ) {
                    Text(
                        "Income",
                        color = Color(0xFF093030),
                        fontSize = 15.sp,
                    )
                    Text(
                        "Expense",
                        color = Color(0xFF093030),
                        fontSize = 15.sp,
                    )
                }
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .padding(bottom = 36.dp, start = 78.dp, end = 78.dp)
                        .fillMaxWidth()
                ) {
                    Text(
                        "R4,120.00",
                        color = Color(0xFF093030),
                        fontSize = 20.sp,
                    )
                    Text(
                        "R1,187.40",
                        color = Color(0xFF0068FF),
                        fontSize = 20.sp,
                    )
                }

                //Goals Section which comes from Categories page (Categories page will later be implemented)
                Text(
                    "My goals",
                    color = Color(0xFF093030),
                    fontSize = 15.sp,
                    modifier = Modifier.padding(bottom = 18.dp, start = 39.dp)
                )
                Box {
                    Row(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        OutlinedButton(
                            onClick = { },
                            border = BorderStroke(0.dp, Color.Transparent),
                            colors = ButtonDefaults.outlinedButtonColors(Color.Transparent),
                            contentPadding = PaddingValues(),
                            modifier = Modifier
                                .padding(start = 37.dp, end = 19.dp)
                                .clip(shape = RoundedCornerShape(50.dp))
                                .width(169.dp)
                                .background(
                                    color = Color(0xFFFF8D3C), shape = RoundedCornerShape(50.dp)
                                )
                        ) {
                            Column(
                                modifier = Modifier.padding(vertical = 20.dp)
                            ) {
                                Box {
                                    CoilImage(
                                        imageModel = { "https://i.imgur.com/1tMFzp8.png" },
                                        imageOptions = ImageOptions(contentScale = ContentScale.Crop),
                                        modifier = Modifier
                                            .padding(
                                                bottom = 10.dp,
                                                start = 27.dp,
                                                end = 27.dp,
                                            )
                                            .height(107.dp)
                                            .fillMaxWidth()
                                    )
                                    Row(
                                        modifier = Modifier
                                            .padding(
                                                bottom = 10.dp,
                                                start = 27.dp,
                                                end = 27.dp,
                                            )
                                            .height(107.dp)
                                            .fillMaxWidth()
                                            .padding(top = 4.dp, bottom = 40.dp, start = 37.dp)
                                    ) {
                                        Column(
                                            modifier = Modifier.weight(1f)
                                        ) {
                                            Column(
                                                modifier = Modifier
                                                    .border(
                                                        width = 4.dp,
                                                        color = Color(0xFF0068FF),
                                                    )
                                                    .width(52.dp)
                                                    .height(52.dp)
                                            ) {}
                                            Text(
                                                "30%",
                                                color = Color(0xFFFFFFFF),
                                                fontSize = 20.sp,
                                            )
                                        }
                                    }
                                }
                                Text(
                                    "Travel",
                                    color = Color(0xFFFFFFFF),
                                    fontSize = 15.sp,
                                    modifier = Modifier.padding(start = 59.dp)
                                )
                            }
                        }
                        OutlinedButton(
                            onClick = { },
                            border = BorderStroke(0.dp, Color.Transparent),
                            colors = ButtonDefaults.outlinedButtonColors(Color.Transparent),
                            contentPadding = PaddingValues(),
                            modifier = Modifier
                                .clip(shape = RoundedCornerShape(50.dp))
                                .width(169.dp)
                                .background(
                                    color = Color(0xFFFF8D3C), shape = RoundedCornerShape(50.dp)
                                )
                        ) {
                            Column(
                                modifier = Modifier.padding(vertical = 20.dp)
                            ) {
                                Box {
                                    CoilImage(
                                        imageModel = { "https://i.imgur.com/1tMFzp8.png" },
                                        imageOptions = ImageOptions(contentScale = ContentScale.Crop),
                                        modifier = Modifier
                                            .padding(
                                                bottom = 11.dp,
                                                start = 30.dp,
                                                end = 30.dp,
                                            )
                                            .height(107.dp)
                                            .fillMaxWidth()
                                    )
                                    Row(
                                        modifier = Modifier
                                            .padding(
                                                bottom = 11.dp,
                                                start = 30.dp,
                                                end = 30.dp,
                                            )
                                            .height(107.dp)
                                            .fillMaxWidth()
                                            .padding(top = 4.dp, bottom = 4.dp, start = 35.dp)
                                    ) {
                                        Column(
                                            modifier = Modifier
                                                .weight(1f)
                                                .padding(bottom = 39.dp)
                                        ) {
                                            Column(
                                                modifier = Modifier
                                                    .border(
                                                        width = 4.dp,
                                                        color = Color(0xFF0068FF),
                                                    )
                                                    .width(54.dp)
                                                    .height(102.dp)
                                            ) {}
                                            Text(
                                                "50%",
                                                color = Color(0xFFFFFFFF),
                                                fontSize = 20.sp,
                                            )
                                        }
                                    }
                                }
                                Text(
                                    "Car",
                                    color = Color(0xFFFFFFFF),
                                    fontSize = 15.sp,
                                    modifier = Modifier.padding(start = 68.dp)
                                )
                            }
                        }
                    }

                    //Bottom nav which will need to display across the WHOLE APP.
                    Column(
                        modifier = Modifier
                            .offset(x = 0.dp, y = 0.dp)
                            .align(Alignment.BottomStart)
                            .height(108.dp)
                            .fillMaxWidth()
                            .background(
                                color = Color(0xFFFFF4EC),
                            )
                            .padding(horizontal = 60.dp)
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .padding(top = 36.dp)
                                .fillMaxWidth()
                        ) {
                            IconButton(
                                onClick = { },
                                modifier = Modifier
                                    .padding(end = 43.dp)
                                    .width(25.dp)
                                    .height(31.dp)
                            ) {
                                CoilImage(
                                    imageModel = { "https://i.imgur.com/1tMFzp8.png" },
                                    imageOptions = ImageOptions(contentScale = ContentScale.Crop),
                                )
                            }
                            IconButton(
                                onClick = { },
                                modifier = Modifier
                                    .padding(end = 43.dp)
                                    .width(31.dp)
                                    .height(30.dp)
                            ) {
                                CoilImage(
                                    imageModel = { "https://i.imgur.com/1tMFzp8.png" },
                                    imageOptions = ImageOptions(contentScale = ContentScale.Crop),
                                )
                            }
                            IconButton(
                                onClick = { },
                                modifier = Modifier
                                    .padding(end = 43.dp)
                                    .width(33.dp)
                                    .height(25.dp)
                            ) {
                                CoilImage(
                                    imageModel = { "https://i.imgur.com/1tMFzp8.png" },
                                    imageOptions = ImageOptions(contentScale = ContentScale.Crop),
                                )
                            }
                            IconButton(
                                onClick = { },
                                modifier = Modifier
                                    .padding(end = 43.dp)
                                    .width(27.dp)
                                    .height(23.dp)
                            ) {
                                CoilImage(
                                    imageModel = { "https://i.imgur.com/1tMFzp8.png" },
                                    imageOptions = ImageOptions(contentScale = ContentScale.Crop),
                                )
                            }
                            IconButton(
                                onClick = { }, modifier = Modifier
                                    .width(22.dp)
                                    .height(27.dp)
                            ) {
                                CoilImage(
                                    imageModel = { "https://i.imgur.com/1tMFzp8.png" },
                                    imageOptions = ImageOptions(contentScale = ContentScale.Crop),
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AnalysisPreview() {
    Analysis()
}