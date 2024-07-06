package by.eapp.mts.data.permission

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat

class PermissionStateProviderImpl(
    context: Context
): PermissionStateProvider {
    private val appContext: Context = context.applicationContext

    override fun checkRecordAudioPermissionIsGranted(): Boolean {
        return ActivityCompat.checkSelfPermission(
            appContext,
            Manifest.permission.RECORD_AUDIO
        ) == PackageManager.PERMISSION_GRANTED
    }
}