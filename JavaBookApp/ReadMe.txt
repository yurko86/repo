------------------------------------------
This application made to display information about book as well as reader's feedback, opinion and progress.
During first run it creates default preference file with description of Bert Bates, Kathy Sierra Head First Java: A Brain-Friendly Guide.
Application display next book data:
-Author
-Title
-Category
-Status
-Progress
-Feedback
-Rating

There is a possibility to edit data from application and save it except link to book image.
Link to image can be edited only from .ini file which located at src\preference.ini (use IMG_URL field)

------------------------------------------
how to run
------------------------------------------
1. goto src folder
2. compile java files: javac javabookapp/*.java
3. create jar file: jar cvfm javabookapp/JavaBookApp.jar manifest.txt javabookapp/*.class

------------------------------------------
to run application use command
java -jar JavaBookApp.jar

------------------------------------------