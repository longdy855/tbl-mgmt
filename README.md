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

:memo: **<em> Note: Each task, require back up table script, back up data, make new script and store as sql files.</em>**

### 2. Separate DB and Schema for each project
To manage our company's numerous databases and schemas across various projects, we need a function to select the specific database and schema to be used for each task.
### 3. Store table info
Table info such as Table Physical Name, Table Logical Name, Column Physical Name, Column Logical Name, Data Type, Word, Term, Description to explain each item.
Store code and description of a column.
### 4. Generate all tables to an excel
At the end of the project, we need to provide table info to the customer. With this function it will help us to make the document accurately and fast.
### 5. Integrate with Portal system
In portal, we have security and user management functions. We can utilize those functions.
