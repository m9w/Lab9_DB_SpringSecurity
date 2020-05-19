# Lab9_DB_SpringSecurity
Task

![task](images/0.PNG)

Created by using 
- Spring Core/Boot/Data/MVC/Security
- Mustache
- JQuery
- PostgreSQL (not include to repository)
- Maven
on IntelliJ idea Ultimate (JB)

How it's work

0 Compiled project (30MB)
https://www.dropbox.com/s/3dz595do8nbejtw/lab9-1.0-SNAPSHOT.jar?dl=0

Change settings connect to DB: 

![DB_chenge](images/1.PNG)

Tables emp and dept must be created, for example using init.sql

And simply run (cmd):
java -jar lab9-1.0-SNAPSHOT.jar

1 Starting application:

![Start_run](images/2.PNG)

Completed run:

![complete_run](images/3.PNG)

2. goto localhost:8080

![complete_run](images/4.PNG)

3. Firstly registration:

![complete_run](images/5.PNG)

4 Login user account:

![complete_run](images/6.PNG)

5 Head page after sign in:

![complete_run](images/7.PNG)

6 Show first records table emp:

![complete_run](images/8.PNG)

7 set pagesize=20 and go to prelast page:

![complete_run](images/9.PNG)

8 First page shows 20 records:

![complete_run](images/10.PNG)

9 Press "Plus" and show modal form adding new records (invalid fields lighting lightcoral color)

![complete_run](images/11.PNG)

10 write valid values and add record:

![complete_run](images/12.PNG)

11 seen success result and our records on last page 
![complete_run](images/13.PNG)

12 repeat empno value: 
![complete_run](images/14.PNG)

13 try using not exist deptno

![complete_run](images/15.PNG)

result:

![complete_run](images/16.PNG)

check dept:

![complete_run](images/17.PNG)

14 change password form

![complete_run](images/18.PNG)

white invalid old password:

![complete_run](images/19.PNG)

write not equal new password:

![complete_run](images/20.PNG)

write correct password:

![complete_run](images/21.PNG)
