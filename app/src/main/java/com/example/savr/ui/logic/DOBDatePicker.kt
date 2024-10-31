import android.app.DatePickerDialog
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.util.Calendar

@Composable
fun DOBDatePicker(onDateSelected: (String) -> Unit) {
    var date by remember { mutableStateOf("") }
    val context = LocalContext.current // Get the context

    // Create a clickable text field for the DatePicker
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(bottom = 29.dp)
            .clip(RoundedCornerShape(18.dp))
            .fillMaxWidth()
            .background(color = Color(0xFFFFF4EC), shape = RoundedCornerShape(18.dp))
            .padding(vertical = 15.dp, horizontal = 35.dp)
            .clickable {
                // Create and show the DatePickerDialog
                val calendar = Calendar.getInstance()
                val year = calendar.get(Calendar.YEAR)
                val month = calendar.get(Calendar.MONTH)
                val day = calendar.get(Calendar.DAY_OF_MONTH)
                DatePickerDialog(context, { _, selectedYear, selectedMonth, selectedDay ->
                    // Format the selected date
                    val formattedDate = "$selectedDay/${selectedMonth + 1}/$selectedYear"
                    date = formattedDate // Update the date state variable
                    onDateSelected(formattedDate) // Invoke the callback with the selected date
                }, year, month, day).show() // Show the dialog
            }
    ) {
        Text(
            text = if (date.isEmpty()) "Please enter your date of birth." else date, // Show the selected date or placeholder
            color = if (date.isEmpty()) Color.Gray else Color(0xFF093030),
            fontSize = 15.sp, // Match font size
            modifier = Modifier.weight(1f) // Fill available space
        )
    }
}



