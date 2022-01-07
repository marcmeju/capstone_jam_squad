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

select * from not_booking.user_role;
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

SELECT * from not_booking.PACKAGE_DATA_DUMP;

-- -----------------------------------------------------
-- Inserting into Package table
-- -----------------------------------------------------
insert into not_booking.Package
(package_name,tier_id,package_price)
select pdd.Package_Name, tr.tier_id, sum(pdd.Event_Price)
 from not_booking.PACKAGE_DATA_DUMP pdd
inner join not_booking.Tier tr
on tr.tier_name = pdd.Tier
group by pdd.Package_Name, tr.tier_id;

SELECT * FROM not_booking.Package;
-- -----------------------------------------------------
-- Adding name "combo" to express 6 packages
-- -----------------------------------------------------

update not_booking.Package
set package_name = concat(package_name, ' - Combo')
WHERE package_name like '% Express 6'
and package_id<>-1;

update not_booking.Package
set package_name = concat(package_name, ' - Combo')
WHERE package_name = 'Chicago Express 4'
and package_id<>-1;


-- -----------------------------------------------------
-- Removing space and - in Newyork Elite packages
-- -----------------------------------------------------
update not_booking.Package pkg
set pkg.package_name =  concat(substring(pkg.package_name, 1,14), substring(pkg.package_name, 17,1)) 
WHERE package_name like 'NewYork Elite%'
and package_id<>-1;


-- -----------------------------------------------------
-- Fixing the space issue in the Evenr_type column`
-- -----------------------------------------------------
UPDATE not_booking.Event  set event_type = TRIM(event_type) where event_id<>-1;

-- -----------------------------------------------------
-- Inserting into Package_event table
-- -----------------------------------------------------
insert into not_booking.Package_event
(event_id, package_id)
select eve.event_id, pckg.package_id from not_booking.PACKAGE_DATA_DUMP pdd
inner join not_booking.Package pckg
on pckg.package_name = pdd.Package_Name
inner join not_booking.Event eve
on eve.event_name = pdd.Event_Name
and eve.event_type = pdd.Event_Type
and eve.event_price = pdd.Event_Price
and eve.event_date = STR_TO_DATE(pdd.Event_Date, '%m/%d/%Y');


SELECT * FROM not_booking.Package_event;

-- -----------------------------------------------------
-- Changing the months of the event date
-- -----------------------------------------------------

select event_date, DATE_ADD(event_date, INTERVAL 3 MONTH) from not_booking.Event;

UPDATE not_booking.Event  set event_date = DATE_ADD(event_date, INTERVAL 6 MONTH) where event_id<>-1;

select * from not_booking.Event;

delete from not_booking.Location where location_id = 1;