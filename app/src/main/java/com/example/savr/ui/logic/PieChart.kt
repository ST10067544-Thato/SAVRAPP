package com.example.savr.ui.logic

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import com.example.savr.data.database.model.Category
import com.example.savr.data.database.model.Expense
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.formatter.PercentFormatter
import com.github.mikephil.charting.utils.MPPointF
import java.time.LocalDate

@Composable
fun CategoryPieChart(selectedDate: LocalDate, expenses: List<Expense>, categories: List<Category>) {
    val filteredExpenses = expenses.filter { it.date == selectedDate }
    val categoryData = filteredExpenses.groupBy { it.categoryId }
        .mapValues { (_, expenses) -> expenses.sumOf { it.amount }.toFloat() }
    val categoryNames = categoryData.keys.map { categoryId ->
        categories.find { it.id == categoryId }?.name ?: "Unknown"
    }
    val colors = listOf(
        Color(0xFF3299FF), Color(0xFF6CB5FD), Color(0xFF0068FF), Color(0xFF093030)
    )

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .wrapContentSize(Alignment.Center),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Pie chart using MPAndroidChart with Card for elevation
        Card(
            modifier = Modifier
                .size(250.dp)
                .padding(8.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
            shape = RoundedCornerShape(16.dp) // Rounded corners
        ) {
            AndroidView(factory = { context ->
                PieChart(context).apply {
                    description.isEnabled = false
                    legend.isEnabled = false
                    setUsePercentValues(true)
                    setEntryLabelColor(android.graphics.Color.WHITE)
                    setEntryLabelTextSize(12f)
                    // Remove hole and transparent circle
                    holeRadius = 0f
                    transparentCircleRadius = 0f
                    // Draw entry labels outside the chart
                    setDrawEntryLabels(false) // Disable entry labels inside slices
                    // Set value line color and width (apply to data object later)
                    // Customize slice spacing and selection shift (apply to dataSet later)
                    // Disable rotation
                    setRotationEnabled(false)
                    // Set animation
                    animateY(1400)
                }
            }, modifier = Modifier.size(250.dp), update = { pieChart ->
                val entries = categoryData.map { (categoryId, spends) ->
                    PieEntry(spends, categoryNames[categoryData.keys.indexOf(categoryId)])
                }
                val dataSet = PieDataSet(entries, "").apply {
                    this.colors = colors.map { it.toArgb() }.toIntArray().toMutableList()
                    valueTextSize = 12f
                    valueTextColor = android.graphics.Color.WHITE
                    // Set value line part 1 offset percentage
                    valueLinePart1OffsetPercentage = 80f
                    // Set value line part 1 length
                    valueLinePart1Length = 0.3f
                    // Set value line part 2 length
                    valueLinePart2Length = 0.4f
                    // Set whether value lines are curved
                    isValueLineVariableLength = true // Use isValueLineVariableLength instead of valueLineVariableLength
                    // Set X value position
                    xValuePosition = PieDataSet.ValuePosition.OUTSIDE_SLICE
                    // Set Y value position
                    yValuePosition = PieDataSet.ValuePosition.OUTSIDE_SLICE
                    // Set icons offset
                    iconsOffset = MPPointF.getInstance(0f, 40f)
                    // Set slice space and selection shift
                    sliceSpace = 4f
                    selectionShift = 8f
                    // Set value line color and width
                    setValueLineColor(Color.Gray.toArgb())
                    setValueLineWidth(0.5f)
                    // Set value text size and color
                    setValueTextSize(12f)
                    setValueTextColor(Color.Black.toArgb())
                    // Enable drawing values outside the slices using valueFormatter
                    valueFormatter = PercentFormatter(pieChart)
                }
                val pieData = PieData(dataSet)
                pieChart.data = pieData
                pieChart.invalidate()
            })
        }

        // Legend with improved spacing and layout
        Spacer(modifier = Modifier.height(16.dp)) // Add space between chart and legend

        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            categoryNames.forEachIndexed { index, categoryName ->
                LegendItem(categoryName, colors[index % colors.size])
                Spacer(modifier = Modifier.height(8.dp)) // Add space between legend items
            }
        }
    }
}

// Legend item composable with improved styling
@Composable
fun LegendItem(category: String, color: Color) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Box(
            modifier = Modifier
                .size(16.dp, 16.dp)
                .background(color, shape = RoundedCornerShape(4.dp)) // Rounded corners for legend item
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = category, fontSize = 14.sp, color = Color.DarkGray) // Adjust text size and color
    }
}