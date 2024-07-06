package by.eapp.mts.data.permission

interface PermissionStateProvider {
    fun checkRecordAudioPermissionIsGranted(): Boolean
}