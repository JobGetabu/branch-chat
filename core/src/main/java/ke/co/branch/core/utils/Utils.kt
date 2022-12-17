package ke.co.branch.core.utils

import android.app.Activity
import com.google.android.material.snackbar.Snackbar
import java.text.SimpleDateFormat
import java.util.*

fun formatDate(date: String?, originFormat: String, toFormat: String): String? {
    return try {
        val parsedDate = SimpleDateFormat(originFormat, Locale.getDefault()).parse(date)
        SimpleDateFormat(toFormat, Locale.getDefault()).format(parsedDate)
    } catch (e: Exception) {
        date
    }
}

fun formatDate(date: Date): String? {
    var toFormat = "E,MMM yyyy HH:mm"
    return SimpleDateFormat(toFormat, Locale.getDefault()).format(date)
}

fun showSnackBar(message: String?, activity: Activity?) {
    if (null != activity && null != message) {
        Snackbar.make(
            activity.findViewById(android.R.id.content),
            message, Snackbar.LENGTH_SHORT
        ).show()
    }
}