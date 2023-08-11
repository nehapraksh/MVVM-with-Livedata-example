# Project Technical Details
1. Language - Kotlin
2. Networking Library - Retrofit2
3. Android Architecture Component - MVVM with Livedata(Model, View, ViewModel)
4. Unit Testing tool - Mockito and JUnit
5. Image Loading Framework - Glide

# Features in the App
1. Show Car List with details like - Manufacturer, Model, Image and Price
2. Different screen view on Loading, Success, Empty and Error Result.
3. Pull to refresh (to refresh the list)
4. Responsive UI - layout well on any screen sizes(not customized for tablets)

# Focused Area
1.  App Architecture pattern - MVVM pattern has been used in the app. It is clean architecture and allows to write all business logic
    in ViewModel Class which makes our app's view component (activity, fragment) clean with less code. It is independent of any view and so,
    allows to use same ViewModel in different Views. ViewModel works with DataModel to retrieve data. Moreover, this pattern is easy to test.

2.  Architecture Component - Livedata - With the MVVM, Livedata architecture component has been used because
    it is lifecycle-aware means it respects the lifecycle of the app components, such as activities, fragments, or services.
    So, in the app LiveData only updates activity observers that are in an active lifecycle state.

3.  Network Result Callbacks - Implemented callback interface to access the different states of remote response such as - Success and Failure.

4.  UI design components - ConstraintLayout is used to make the UI flexible so that it can fit to any mobile screen size.
    CardView has been used to make the UI more presentable. SwipeRefreshLayout has been used to implement pull to refresh (to refresh the list).

5.  Clean File Structure

6.  Unit Test - Unit test for ViewModel has been written which covers all the response types tests of network.