# 📱 Random User Fetcher - Android App

## 📌 Overview
This Android application fetches random user data from the [Random Data API](https://random-data-api.com) and displays user details, including name, email, avatar, and gender. The app uses the Volley library to make API calls and provides a seamless user experience with next and previous navigation.

---

## 🚀 Features
- Fetches random user details from an API.
- Displays user avatar, name, email, and gender.
- Allows navigation through users with "Next" and "Previous" buttons.
- Implements a `ProgressBar` to indicate loading.
- Displays an error message if the API request fails.

---

## 🛠️ Tech Stack
- **Language**: Java
- **UI Framework**: Android XML
- **Networking**: Volley
- **Image Loading**: Glide

---

## 📲 Screenshots
_(Add screenshots of the app interface here)_

---

## 🔧 Installation & Setup

### **1. Clone the Repository**
```sh
git clone https://github.com/yourusername/RandomUserFetcher.git
cd RandomUserFetcher
```

### **2. Open in Android Studio**
- Open **Android Studio**
- Click on **Open an existing project**
- Select the cloned repository folder

### **3. Run the App**
- Connect an Android device or start an emulator.
- Click the **Run** ▶️ button in Android Studio.

---

## 📜 Code Structure

### **Main Files & Directories**
```
RandomUserFetcher/
├── app/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/com/example/randomuserfetcher/
│   │   │   │   ├── MainActivity.java    # Main Activity File
│   │   │   ├── res/
│   │   │   │   ├── layout/
│   │   │   │   │   ├── activity_main.xml  # UI Layout File
│   │   │   │   ├── drawable/               # Icons & Drawables
│   │   │   │   ├── values/
│   │   │   │   │   ├── strings.xml          # String Resources
```

### **API Endpoint Used**
The app fetches random user data from:
```
https://random-data-api.com/api/users/random_user?size=80
```

---

## 🎯 How It Works
1. When the app starts, it fetches 80 random users from the API.
2. The first user’s details are displayed.
3. Clicking **Next** or **Previous** updates the displayed user.
4. A **ProgressBar** is shown while fetching data.
5. If the API request fails, a Toast message is displayed.

---

## 📦 Dependencies
Add these dependencies in `build.gradle (Module: app)`:
```gradle
dependencies {
    implementation 'com.android.volley:volley:1.2.1'
    implementation 'com.github.bumptech.glide:glide:4.15.1'
    annotationProcessor 'com.github.bumptech.glide:compiler:4.15.1'
}
```

---

## 📂 Android Intern Assignment: User Information App

### 📌 Problem Statement
Your task is to develop a simple React Native application. This app will display user information fetched from a public API. The goal is to showcase your skills in React Native development, particularly in handling data from APIs and presenting it in a user-friendly interface.

### 📜 Assignment Instructions
1. Create a React Native Mobile App that resembles the below image. Feel free to adapt the design based on your understanding of the problem mentioned below.
2. **Screen Fields**: Your app screen should display the following user information fields:
   - id
   - uid
   - password
   - first_name
   - last_name
   - username
   - email
   - Avatar (user image)
3. **Navigation Buttons**:
   - **Previous Button**: Navigates to the screen showing the previous user's details.
   - **Next Button**: Navigates to the screen showing the next user's details.
4. **User Data Display**: The app should be capable of displaying data for a total of 80 users, showing only one user's data per screen.

### ⚙️ Technical Details
- **API Usage**: Fetch user data from Random Data API.
  - The `size` parameter in the URL specifies the number of users to fetch.
  - Example:
    - `https://random-data-api.com/api/users/random_user?size=1` fetches data for one user.
    - `https://random-data-api.com/api/users/random_user?size=2` fetches data for two users.
    - `https://random-data-api.com/api/users/random_user?size=3` fetches data for three users, and so on.

### 📤 Submission Guidelines
- Submit your work as a GitHub repository link.
- Include a `README.md` file in the repository with:
  - A brief overview of the project.
  - Instructions on how to run the application locally.
  - Any additional notes or considerations relevant to the project.

### 🏆 Evaluation Criteria
- **Code Quality**: Clean, organized, and well-commented code.
- **React Native Usage**: Efficient use of React Native components and navigation.
- **Data Handling**: Successful fetching, processing, and display of API data.
- **UI Design**: Aesthetically pleasing and responsive user interface.
- **Error Handling**: Proper management of edge cases and potential errors.

---

## 🛠️ Future Enhancements
- Add a **search** functionality to filter users.
- Implement **pagination** instead of fetching all users at once.
- Improve **UI design** using Material Design components.

---

## 🤝 Contribution
Contributions are welcome! Feel free to submit a pull request.

---

## 📄 License
This project is licensed under the MIT License.

---

## 💬 Contact
For any inquiries, reach out to me via:
- GitHub: [yourusername](https://github.com/yourusername)
- LinkedIn: [your-profile](https://linkedin.com/in/your-profile)

