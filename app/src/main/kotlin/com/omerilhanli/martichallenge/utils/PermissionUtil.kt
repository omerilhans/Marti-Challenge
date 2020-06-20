package com.omerilhanli.martichallenge.utils

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import com.omerilhanli.martichallenge.extensive.KEY_PERMISSIONS_REQUEST_CODE

object PermissionUtil {

    fun checkPermission(cTx: Context): Boolean {
        val fineLocationPermissionState =
            ActivityCompat.checkSelfPermission(cTx, Manifest.permission.ACCESS_FINE_LOCATION)
        val coarseLocationPermissionState =
            ActivityCompat.checkSelfPermission(cTx, Manifest.permission.ACCESS_COARSE_LOCATION)
        return (fineLocationPermissionState == PackageManager.PERMISSION_GRANTED) &&
                (coarseLocationPermissionState == PackageManager.PERMISSION_GRANTED)
    }

    fun requestPermission(activity: Activity) {
        val permissionAccessFineLocationApproved =
            ActivityCompat.checkSelfPermission(
                activity,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED

        val permissionAccessCoarseLocationApproved =
            ActivityCompat.checkSelfPermission(
                activity,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED

        val shouldProvideRationale =
            permissionAccessFineLocationApproved && permissionAccessCoarseLocationApproved

        if (shouldProvideRationale) {
            // Request permission
            ActivityCompat.requestPermissions(
                activity,
                arrayOf(
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ),
                KEY_PERMISSIONS_REQUEST_CODE
            )
        } else {
            ActivityCompat.requestPermissions(
                activity,
                arrayOf(
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ),
                KEY_PERMISSIONS_REQUEST_CODE
            )
        }
    }

}