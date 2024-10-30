package com.example.savr.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.savr.R
import com.example.savr.ui.logic.BottomNavBar
import com.example.savr.ui.logic.CustomNotificationBar
import com.example.savr.ui.logic.FilteredHomeResultRow
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.coil.CoilImage

@Composable
fun Home() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFF8D3C)) // Base orange background
    ) {
        // Notification Bar and Title
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp)
                .padding(horizontal = 36.dp)
        ) {
            CustomNotificationBar()

            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(bottom = 45.dp, start = 16.dp)
                    .fillMaxWidth()
            ) {
                //personalized welcome label with name dynamic on logged in user
                Text(
                    "Hi John, Welcome Back",
                    color = Color(0xFFFFFFFF),
                    fontSize = 20.sp,
                    modifier = Modifier.weight(1f) // Allow text to take available space
                )
                //little notification button on the far right of the welcome label
                IconButton(
                    onClick = { /* Handle click for icon 2 */ },
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_notifications), // Load the analysis icon
                        contentDescription = "notification",
                        modifier = Modifier.size(28.dp)
                    )
                }

            }
        }
        // Section which will dynamically display summary of logged in user's balance + expenses for the month
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(bottom = 16.dp, start = 60.dp, end = 60.dp)
                .fillMaxWidth()
        ) {
            Column(
                modifier = Modifier.width(115.dp)
            ) {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .padding(bottom = 8.dp)
                        .fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier
                            .border(
                                width = 1.dp,
                                color = Color(0xFFFFFFFF),
                                shape = RoundedCornerShape(3.dp)
                            )
                            .clip(shape = RoundedCornerShape(3.dp))
                            .width(12.dp)
                            .padding(horizontal = 3.dp)
                    ) {
                        CoilImage(
                            imageModel = { "https://i.imgur.com/1tMFzp8.png" },
                            imageOptions = ImageOptions(contentScale = ContentScale.Crop),
                            modifier = Modifier
                                .padding(top = 3.dp)
                                .height(6.dp)
                                .fillMaxWidth()
                        )
                    }
                    Text(
                        "Total Balance",
                        color = Color(0xFFFFFFFF),
                        fontSize = 12.sp,
                    )
                }
                Text(
                    "R7,783.00",
                    color = Color(0xFFFFF4EC),
                    fontSize = 24.sp,
                )
            }
            Column(
                modifier = Modifier
                    .width(1.dp)
                    .height(42.dp)
                    .background(
                        color = Color(0xFFFFF4EC),
                    )
            ) {}
            Column(
                modifier = Modifier.width(118.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .padding(bottom = 7.dp)
                        .fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier
                            .padding(end = 8.dp)
                            .border(
                                width = 1.dp,
                                color = Color(0xFFFFFFFF),
                                shape = RoundedCornerShape(3.dp)
                            )
                            .clip(shape = RoundedCornerShape(3.dp))
                            .width(12.dp)
                            .padding(horizontal = 3.dp)
                    ) {
                        CoilImage(
                            imageModel = { "https://i.imgur.com/1tMFzp8.png" },
                            imageOptions = ImageOptions(contentScale = ContentScale.Crop),
                            modifier = Modifier
                                .padding(top = 3.dp)
                                .height(5.dp)
                                .fillMaxWidth()
                        )
                    }
                    Text(
                        "Total Expense",
                        color = Color(0xFFFFFFFF),
                        fontSize = 12.sp,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
                Text(
                    "-R1,187.40",
                    color = Color(0xFF0068FF),
                    fontSize = 24.sp,
                )
            }
        }
        //little progress that will be dynamic and fill with black depending on progress of saving goal set by logged user
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(bottom = 15.dp, start = 50.dp, end = 50.dp)
                .clip(shape = RoundedCornerShape(13.dp))
                .fillMaxWidth()
                .background(
                    color = Color(0xFF052224), shape = RoundedCornerShape(13.dp)
                )
                .padding(start = 22.dp)
        ) {
            Text(
                "30%",
                color = Color(0xFFFFF4EC),
                fontSize = 12.sp,
            )
            Column(
                modifier = Modifier
                    .clip(shape = RoundedCornerShape(13.dp))
                    .width(261.dp)
                    .background(
                        color = Color(0xFFFFF4EC), shape = RoundedCornerShape(13.dp)
                    )
                    .padding(top = 8.dp, bottom = 8.dp, start = 176.dp, end = 23.dp)
            ) {
                Text(
                    "R2,000.00",
                    color = Color(0xFF052224),
                    fontSize = 13.sp,
                )
            }
        }
        //little motivation message which will display and change dynamically based on user saving goal progress
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(bottom = 20.dp, start = 60.dp, end = 60.dp)
                .fillMaxWidth()
        ) {
            //FIRE EMOJI
            CoilImage(
                imageModel = { "https://i.imgur.com/1tMFzp8.png" },
                imageOptions = ImageOptions(contentScale = ContentScale.Crop),
                modifier = Modifier
                    .padding(end = 11.dp)
                    .width(11.dp)
                    .height(11.dp)
            )
            Text(
                "30% of your expenses, looks good!",
                color = Color(0xFFFFFFFF),
                fontSize = 15.sp,
                modifier = Modifier.fillMaxWidth()
            )
        }

        // Curved white layered page for Home elements
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .background(
                    Color.White, shape = RoundedCornerShape(topStart = 50.dp, topEnd = 50.dp)
                )
                .verticalScroll(rememberScrollState())
        ) {
            Column( // Wrap content in a Column
                modifier = Modifier
                    .padding(top = 40.dp)
                    .padding(horizontal = 36.dp)
                    .fillMaxWidth()
            ) {
                //little cute modern and sleek weekly summary box section with a summary of the user's
                // previous week's expenses,and their highest spent category and their current
                // savings progress on one their saved goals like car but this one will have a
                //progress circle around the category symbol which will fill based on progress
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .padding(bottom = 20.dp)
                        .height(152.dp)
                        .clip(shape = RoundedCornerShape(31.dp))
                        .fillMaxWidth()
                        .background(
                            color = Color(0xFFFF8D3C)
                        )
                        .padding(vertical = 11.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .padding(end = 33.dp)
                            .width(71.dp)
                    ) {
                        //category or goal's icon plus a circle progress around it which will fill based on progress
                        IconButton(
                            onClick = { },
                            modifier = Modifier
                                .padding(bottom = 6.dp)
                                .height(71.dp)
                                .fillMaxWidth()
                        ) {
                            CoilImage(
                                imageModel = { "https://i.imgur.com/1tMFzp8.png" },
                                imageOptions = ImageOptions(contentScale = ContentScale.Crop),
                            )
                        }
                        TextButton(
                            onClick = {},
                            modifier = Modifier
                                .padding(horizontal = 9.dp)
                                .width(53.dp)
                        ) {
                            Text(
                                "Savings on goals",
                                color = Color(0xFFFFFFFF),
                                fontSize = 12.sp,
                            )
                        }
                    }
                    Column(
                        modifier = Modifier
                            .padding(end = 16.dp)
                            .border(
                                width = 2.dp,
                                color = Color(0xFFFFF4EC),
                            )
                            .width(2.dp)
                            .height(108.dp)
                    ) {}
                    Column(
                        modifier = Modifier.width(161.dp)
                    ) {
                        // summary section of the user's previous week's expenses,and their highest spent category and their current
                        Row(
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .padding(bottom = 12.dp, start = 8.dp, end = 2.dp)
                                .fillMaxWidth()
                        ) {
                            //revenue icon
                            CoilImage(
                                imageModel = { "https://i.imgur.com/1tMFzp8.png" },
                                imageOptions = ImageOptions(contentScale = ContentScale.Crop),
                                modifier = Modifier
                                    .width(19.dp)
                                    .height(34.dp)
                            )
                            Column(
                                modifier = Modifier.width(113.dp)
                            ) {
                                Text(
                                    "Revenue Last Week",
                                    color = Color(0xFFFFFFFF),
                                    fontSize = 12.sp,
                                    modifier = Modifier.padding(bottom = 6.dp)
                                )
                                Text(
                                    "R4,000.00",
                                    color = Color(0xFFFFFFFF),
                                    fontSize = 15.sp,
                                )
                            }
                        }
                        //horizontal seperator
                        Column(
                            modifier = Modifier
                                .padding(bottom = 12.dp)
                                .border(
                                    width = 2.dp,
                                    color = Color(0xFFFFF4EC),
                                )
                                .height(2.dp)
                                .fillMaxWidth()
                        ) {}
                        //highest spent category placeholder which will be dynamic per user (it's food in this case)
                        Row(
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .padding(start = 8.dp, end = 2.dp)
                                .fillMaxWidth()
                        ) {
                            //highest spent category icon
                            CoilImage(
                                imageModel = { "https://i.imgur.com/1tMFzp8.png" },
                                imageOptions = ImageOptions(contentScale = ContentScale.Crop),
                                modifier = Modifier
                                    .width(19.dp)
                                    .height(34.dp)
                            )
                            Column(
                                modifier = Modifier.width(91.dp)
                            ) {
                                Text(
                                    "Food Last Week",
                                    color = Color(0xFFFFFFFF),
                                    fontSize = 12.sp,
                                    modifier = Modifier.padding(bottom = 6.dp)
                                )
                                Text(
                                    "-R100.00",
                                    color = Color(0xFF0068FF),
                                    fontSize = 15.sp,
                                    modifier = Modifier.padding(start = 1.dp)
                                )
                            }
                        }
                    }
                }

                //Recent transactions section which will display recent transactions filtered based
                // on the period the user chooses from the button: "Daily", "Weekly" or "Monthly"
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            color = Color(0xFFFFF4EC), shape = RoundedCornerShape(22.dp)
                        )
                        .padding(vertical = 1.dp)
                ) {
                    OutlinedButton(
                        onClick = { },
                        border = BorderStroke(0.dp, Color.Transparent),
                        colors = ButtonDefaults.outlinedButtonColors(Color.Transparent),
                        contentPadding = PaddingValues(),
                        modifier = Modifier
                            .padding(end = 24.dp)
                            .clip(shape = RoundedCornerShape(10.dp))
                            .width(95.dp)
                            .background(
                                color = Color(0xFFFFF4EC), shape = RoundedCornerShape(10.dp)
                            )
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier.padding(vertical = 10.dp)
                        ) {
                            Text(
                                "Daily",
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
                            .padding(end = 24.dp)
                            .clip(shape = RoundedCornerShape(10.dp))
                            .width(95.dp)
                            .background(
                                color = Color(0xFFFFF4EC), shape = RoundedCornerShape(10.dp)
                            )
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier.padding(vertical = 10.dp)
                        ) {
                            Text(
                                "Weekly",
                                color = Color(0xFF052224),
                                fontSize = 15.sp,
                            )
                        }
                    }
                    //This button is orange because it is currently "selected". Change it to the
                    // "daily" button which must be selected by default.
                    OutlinedButton(
                        onClick = { },
                        border = BorderStroke(0.dp, Color.Transparent),
                        colors = ButtonDefaults.outlinedButtonColors(Color.Transparent),
                        contentPadding = PaddingValues(),
                        modifier = Modifier
                            .width(85.dp)
                            .padding(end = 8.dp)
                            .padding(vertical = 5.dp)
                            .background(
                                color = Color(0xFFFF8D3C), shape = RoundedCornerShape(24.dp)
                            )
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier.padding(vertical = 19.dp)
                        ) {
                            Text(
                                "Monthly",
                                color = Color(0xFFFFFFFF),
                                fontSize = 15.sp,
                            )
                        }
                    }
                }
                FilteredHomeResultRow() // Display the Conditional content based on selected filter
            }
        }
        BottomNavBar()
    }
}

@Preview(showBackground = true)
@Composable
fun HomePreview() {
    Home()
}

