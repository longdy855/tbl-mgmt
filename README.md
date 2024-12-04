# DB Table Management
## Functions
### 1. Table List
A screen to display all tables within a specific schema.
### 2. Table Detail
A screen to view detailed information about a specific table. It also allows for the generation of MyBatis scripts and Excel file downloads.

## Next steps
### 1. Add an alter column function
The app should be table to 
1. Remove a column
1. Change column name
2. Change Logical column name
3. Change Datatype
4. Change Primary key
5. Add new cloumns at the bottom of the table
6. Add new columns to any sepcific order
7. Add a function to store changed history

:memo: **<em>Each task involves creating a backup table script, exporting data to an Excel file, generating a new script, and storing it as an SQL file.</em>**

### 2. Separate DB and Schema for each project
To manage our company's numerous databases and schemas across various projects, we need a function to select the specific database and schema to be used for each task.
### 3. Store table info
Table information such as physical and logical table and column names, data types, word, term, and description for each item, as well as column-specific store codes and descriptions.
### 4. Generate all tables to an excel
At project completion, we'll provide detailed table information to the client. This function will streamline the process, ensuring accurate and timely documentation.
### 5. Integrate with Portal system
We can leverage the existing security and user management features within the portal to streamline our application.
