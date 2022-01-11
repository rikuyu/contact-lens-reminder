package com.example.contactlensreminder.presentation.util

object SharedPreferencesKey {

    // SharedPreferenceのデータ保存ファイル名
    const val SHARED_PPREFERENCE_FILE_NAME = "profile_data"

    // コンタクトレンズの種類（2Weeks, 1Month, Other）
    const val STORED_CONTACT_LENS_TYPE = "contact_lens_type"

    // Other だったときの日数
    const val STORED_CONTACT_LENS_PERIOD = "contact_lens_period"

    // 通知をするかどうか
    const val STORED_IS_USE_NOTIFICATION = "is_use_notification"

    // 通知するのは前日か当日か
    const val STORED_NOTIFY_DAY = "notify_day"
}