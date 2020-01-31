# Simple-Notes

- Written in Kotlin
- Follow MVVM
- Uses Android architecture components

Here's what it looks like:

![Output sample](https://j.gifs.com/nxX21P.gif)

## Architecture
MVVM is an architecture pattern that takes advantage of user device capabilities and browser memory to improve application performance

MVVM stands for Model, View, ViewModel.

- Model: This holds the data of the application. It cannot directly talk to the View. Generally, it’s recommended to expose the data to the ViewModel through Observables.
- View: It represents the UI of the application devoid of any Application Logic. It observes the ViewModel.
- ViewModel: It acts as a link between the Model and the View. It’s responsible for transforming the data from the Model. It provides data streams to the View. It also uses hooks or callbacks to update the View. It’ll ask for the data from the Model.


## Architecture componets that used are
- LiveData: it's an observable data holder class. Unlike a regular observable, LiveData is lifecycle-aware, meaning it respects the lifecycle of other app components, such as activities, fragments, or services. This awareness ensures LiveData only updates app component observers that are in an active lifecycle state.
- Room: it's an ORM (object relational mapper) for SQLite database in Android. It is part of the Architecture Components released by Google. At its core, all the code that you write related to Room will eventually be converted to SQLite code. Room allows you to create and manipulate database in Android more quickly. See it as an abstraction layer on top of inbuilt SQLite database.
-ViewModel: it's a class that is responsible for preparing and managing the data for an Activity or a Fragment. It also handles the communication of the Activity / Fragment with the rest of the application (e.g. calling the business logic classes).
- Repository: Used to manage multiple data sources.

[![](https://i.imgur.com/UQYoZQQ.png)]()



