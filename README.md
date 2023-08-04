Form List App
Form List App is an Android application that allows users to manage and view a list of forms. Users can add new forms, view existing forms, and proceed with form submission.

General Requirements
Android SDK Version: 21 and above.
Butterknife library is used for view binding.
All views have defined styles.
The app supports scrolling on relevant screens.

Features
1. Home Fragment
The Home Fragment provides a navigation menu to access other developed screens. It is a simple, empty screen.

2. Form List Screen
The Form List Screen is a Fragment that displays a list of forms saved on the device using a RecyclerView. Each item in the list contains the following information:

Profile picture
Full name
Background based on gender
When a list item is clicked, a Toast message shows the name and birthdate of the selected form.

3. Form Screen
The Form Screen is a Fragment that allows users to fill in their information for a new form submission. The following fields are required:

Toolbar with a title
Full name input field (with a hint)
Date picker for selecting the birthdate (in the format DD.MM.YYYY)
Image picker to choose and display a profile picture (using Glide or Picasso)
Custom account selection component (similar to Halkbank App)
Contract agreement: A clickable Spannable String that opens a contract page. Users can scroll to the bottom of the page to provide consent (similar to Halkbank App).
Gender selection (Radio button)
Multiple item selection from a list of 5-6 products (Checkbox): e.g., Savings Account, Current Account, etc.
Custom phone input view for collecting the phone number in the format "0 (505) 123 45 67".
All fields must be filled, and if any field is empty, appropriate error messages will be displayed. Upon successful validation, an acknowledgment page will open, showing all the entered information. The acknowledgment page is similar to the Halkbank App's reference.

On the acknowledgment page, users can save the information to the device by clicking the "Onayla" button, or they can return to the form page by clicking the "Vazgeç" button.

After the acknowledgment page, a Result Screen will open, showing the message "X Kişisini Kayıt İşleminiz Gerçekleştirilmiştir," where X represents the user's full name.

A button named "Ana Sayfaya Dön" will be present on the Result Screen, allowing users to return to the HomeFragment.
