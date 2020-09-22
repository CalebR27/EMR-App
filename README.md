# EMR-App
Sample Electronic Medical Records System connected to a backend database using a JDBC driver. Backend database is running on AWS RDS.

There are three tables, each with their own fields. They are as follows:

Patients:
Name
Age
DOB
Sex
PID
Notes


Vitals:
Height (cm)
Weight (lbs)
BPM
Temperature (Farenheit)
PID
BP (string: "##/##")
Position


Medication:
Active Medication (Name will contain unit [mg, mL, etc.])
Dose Freq
PID
Route
Time
MID

Application was created using Swing
3 different frames:
Main application (divided into three panels)
  Left: Directory of patients
  Middle: Patient Information on top, vitals on the bottom
  Right: Medication

Add Patient (where new patients are added into the database)
Add Medication (where new medication is added into the database)

**********Requirements**********

1.Ensure you are running on JDK14 or higher.

2. Requires JDBC driver to run. This allows the app to make API calls and connect to the backend database.
  a. Extract sqljdbc_8.4.0.0_enu as: C:\Program Files\Microsoft JDBC DRIVER 8.4 for SQL Server\
