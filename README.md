## コンタクトレンズ交換リマインダー

[Play Store](https://play.google.com/store/apps/details?id=io.github.rikuyu.contactlensreminder)

<img src="https://user-images.githubusercontent.com/51118613/156141489-3ee61042-3608-449b-ba34-076bd071f040.png">

### 使用技術・ツール等
- Jetpack Compose
- Dagger-Hilt
- Navigation
- Pager
- AlarmManager
- Notification

- Robolectric
- MockK
- Truth

- Firebase-Crashlytics
- Github Actions
- ktlint
- Danger

### アーキテクチャ

![diagram](https://user-images.githubusercontent.com/51118613/156873435-3e451ec1-1f0b-4167-8cc4-029cd2b2abab.svg)

[アプリ アーキテクチャ ガイド](https://developer.android.com/jetpack/guide?hl=ja) を手本にした。
### 工夫点
- 説明を見なくても操作方法の分かるUIデザイン
- 毎日アプリアイコンを変えること
- Unit Test

### デモ（廃止予定）
アプリアイコンが日付をまたいで変化する様子（Googleカレンダーのアイコンも変わっていることに注目）
<img src="https://user-images.githubusercontent.com/51118613/155189795-1d74ebf9-799e-468a-894d-1b1765cb3cc5.gif" width="270">

### 新機能 AppWidget（開発中 v2.0.0 より使用可能）
AppWidgetが日付をまたいで変化する様子

<img src="https://user-images.githubusercontent.com/51118613/156142151-bb4a9376-93d4-453d-aeaf-24ebd38c7fee.gif" width="270">

### 関連ブログ記事
- [Androidアプリアイコンを動的に変える方法](https://www.yuuuki-blog.com/2022/02/13/Android-%E3%82%A2%E3%83%97%E3%83%AA%E3%82%A2%E3%82%A4%E3%82%B3%E3%83%B3%E3%82%92%E5%8B%95%E7%9A%84%E3%81%AB%E5%A4%89%E3%81%88%E3%82%8B%E6%96%B9%E6%B3%95/)
