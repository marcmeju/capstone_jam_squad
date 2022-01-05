SELECT * from not_booking.Data_Dump;

-- -----------------------------------------------------
-- Inserting into Location table
-- -----------------------------------------------------
INSERT INTO not_booking.Location
(location_city, location_state, location_address, location_zipcode)
SELECT distinct CITY, STATE, ADDRESS, Zipcode FROM not_booking.Data_Dump;


select * from not_booking.Location;

-- -----------------------------------------------------
-- Inserting into Contact table
-- -----------------------------------------------------

insert into not_booking.Contact
(phone, email, contact_type)
select distinct phone, Event_Email, "Event" from not_booking.Data_Dump;

SELECT * FROM not_booking.Contact;

-- -----------------------------------------------------
-- Inserting into Event table
-- -----------------------------------------------------

insert into not_booking.Event
(event_type, event_name, event_date, event_price, location_id, contact_id)
SELECT distinct dd.Event_type, dd.Event_Name, STR_TO_DATE(dd.Event_Date, '%m/%d/%Y'), dd.Event_Price, lct.location_id, cnt.contact_id
FROM not_booking.Data_Dump dd
inner join not_booking.Location lct
on lct.location_address = dd.Address
and lct.location_city = dd.City
and lct.location_state = dd.State
and lct.location_zipcode = dd.Zipcode
inner join not_booking.Contact cnt
on cnt.email = dd.Event_Email
and cnt.phone = dd.phone;

SELECT * FROM not_booking.Event;


SELECT * FROM not_booking.USER_DATA_DUMP;
-- -----------------------------------------------------
-- Inserting into user_role table
-- -----------------------------------------------------

insert into not_booking.user_role
(role_name)
select distinct user_role from not_booking.USER_DATA_DUMP;

-- -----------------------------------------------------
-- Inserting into "admin" value in  user_role table
-- -----------------------------------------------------

insert into not_booking.user_role
(role_name)
value
('admin');

select * from not_booking.user_role;

-- -----------------------------------------------------
-- Inserting into user table
-- -----------------------------------------------------
insert into not_booking.user
(user_name, password_hash, user_role_id)
select udd.user_name, '$2a$10$ntB7CsRKQzuLoKY3rfoAQen5nNyiC/U60wBsWnnYrtQQi8Z3IZzQa', ur.user_role_id
 from not_booking.USER_DATA_DUMP udd
inner join not_booking.user_role ur
on ur.role_name = udd.user_role;

SELECT * from not_booking.user;

SELECT * from not_booking.USER_DATA_DUMP;


-- -----------------------------------------------------
-- Inserting into Contact table
-- -----------------------------------------------------
insert into not_booking.Contact
(phone, email, contact_type)
select distinct phone, email, "Customer" from not_booking.USER_DATA_DUMP;

SELECT * FROM not_booking.Contact;

-- -----------------------------------------------------
-- Inserting into Customer table
-- -----------------------------------------------------
insert into not_booking.Customer
(customer_first_name,customer_last_name,user_id,contact_id)
select udd.first_name, udd.last_name,usr.user_id, cnt.contact_id
from not_booking.USER_DATA_DUMP udd
inner join not_booking.Contact cnt
on cnt.email = udd.email
and cnt.phone = udd.phone
inner join not_booking.user usr
on usr.user_name = udd.user_name;

SELECT * from not_booking.Customer;

-- -----------------------------------------------------
-- Inserting into Tier table
-- -----------------------------------------------------
insert into not_booking.Tier
(tier_name, tier_desc)
values
('Tier 1', 'Express - Two day Package'),
('Tier 2', 'Elite -  Three day Package'),
('Tier 3', 'VIP -  Customize your Package');

SELECT * from not_booking.Tier;



