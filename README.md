# HADA: HornsApp Dependency Accessing
Hada is whole Kotlin container, which you can use very simple on JVM or Android. You can create an only instance for your app and manage all your dependencies.   

## Getting Started
In case of Android, you can create an instance of Container in your applications class.

```
class MyApp: Application() {
    val container: Container = Hada()
}
```

Then, you could used in entire app.

## How to use: Register dependency
 Some code is better than words, so to register any dependency:

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
 private val actionListener: MainContract.ActionListener = container.resolve()
 ```

 ## Demo Projects
 In this Repository you have an Android Demo that explain how to use it.