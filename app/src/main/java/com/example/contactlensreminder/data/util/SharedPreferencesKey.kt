package com.example.contactlensreminder.data.util

object SharedPreferencesKey {

    // SharedPreferenceのデータ保存ファイル名
    const val SHARED_PPREFERENCE_FILE_NAME = "profile_data"

    // コンタクトレンズの種類（2Weeks, 1Month, Other）
    const val STORED_CONTACT_LENS_TYPE = "contact_lens_type"

    // Other だったときの日数
    const val STORED_CONTACT_LENS_PERIOD = "contact_lens_period"

    // コンタクトレンズ使用中かどうか
    const val STORED_IS_USING_CONTACT_LENS = "is_using_contact_lens"

    // 経過日数
    const val STORED_CONTACT_LENS_ELAPSED_DAYS = "contact_lens_elapsed_days"

    // 通知をするかどうか
    const val STORED_IS_USE_NOTIFICATION = "is_use_notification"

    // 通知するのは前日か当日か
    const val STORED_NOTIFICATION_DAY = "notification_day"

    // 通知時刻
    const val STORED_NOTIFICATION_TIME = "notification_time"

    // 左目の度数
    const val STORED_LEFT_CONTACT_LENS_POWER = "left_contact_lens_power"

    // 右目の度数
    const val STORED_RIGHT_CONTACT_LENS_POWER = "right_contact_lens_power"
}