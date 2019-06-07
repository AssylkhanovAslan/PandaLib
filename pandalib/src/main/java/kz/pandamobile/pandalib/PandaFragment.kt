package kz.pandamobile.pandalib

import android.app.ProgressDialog
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import androidx.fragment.app.Fragment

/**
 * Base abstract fragment with common methods.
 */
abstract class PandaFragment : Fragment() {

    protected lateinit var dialog: ProgressDialog
    private var dialogCounter = 0
    private var isAttached = false

    override fun onDetach() {
        super.onDetach()
        isAttached = false
        dialog.dismiss()
    }

    /**
     * Sets mesage for the loader dialog
     *
     * @param message message to be shown;
     */
    fun setLoaderMessage(message: String) {
        dialog.setMessage(message)
    }


    /**
     * Sets mesage for the loader dialog
     *
     * @param messageResourceId resourse of message to be shown;
     */
    fun setLoaderMessage(messageResourceId: Int) {
        dialog.setMessage(getString(messageResourceId))
    }

    /**
     * Displays / Hides progress dialog.
     *
     * @param show Show dialog if true, hides it if false.
     */
    fun showLoader(show: Boolean) {
        if (isAttached) {
            if (show) {
                dialogCounter++
                dialog.show()
            } else {
                if (dialogCounter != 0) {
                    dialogCounter--
                }
                if (dialogCounter < 1) {
                    dialog.dismiss()
                }
            }
        }
    }

    /**
     * Displays a simple toast message.
     *
     * @param message message to be shown;
     */
    fun showToast(message: String, length: Int = LENGTH_SHORT) {
        Toast.makeText(activity, message, length).show()
    }

    /**
     * Displays a simple toast message.
     *
     * @param messageResourceId message to be shown;
     */
    fun showToast(messageResourceId: Int, length: Int = LENGTH_SHORT) {
        Toast.makeText(activity, getString(messageResourceId), length).show()
    }

    /**
     * Gets tag for stack.
     *
     * @return the tag for stack
     */
    fun getTagForStack(): String {
        return this::class.qualifiedName!!
    }
}