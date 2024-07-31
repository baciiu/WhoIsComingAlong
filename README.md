# WhoIsComingAlong
Group Project for SS24 Course App Development @ Uni Klagenfurt
Members: Bäck, Baciu, Drucks, Vospernik
## Project Description
- Multi-user communication tool to make appointments on short notice
  ( eg. go for lunch during work break)
- For a limited number of group participants.
- Communication and status updates between group members are instantaneously transmitted.

## Admin User
- Username: `admin`
- Password: `admin123`

## Room and Hilt
#### General settings
- Dependencies: have to be added to libs.versions.toml and build.gradle.kts project and module level
- Database: sets up the Room database
- Hilt modules: to provide the database and DAO
- Application class: to initialize Hilt, also set in Android manifest

#### Database tables
For each of the tables, the following classes have to be implemented:
- Entity: defines tables of the database
- DAO: data access objects (caveat: suspend functions for all non-query functions)
- Repository: to abstract data operations

## UI, Screens and ViewModels
#### Usage of database tables in the ScreensViewModel and Composable functions
- ViewModel: to interact with the repository (more than one ViewModel can interact with one Repository)
- Screens: contain the Composable functions: create the UI, to interact with the ViewModel

#### UI
The UI of the app is built using Jetpack Compose, following a modern, user-friendly design. Each screen is implemented as a Composable function that interacts with its respective ViewModel. Below are the details of each screen:

Login Screen
- Purpose: Allows users to log in.
- UI Elements: Username and Password fields, Login button, Navigation to Sign Up screen.
- Logic: Authenticates the user against the database.

Sign Up Screen
- Purpose: Allows new users to register.
- UI Elements: Fields for First Name, Last Name, Date of Birth, Company, Department, Email, Nick Name, Password, Sign Up button.
- Logic: Validates input and creates a new user in the database.

Central Screen (Start Screen)
- Purpose: Main screen showing current appointments and providing access to other features.
- UI Elements: Profile section, Appointments list, Navigation buttons for managing groups and restaurants, Logout button.
- Logic: Fetches and displays appointments, handles user navigation.

Profile Screen
- Purpose: Displays and allows editing of the user profile.
- UI Elements: Fields for First Name, Last Name, Nick Name, Date of Birth, Email, Company, Department, Edit/Save button.
- Logic: Fetches and updates user data in the database.

Group Screen
- Purpose: Allows creation and management of groups.
- UI Elements: Group Name field, User selection list, Create Group button, List of existing groups with Edit/Delete options.
- Logic: Handles creation, update, and deletion of groups, as well as adding/removing users from groups.

Edit Group Screen
- Purpose: Allows editing of an existing group.
- UI Elements: Group Name field, User selection list, Update Group button.
- Logic: Fetches group data for editing, updates group information in the database.

All Appointments Screen
- Purpose: Displays all appointments for the user.
- UI Elements: List of appointments, Navigation to detailed appointment view.
- Logic: Fetches and displays appointments from the database.

Appointment Screen
- Purpose: Displays details of a specific appointment.
- UI Elements: Appointment details (name, date, time, location, participants), Toggle for user's participation status.
- Logic: Fetches appointment details and updates user's participation status.

Restaurant Screen
- Purpose: Allows creation and management of restaurants.
- UI Elements: Restaurant Name, Address, Latitude, Longitude fields, Create Restaurant button.
- Logic: Validates input and creates a new restaurant in the database.

Edit Restaurant Screen
- Purpose: Allows editing of an existing restaurant.
- UI Elements: Restaurant Name, Address, Latitude, Longitude fields, Update Restaurant button.
- Logic: Fetches restaurant data for editing, updates restaurant information in the database.

Styling
- Theme: Custom theme using Material3 components.
- Colors: Consistent color scheme using primary, secondary, and accent colors.
- Typography: Consistent text styles for headings, body text, and input fields.

Design
The app features a consistent design language across all screens, using a specific color palette and styling elements to maintain a cohesive look and feel. The primary colors used are red, gray, black, and white, which are applied to buttons, text fields, and other UI components. The design aims for clarity and simplicity, ensuring that users can navigate and interact with the app easily.

