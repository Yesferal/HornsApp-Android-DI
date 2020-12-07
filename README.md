# HADA: HornsApp Dependency Accessing
Hada is whole Kotlin container, which you can use very simple on JVM or Android. You can create an only instance for your app and manage all your dependencies.   

## Getting Started
In the app gradle add the dependency:

```
implementation 'com.yesferal.hornsapp:hada:1.0.1'
```

Then, you should instance Hada Container in your main class, so any class cloud access from anywhere of your project.

```
val container: Container = Hada()
```

### Android
In case of Android, you can create an instance of Container in your Application class.

```
class MyApp: Application() {
    val container: Container = Hada()
}
```

Then, you could used it in any Activity:

```
val container = (application as MyApp).container
```

## How to use: Register dependency
 Some code is better than words, so, to register any dependency:

 ```
 container register Factory<String> { "Hada Container: First Option !" }
 container register Singleton<MainRepository> {
    MainRepository(container.resolve())
 }

 class MainRepository(private val message: String) {
    fun getMessage() = message
 }
 ```

 or

 ```
 container.register(String::class.java, Singleton {
    "Hada Container: Second Option !"
 })
 container.register(MainRepository::class.java, Singleton {
    MainRepository(container.resolve())
 })

 class MainRepository(private val message: String) {
    fun getMessage() = message
 }
 ```

 ## How to use: Resolve dependency
 To resolve any dependency you only need:

 ```
 private val mainRepository: MainRepository = container.resolve()
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
