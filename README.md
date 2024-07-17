# WhoIsComingAlong
Group Project for SS24 Course App Development @ Uni Klagenfurt
## Project Description
- Multi-user communication tool to make appointments on short notice 
( eg. go for lunch during work break)
- For a limited number of group participants. 
- Communication and status updates between group members are instantaneously transmitted.
## Room and Hilt
#### General settings
- Dependencies: have to be added to libs.versions.toml and build.gradle.kts project and module level
- Database: sets up the Room database
- Hilt modules: to provide the database and DAO
- Application class: to initialize Hilt, also set in Android manifest
#### Database tables
for each of the tables, the following classes have to be implemented:
- Entity: defines tables of the database
- DAO: data access objects
- Repository: to abstract data operations
## UI, Screens and ViewModels
#### Usage of database tables in the ScreensViewModel and Composable functions
- ViewModel: to interact with the repository (more than one ViewModel can interact with one Repository)
- Screens: contain the Composable functions: create the UI, to interact with the ViewModel

