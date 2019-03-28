package kz.pandamobile.pandalib

import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import androidx.appcompat.app.AppCompatActivity

abstract class PandaActivity : AppCompatActivity() {

    /**
     * Displays a simple toast message.
     *
     * @param message message to be shown;
     */
    fun showToast(message: String, length: Int = LENGTH_SHORT) {
        Toast.makeText(this, message, length).show()
    }

    /**
     * Displays a simple toast message.
     *
     * @param messageResourceId message to be shown;
     */
    fun showToast(messageResourceId: Int, length: Int = LENGTH_SHORT) {
        Toast.makeText(this, getString(messageResourceId), length).show()
    }

}