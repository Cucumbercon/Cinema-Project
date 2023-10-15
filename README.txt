In order to run this program, there are some prerequistes:
Firstly, it is assumed that node.js is installed and Java 17 is installed.
It is recommended to run this in Visual Studio Code program.
If running in the VS Code program, please install the Extension Pack for Java.
In the terminal, run the following commands, if any of these are not installed:

npm install react-router-dom
npm install react-slick
npm install react-modal
npm install react-player

Once the prerequistes are finished, the Java file in the following path should be run:
server/src/main/java/edu/uga/cinemabooking/CinemaBookingApplication.java
As this Java file is running, go into the terminal, change directory to web by using the following command:
cd web
Once inside the web directory, use the following commands to run the website on localhost:
npm run build
npm run start

Once these are executed, you will see the homepage for the movies.

In order to navigate through different pages, please refer to the following path:
web/src/App.js

Within App.js, right under the import functions will the function App(), with listing of all the pages that are implemented.
At default, <MovieBooking /> should be uncommented.
To switch to a different page, comment attribute and uncomment the other, then compile again (ctrl-s if on Visual Studio) to switch pages.