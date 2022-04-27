## コンタクトレンズ交換リマインダー

:gift: [Play Store](https://play.google.com/store/apps/details?id=io.github.rikuyu.contactlensreminder)

<img src="https://user-images.githubusercontent.com/51118613/156141489-3ee61042-3608-449b-ba34-076bd071f040.png">

### :wrench: 使用技術・ツール等
* Main
    * Jetpack Compose
    * Dagger-Hilt
    * Navigation
    * Pager
    * AlarmManager
    * Notification
    * AppWidgetProvider
* Test
    * Robolectric
    * MockK
    * Truth
* Other
    * Firebase-Crashlytics
    * Github Actions
    * In-App-Update
    * In-App-Review
    * ktlint
    * Danger

### :house: アーキテクチャ

![diagram](https://user-images.githubusercontent.com/51118613/156873435-3e451ec1-1f0b-4167-8cc4-029cd2b2abab.svg)

[アプリ アーキテクチャ ガイド](https://developer.android.com/jetpack/guide?hl=ja) を手本にした。

### :seedling: 工夫点

- 説明を見なくても操作方法の分かる最高のUIデザイン
- アプリウィジェットを活用した最高のUX
- Unit Test（自動化）

### :iphone:  新機能 AppWidget（開発中 v2.0.0 から使用可能）

AppWidgetが日付をまたいで変化する様子

<img src="https://user-images.githubusercontent.com/51118613/156142151-bb4a9376-93d4-453d-aeaf-24ebd38c7fee.gif" width="270">

### :last_quarter_moon: ライト/ダークモード

| ライトモード | ダークモード |
|:---:|:---:|
| <img src="https://user-images.githubusercontent.com/51118613/158942189-20ff8ba8-8872-4ddd-84bc-9efd4c3153eb.jpg" width=220 > | <img src="https://user-images.githubusercontent.com/51118613/158942244-b96f98c1-c63c-4388-8ded-c8a4f6e89d12.jpg" width=220 > |

### :art: テーマカラー変更機能

<img src="https://user-images.githubusercontent.com/51118613/159910626-b127a561-df51-4dc8-be69-093124730e9e.jpg" >

### :earth_asia: 多言語対応
- 日本語
- 英語
- 韓国語
- 中国語

### :memo: 関連ブログ記事

- [Androidアプリアイコンを動的に変える方法](https://www.yuuuki-blog.com/2022/02/13/Android-%E3%82%A2%E3%83%97%E3%83%AA%E3%82%A2%E3%82%A4%E3%82%B3%E3%83%B3%E3%82%92%E5%8B%95%E7%9A%84%E3%81%AB%E5%A4%89%E3%81%88%E3%82%8B%E6%96%B9%E6%B3%95/)
