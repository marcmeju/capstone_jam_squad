# capstone_jam_squad

Vishal, Jeya, Marcel

Potential Titles: Canoe ; Not-Bookings.com ; Keep Calm and Travel On 

Project: This project is to develop a functional application in which users would be able to plan their vacation with an agent who will serve as the admin for their location. Users will be treated as GUESTS until they login as either a Member or a VIP member each of which has different permissions. The app uses packages to keep track of events and has a customizable option for VIP members which is validated to prevent overlap of events. These events will be in one of three locations for the start: NYC, Chicago, and LA. A mock form will be supplied to the user for payment information. 

Roles and Permissions:

Admin: CRUD packages, events, and locations
User:
-	Guest: View only
-	Member: View, add different packages to cart, cancel package, edit dates, edit package
-	VIP: same as member plus can customize package and choose up to 5 events 	

Logistical Timeline:  
 
  0) A proposal will be written on 12/29 and checked for approval. After which we will proceed or edit accordingly. The following steps stand regardless of the idea of the project. 
 
1.	Git Hub : We have to make a git hub repo, and have a shared understanding of naming conventions for files. We should send pull requests to the group when we’re ready to move onto another phase so we can branch further along the process without losing work. (less than an hour on Monday)  
2.	Skeleton : The first day and a half will be dedicated to setting up the bare bones of the application. This means: 	
- creating the schema and data table       
- Java setup (Marcel)	 
- SQL (Jeya)        
- React setup w/o fetch commands (Vishal)  

Note that in this stage there will be some overlap to help everyone be involved in their interests and strengthen personal weak points.   
	(Monday to half of Tuesday) 

3.	Connecting : In this stage we will be connecting all the parts of the skeleton together and adding some meat to the java (like validations and tests). Some tangible tasks involved:	
 - SQL mapper (Vishal)	
- Controllers and Spring Injections (Marcel)	
- HTML interface (Jeya)     
- Validations and Tests (all)  
(Tuesday afternoon and half of Wednesday)

— Reassessment point — 

4.	UI and Security: JWT security in Java, Validation API. At this point we know we will need instructor help 

High Level Requirements:

Create: Admin can create packages, add locations, and add events 

Read: All roles can read.

Edit: Admin can edit prices and dates. Members and VIPs can edit date and package.

Delete: Admin can delete packages, locations, and events. Member and VIP can  

Packages are limited within a date range and validated for capacity per day. If an event is at capacity user will be prompted to choose between other options as a replacement.

Login and Logout features

Stretch Goals:

-	Add events, locations, or packages
-	Have a form for users to be able to continue as guest
-	Members and VIPs can delete account
-	Multiple admins for different locations


