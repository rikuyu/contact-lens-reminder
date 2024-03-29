package io.github.rikuyu.contactlensreminder.data.local.sharedpreferences

import android.content.Context
import android.content.SharedPreferences
import javax.inject.Inject

class SharedPreferencesService @Inject constructor(private val context: Context) {

    // SharedPreferencesインスタンスの取得
    private fun getSharedPreferences(): SharedPreferences {
        return context.getSharedPreferences(
            SharedPreferencesKey.SHARED_PREFERENCE_FILE_NAME,
            Context.MODE_PRIVATE
        )
    }

    // コンタクトレンズの種類
    fun getContactLensType(): Int {
        return getSharedPreferences().getInt(
            SharedPreferencesKey.STORED_CONTACT_LENS_TYPE,
            0
        )
    }

    fun saveContactLensType(lensType: Int) {
        getSharedPreferences().edit()
            .putInt(SharedPreferencesKey.STORED_CONTACT_LENS_TYPE, lensType).apply()
    }

    // コンタクトレンズの使用期間
    fun getContactLensPeriod(): Int {
        return getSharedPreferences().getInt(
            SharedPreferencesKey.STORED_CONTACT_LENS_PERIOD,
            14
        )
    }

    fun saveContactLensPeriod(period: Int) {
        getSharedPreferences().edit()
            .putInt(SharedPreferencesKey.STORED_CONTACT_LENS_PERIOD, period).apply()
    }

    // コンタクトレンズを使用中かどうか（リマインダー機能中か）
    fun getIsUsingContactLens(): Boolean {
        return getSharedPreferences().getBoolean(
            SharedPreferencesKey.STORED_IS_USING_CONTACT_LENS,
            false
        )
    }

    fun saveIsUsingContactLens(isUsingContactLens: Boolean) {
        getSharedPreferences().edit()
            .putBoolean(SharedPreferencesKey.STORED_IS_USING_CONTACT_LENS, isUsingContactLens)
            .apply()
    }

    // コンタクトレンズの残り日数
    fun getContactLensRemainingDays(): Int {
        return getSharedPreferences().getInt(
            SharedPreferencesKey.STORED_CONTACT_LENS_ELAPSED_DAYS,
            14
        )
    }

    fun saveContactLensRemainingDays(elapsedDays: Int) {
        getSharedPreferences().edit()
            .putInt(SharedPreferencesKey.STORED_CONTACT_LENS_ELAPSED_DAYS, elapsedDays).apply()
    }

    // 通知機能を使うかどうか
    fun getIsUseNotification(): Boolean {
        return getSharedPreferences().getBoolean(
            SharedPreferencesKey.STORED_IS_USE_NOTIFICATION,
            false
        )
    }

    fun saveIsUseNotification(isUseNotification: Boolean) {
        getSharedPreferences().edit()
            .putBoolean(SharedPreferencesKey.STORED_IS_USE_NOTIFICATION, isUseNotification).apply()
    }

    // 通知するのは前日か当日か
    fun getNotificationDay(): Int {
        return getSharedPreferences().getInt(
            SharedPreferencesKey.STORED_NOTIFICATION_DAY,
            0
        )
    }

    fun saveNotificationDay(notificationDay: Int) {
        getSharedPreferences().edit()
            .putInt(SharedPreferencesKey.STORED_NOTIFICATION_DAY, notificationDay).apply()
    }

    // 通知時刻（時）
    fun getNotificationTimeHour(): Int {
        return getSharedPreferences().getInt(
            SharedPreferencesKey.STORED_NOTIFICATION_TIME_HOUR,
            8
        )
    }

    fun saveNotificationTimeHour(notificationTime: Int) {
        getSharedPreferences().edit()
            .putInt(SharedPreferencesKey.STORED_NOTIFICATION_TIME_HOUR, notificationTime).apply()
    }

    // 通知時刻（分）
    fun getNotificationTimeMinute(): Int {
        return getSharedPreferences().getInt(
            SharedPreferencesKey.STORED_NOTIFICATION_TIME_MINUTE,
            0
        )
    }

    fun saveNotificationTimeMinute(notificationTime: Int) {
        getSharedPreferences().edit()
            .putInt(SharedPreferencesKey.STORED_NOTIFICATION_TIME_MINUTE, notificationTime)
            .apply()
    }

    // コンタクトレンズの度数設定項目を表示するかどうか
    fun getIsShowContactLensPowerSection(): Boolean {
        return getSharedPreferences().getBoolean(
            SharedPreferencesKey.STORED_IS_SHOW_CONTACT_LENS_POWER_SECTION,
            true
        )
    }

    fun saveIsShowContactLensPowerSection(isUseNotification: Boolean) {
        getSharedPreferences().edit()
            .putBoolean(
                SharedPreferencesKey.STORED_IS_SHOW_CONTACT_LENS_POWER_SECTION,
                isUseNotification
            ).apply()
    }

    // コンタクトレンズの度数（左）
    fun getLeftContactLensPower(): String? {
        return getSharedPreferences().getString(
            SharedPreferencesKey.STORED_LEFT_CONTACT_LENS_POWER,
            null
        )
    }

    fun saveLeftContactLensPower(lensPower: String) {
        getSharedPreferences().edit()
            .putString(SharedPreferencesKey.STORED_LEFT_CONTACT_LENS_POWER, lensPower).apply()
    }

    // コンタクトレンズの度数（右）
    fun getRightContactLensPower(): String? {
        return getSharedPreferences().getString(
            SharedPreferencesKey.STORED_RIGHT_CONTACT_LENS_POWER,
            null
        )
    }

    fun saveRightContactLensPower(lensPower: String) {
        getSharedPreferences().edit()
            .putString(SharedPreferencesKey.STORED_RIGHT_CONTACT_LENS_POWER, lensPower).apply()
    }

    // レンズ交換日
    fun getLensExchangeDate(): String? {
        return getSharedPreferences().getString(
            SharedPreferencesKey.STORED_CONTACT_LENS_EXCHANGE_DAY,
            null
        )
    }

    fun saveLensExchangeDate(exchangeDay: String) {
        getSharedPreferences().edit()
            .putString(SharedPreferencesKey.STORED_CONTACT_LENS_EXCHANGE_DAY, exchangeDay).apply()
    }

    // UUID
    fun getUuid(): String? {
        return getSharedPreferences().getString(
            SharedPreferencesKey.STORED_UUID,
            null
        )
    }

    fun saveUuid(uuid: String) {
        getSharedPreferences().edit()
            .putString(SharedPreferencesKey.STORED_UUID, uuid).apply()
    }

    // 初めてリマインダーを使用するかどうか
    fun getIsFirstUse(): Boolean {
        return getSharedPreferences().getBoolean(
            SharedPreferencesKey.STORED_IS_FIRST_USE,
            true
        )
    }

    fun saveIsFirstUse() {
        getSharedPreferences().edit()
            .putBoolean(SharedPreferencesKey.STORED_IS_FIRST_USE, false).apply()
    }

    // OnBoarding 画面を表示するかどうか
    fun getIsShowOnBoarding(): Boolean {
        return getSharedPreferences().getBoolean(
            SharedPreferencesKey.STORED_IS_SHOW_ON_BOARDING,
            false
        )
    }

    fun saveIsShowOnBoarding() {
        getSharedPreferences().edit()
            .putBoolean(SharedPreferencesKey.STORED_IS_SHOW_ON_BOARDING, false).apply()
    }

    // Dark Theme
    fun getIsDarkTheme(): Boolean {
        return getSharedPreferences().getBoolean(
            SharedPreferencesKey.STORED_IS_DARK_THEME,
            false
        )
    }

    fun saveIsDarkTheme(isDarkTheme: Boolean) {
        getSharedPreferences().edit()
            .putBoolean(SharedPreferencesKey.STORED_IS_DARK_THEME, isDarkTheme).apply()
    }

    // Theme Color
    fun getThemeColor(): String {
        return getSharedPreferences()
            .getString(SharedPreferencesKey.STORED_IS_THEME_COLOR, "blue") ?: "blue"
    }

    fun saveThemeColor(color: String) {
        getSharedPreferences().edit()
            .putString(SharedPreferencesKey.STORED_IS_THEME_COLOR, color).apply()
    }

    // 通知が鳴ったか（再起動時の再予約に使用）
    fun getIsExecuteNotification(): Boolean {
        return getSharedPreferences()
            .getBoolean(SharedPreferencesKey.STORED_IS_EXECUTE_NOTIFICATION, true)
    }

    fun saveIsExecuteNotification(isExecuteNotification: Boolean) {
        getSharedPreferences().edit()
            .putBoolean(SharedPreferencesKey.STORED_IS_EXECUTE_NOTIFICATION, isExecuteNotification).apply()
    }
}