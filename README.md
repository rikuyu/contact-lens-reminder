## コンタクトレンズ交換リマインダー

[Play Store](https://play.google.com/store/apps/details?id=io.github.rikuyu.contactlensreminder)

<img src="https://user-images.githubusercontent.com/51118613/154684861-5367f4d4-1ef6-4eee-8919-cc09c37d7b96.jpg" width="800">

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
