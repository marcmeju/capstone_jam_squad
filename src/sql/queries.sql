SELECT distinct pkg.package_name, pkg.package_price, tr.tier_name, tr.tier_desc
FROM not_booking.Package pkg
INNER JOIN not_booking.Tier tr
on tr.tier_id = pkg.tier_id;


SELECT pckg.package_name, eve.event_name, eve.event_date, cust.customer_first_name, cust.customer_last_name
 FROM not_booking.Booking bkng
 inner join not_booking.Package pckg
on pckg.package_id = bkng.package_id
inner join not_booking.Customer cust
on cust.customer_id = bkng.customer_id
inner join not_booking.Contact cnt
on cnt.contact_id = cust.contact_id
inner join not_booking.Package_event pckgeve
on pckgeve.package_id = pckg.package_id
inner join not_booking.Event eve
on eve.event_id = pckgeve.event_id;




SELECT * FROM not_booking.Event 
where event_name='Field Museum';

UPDATE not_booking.Event  set event_type = TRIM(event_type) where event_id<>-1;


select * from not_booking.Location
where location_id=1;


select * from not_booking.Contact
where contact_type='Customer'
and contact_id=67;


select pkg.package_name , eve.event_name, eve.event_date, lctn.location_city, lctn.location_state, cnt.email, cnt.phone
from not_booking.Package pkg
inner join not_booking.Package_event pkeve
using(package_id)
inner join not_booking.Event eve
using(event_id)
inner join not_booking.Location lctn
using(location_id)
inner join not_booking.Contact cnt
using(contact_id)
where pkg.package_id = 2


SELECT * FROM not_booking.Package

select * from not_booking.Booking

