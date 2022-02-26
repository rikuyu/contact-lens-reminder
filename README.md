## コンタクトレンズ交換リマインダー

[Play Store](https://play.google.com/store/apps/details?id=io.github.rikuyu.contactlensreminder)

<img src="https://user-images.githubusercontent.com/51118613/155189510-2be974c9-7528-45d1-9a96-bdc5cd47c081.jpg" width="500">

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
```mermaid
flowchart BT
  subgraph data
    direction BT
    Repository -->  LocalDataSource
    subgraph Repository
      ReminderRepository
      SettingRepository
    end
  end
  style data fill:#cafcdd
  style Repository fill:#caebfc
  subgraph domain
    direction LR
    subgraph UseCase
      ReminderUseCase
      SettingUseCase
    end
  end
  style domain fill:#cafcdd
  style UseCase fill:#caebfc
  subgraph UI
    direction BT
    subgraph ViewModel
      ReminderViewModel
      SettingViewModel
    end
    View --> ViewModel
  end
  style UI fill:#cafcdd
  style ViewModel fill:#caebfc
  style View fill:#caebfc
  
  UI --> domain --> data
```
[アプリ アーキテクチャ ガイド](https://developer.android.com/jetpack/guide?hl=ja) を手本にした。
### 工夫点
- 説明を見なくても操作方法の分かるUIデザイン
- 毎日アプリアイコンを変えること
- Unit Test

### デモ
アプリアイコンが日付をまたいで変化する様子（Googleカレンダーのアイコンも変わっていることに注目）

<img src="https://user-images.githubusercontent.com/51118613/155189795-1d74ebf9-799e-468a-894d-1b1765cb3cc5.gif" width="270">


### その他
- [Androidアプリアイコンを動的に変える方法](https://www.yuuuki-blog.com/2022/02/13/Android-%E3%82%A2%E3%83%97%E3%83%AA%E3%82%A2%E3%82%A4%E3%82%B3%E3%83%B3%E3%82%92%E5%8B%95%E7%9A%84%E3%81%AB%E5%A4%89%E3%81%88%E3%82%8B%E6%96%B9%E6%B3%95/)