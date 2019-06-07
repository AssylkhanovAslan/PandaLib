    package kz.pandamobile.pandalib.utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.preference.PreferenceManager
import android.provider.Settings
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment


class PermissionUtils {
    companion object {

        fun useRunTimePermissions(): Boolean {
            return Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP_MR1
        }

        fun hasPermission(context: Context, permission: String): Boolean {
            return if (useRunTimePermissions()) {
                ActivityCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED
            } else true
        }

        fun hasPermission(context: Context, permissions: Array<String>): Boolean {
            if (useRunTimePermissions()) {
                for (permission in permissions) {
                    if (ActivityCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_DENIED) {
                        return false
                    }
                }
                return true
            } else {
                return true
            }
        }

        fun requestPermissions(activity: Activity, permission: Array<String>, requestCode: Int) {
            if (useRunTimePermissions()) {
                activity.requestPermissions(permission, requestCode)
            }
        }

        fun requestPermissions(fragment: Fragment, permission: Array<String>, requestCode: Int) {
            if (useRunTimePermissions()) {
                fragment.requestPermissions(permission, requestCode)
            }
        }

        fun shouldShowRational(activity: Activity, permission: String): Boolean {
            return if (useRunTimePermissions()) {
                activity.shouldShowRequestPermissionRationale(permission)
            } else false
        }

        fun shouldAskForPermission(activity: Activity, permission: String): Boolean {
            return if (useRunTimePermissions()) {
                !hasPermission(activity, permission) && (!hasAskedForPermission(
                    activity,
                    permission
                ) || shouldShowRational(activity, permission))
            } else false
        }

        fun goToAppSettings(activity: Activity) {
            val intent = Intent(
                Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                Uri.fromParts("package", activity.packageName, null)
            )
            intent.addCategory(Intent.CATEGORY_DEFAULT)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            activity.startActivity(intent)
        }

        fun hasAskedForPermission(activity: Activity, permission: String): Boolean {
            return PreferenceManager
                .getDefaultSharedPreferences(activity)
                .getBoolean(permission, false)
        }

        fun markedPermissionAsAsked(activity: Activity, permission: String) {
            PreferenceManager
                .getDefaultSharedPreferences(activity)
                .edit()
                .putBoolean(permission, true)
                .apply()
        }
    }
}