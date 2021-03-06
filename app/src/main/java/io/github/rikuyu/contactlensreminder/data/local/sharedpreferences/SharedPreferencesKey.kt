package io.github.rikuyu.contactlensreminder.data.local.sharedpreferences

object SharedPreferencesKey {

    // SharedPreferenceのデータ保存ファイル名
    const val SHARED_PREFERENCE_FILE_NAME = "profile_data"

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

    // 通知時刻（時）
    const val STORED_NOTIFICATION_TIME_HOUR = "notification_time_hour"

    // 通知時刻（分）
    const val STORED_NOTIFICATION_TIME_MINUTE = "notification_time_minute"

    // コンタクトレンズの度数を決める項目を表示するかどうか
    const val STORED_IS_SHOW_CONTACT_LENS_POWER_SECTION = "is_show_contact_lens_power_section"

    // 左目の度数
    const val STORED_LEFT_CONTACT_LENS_POWER = "left_contact_lens_power"

    // 右目の度数
    const val STORED_RIGHT_CONTACT_LENS_POWER = "right_contact_lens_power"

    // レンズ交換日
    const val STORED_CONTACT_LENS_EXCHANGE_DAY = "contact_lens_exchange_day"

    // UUID
    const val STORED_UUID = "uuid"

    // 初めてリマインダーを使用するかどうか
    const val STORED_IS_FIRST_USE = "is_first_use"

    // OnBoarding 画面を表示するかどうか（AppWidget用）
    @Deprecated("1回目のON_BOARDING")
    const val STORED_IS_SHOW_ON_BOARDING = "is_show_on_boarding"

    // ダークテーマを使うかどうか
    const val STORED_IS_DARK_THEME = "is_dark_theme"

    // テーマカラーを使うかどうか
    const val STORED_IS_THEME_COLOR = "contact_lens_reminder_theme_color"

    // 通知が鳴ったか（再起動時の再予約に使用）
    const val STORED_IS_EXECUTE_NOTIFICATION = "is_execute_notification"
}