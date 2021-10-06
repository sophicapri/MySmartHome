
#### Technical test project

My Smart Home
=================

An app that lets the user control their connected devices.

<img src="https://github.com/sophicapri/MySmartHome/blob/main/screenshots/full_circle_gif2.gif" align="right" width="40%">

Technical Stack and Libraries Used
--------------
  * MVVM Architecture
  * AndroidX 
  * [Android KTX][2] 
  * [Navigation][14] 
  * [View Binding][11] 
  * [Lifecycles][12] - To create a UI that automatically responds to lifecycle events.
  * [LiveData][13] - Build data objects that notify views when the underlying database changes.
  * [ViewModel][17] - To store UI-related data that isn't destroyed on app configuration changes.
  * [Room][16] - Local database.
  * [Hilt][92]: - For dependency injection
  * [Kotlin Coroutines][91] - For managing background threads with simplified code and reducing needs for callbacks
  * [Preferences DataStore][8] - To store simple data. "DataStore uses Kotlin coroutines and Flow to store data asynchronously, consistently, and transactionally."
  * [RxKotlin/RxJava][7] - In this project I've used it to handle the call to the remote data. But I could've used Coroutines Flow for more consistency in the entire project.
  * [Retrofit][5] - REST client for web services calls.
  * [Moshi][9] - JSON library to parse remote data.
  * [MockK][20] - Mocking library for easier and more readable tests.
  * [Lottie][21] - Animations library.
  * [LeakCanary][33] - A memory leak detection library.

[0]: https://developer.android.com/jetpack/components
[2]: https://developer.android.com/kotlin/ktx
[4]: https://developer.android.com/training/testing/
[11]: https://developer.android.com/topic/libraries/view-binding/
[12]: https://developer.android.com/topic/libraries/architecture/lifecycle
[13]: https://developer.android.com/topic/libraries/architecture/livedata
[14]: https://developer.android.com/topic/libraries/architecture/navigation/
[16]: https://developer.android.com/topic/libraries/architecture/room
[17]: https://developer.android.com/topic/libraries/architecture/viewmodel
[30]: https://developer.android.com/guide/topics/ui
[34]: https://developer.android.com/guide/components/fragments
[91]: https://kotlinlang.org/docs/reference/coroutines-overview.html
[92]: https://developer.android.com/training/dependency-injection/hilt-android
[5]: https://github.com/square/retrofit
[7]: https://github.com/ReactiveX/RxKotlin
[8]: https://developer.android.com/topic/libraries/architecture/datastore
[9]: https://github.com/square/moshi
[20]: https://github.com/mockk/mockk
[21]: https://github.com/airbnb/lottie-android
[33]: https://square.github.io/leakcanary/
