# HADA: HornsApp Dependency Accessing
Hada is a whole Kotlin container, which you can use very simple on JVM or Android.
You can define all the instance that you will need in your app and manage them in a single place.

## Getting Started
In the app gradle, you have to add the dependency:

```kotlin
implementation 'com.yesferal.hornsapp:hada:1.0.3'
```

Then, you should instance Hada Container in your main class, so any class cloud access it without any problem.

```kotlin
val container: Container = Hada()
```

### Android
In case of Android, you should create an instance of Hada in your Application class.

```kotlin
class MyApp: Application() {
    val container: Container = Hada()
}
```

So, you can use it in any Activity:

```kotlin
val container = (application as MyApp).container
```

## How to use: Register dependency without Tag
 Some code is better than words, to register any dependency you just need:

 ```kotlin
 container register Factory<String> { "Message: Hada Container is our first option !" }

 container register Singleton<MainRepository> {
    MainRepository(message = container.resolve())
 }

 class MainRepository(private val message: String) {
    fun getMessage() = message
 }
 ```

 ## How to use: Register dependency using Tag
 To register a dependency using a Tag you just need to define it as a unique string:

 ```kotlin
 container register Factory<String>(tag = "Title") { "Title: Hada Container" }
 container register Factory<String>(tag = "Description") { "Description: This is a demo app, which implement Hada Container. This strings are injected by Hada using a Tag, in order to Hada know which one to use in each case." }

 container register Singleton<MainRepository> {
     MainRepository(
         message = container.resolve(tag = "Title"),
         description = container.resolve(tag = "Description")
     )
 }

 container register Factory<MainContract.ActionListener> {
     MainPresenter(mainRepository = container.resolve())
 }
 ```

 ## How to use: Resolve dependency without Tag
 To resolve any dependency you just need:

 ```kotlin
 private val mainRepository: MainRepository = container.resolve()
 ```

 ## How to use: Resolve dependency using Tag
 To resolve a dependency using a Tag you just need to specify the unique tag that you used to register it:
 ```kotlin
 val message: String = container.resolve(tag = "Title")
 val description: String = container.resolve(tag = "Description")
 ```

 ## Demo Projects
 In this Repository you have an Android Demo that explain how to use it.

 ## License
 ```
 Copyright 2020 HornsApp Contributors

 Licensed under the Apache License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at

      https://www.apache.org/licenses/LICENSE-2.0

 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
 ```
