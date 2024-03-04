Asset Management System

Create spring MVC/boot project based on the following details.  
  
stack  
java: 8 latest  
  
DB: mysql?/ms-sql?, hibernate tools for db connections with latest version,  
IDE: Eclipse,  
web server: tomcat  
front end: 508 compliance framework like assets framework? or latest (Ref:https://design.cms.gov/?theme=core)  
or jquery UI? ([https://jqueryui.com/](https://jqueryui.com/))  
or bootstrap? ([https://getbootstrap.com/](https://getbootstrap.com/))  
2. Project  

DB :  
Tables:  
table_Role_master (Admin, regular user, report user)  
table_Users (login credentials bind with assinged role)  
table_asset_category(id, cat_name, cat_description, cat_status)  
table_Asset (ID, category_ID, name, serial number, description, expiration date, status, and created/updated user and dates)  
  
logic:  
1. only admin user - able to add/modify asset_categories  
2. regular user - should able to add/modify assets  
3. report user - only access to reports  
  
run and demonstrate above project in working condition