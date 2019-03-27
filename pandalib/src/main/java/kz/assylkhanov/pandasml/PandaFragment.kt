package kz.assylkhanov.pandasml

import android.app.ProgressDialog
import android.content.Context
import android.widget.Toast
import androidx.fragment.app.Fragment

/**
 * Base abstract fragment with common methods.
 */
abstract class PandaFragment : Fragment() {

    protected lateinit var dialog: ProgressDialog
    private var dialogCounter = 0
    private var isAttached = false

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        isAttached = true
        dialog = ProgressDialog(context)
        dialog.isIndeterminate = true
    }

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
    fun showToast(message: String) {
        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
    }

    /**
     * Displays a simple toast message.
     *
     * @param messageResourceId message to be shown;
     */
    fun showToast(messageResourceId: Int) {
        Toast.makeText(activity, getString(messageResourceId), Toast.LENGTH_SHORT).show()
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