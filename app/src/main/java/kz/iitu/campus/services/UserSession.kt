package kz.iitu.campus.services

import android.content.Context


object UserSession {

    private const val ACCESS_TOKEN = "access"
    private const val REFRESH_TOKEN = "refresh"
    private const val PREFERENCE_FILE_NAME = "USER_INFO"

    private fun getPreferences(context: Context) =
        context.getSharedPreferences(PREFERENCE_FILE_NAME, Context.MODE_PRIVATE)

    fun saveUserDetails(context: Context, access: String, refresh: String) {
        val editor = getPreferences(context).edit()
        editor
            .putString(ACCESS_TOKEN, access)
            .putString(REFRESH_TOKEN, refresh)
            .apply()
    }

    fun getUserToken(context: Context) =
        getPreferences(context).getString(ACCESS_TOKEN, "")

    fun isLoggedIn(context: Context): Boolean =
        getUserToken(context)?.isNotEmpty() ?: false

    fun logout(context: Context) =
        getPreferences(context).edit().clear().commit()
}