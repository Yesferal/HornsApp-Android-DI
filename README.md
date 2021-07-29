[![](https://jitpack.io/v/Yesferal/hada.svg)](https://jitpack.io/#Yesferal/hada)
# HADA: HornsApp Dependency Accessing
Hada is a whole Kotlin container, which you can use very simple on JVM or Android.
You can define all the instance that you will need in your app and manage them in a single place.

## Getting Started
In your app gradle, you should add the repository:
 ```kotlin
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
 ```
 and the dependency:
 ```kotlin
 implementation 'com.github.Yesferal:hada:1.1.1'
 ```

Then, you should instance Hada Container in your main class, so any class could access it without any problem.

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

## How to use: Register a simple dependency
 Some times the code is better than words, to register any dependency you just need:

 ```kotlin
 container register Factory<String> { "Message: Hada Container is our first option !" }

 container register Singleton<MainRepository> {
    MainRepository(message = container.resolve())
 }

 class MainRepository(private val message: String) {
    fun getMessage() = message
 }
 ```

 ## How to use: Resolve dependency without Tag
 To resolve any dependency you just need:

 ```kotlin
 private val mainRepository: MainRepository = container.resolve()
 ```

 ## How to use: Register dependency using Tag
 To register a dependency using a Tag you just need to define the tag as a string. You can use this feature if you have dependencies that implement the same class, for example String:
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

 ## How to use: Resolve dependency using Tag
 To resolve a dependency using a Tag you just need to specify the tag that you used to register it:
 ```kotlin
 val message: String = container.resolve(tag = "Title")
 val description: String = container.resolve(tag = "Description")
 ```

 ## How to use: Register dependency with Parameters
 To register a dependency with parameters you just need to define the parameter structure as `name: Type`.
 You can use this feature if you have dependencies that need some parameters obtained at runtime:
 ```kotlin
 container register Factory(tag = "Parameter") { (string: String) ->
     string
 }
 ```

 ## How to use: Resolve dependency with parameters
 To resolve a dependency with parameters you just need to specify them inside the `Parameters`'s constructor:
 ```kotlin
 val description: String = container.resolve(params = Parameters("Description: Inserting value as Parameter"))
 ```

 Now you're ready to include & use HADA library into your project!

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
