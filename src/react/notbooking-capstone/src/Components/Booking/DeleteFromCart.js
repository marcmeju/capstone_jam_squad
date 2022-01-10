import { useState, useEffect } from "react";
import Errors from '../Errors';


function DeleteBooking(){

    const [bookingId, setBookingId] = useState(1);
    const [bookings, setBookings] = useState([]);
    const [showBooking, setShowBooking] = useState(true)
    const [showSuccess, setShowSuccess] = useState(true)
    const [errors, setErrors] = useState([]);
    const [missingBooking, setMissingBooking] = useState(true)

    function updateBookingId(e){
        
        setBookingId(e.target.value)
        setShowSuccess(true)
        setShowBooking(true)
        setMissingBooking(true)
        console.log(bookingId)
    }

    const getBooking = async () => {

        console.log("This is the booking id:", bookingId)
        
        try{
            const response = await fetch(`http://localhost:8080/booking/${bookingId}`);
            const data = await response.json();
            setBookings(data);
            setShowBooking(false)
            setShowSuccess(true)
            setMissingBooking(true)
            console.log("This is booking data: ", data)

        }catch(error){
            console.log(error);
            setMissingBooking(false)
        };
    }

    function searchBooking(){
        getBooking()

    }

    function deleteBooking(){

        const init = {
            method: 'DELETE', // GET by default
          };
      
          fetch(`http://localhost:8080/booking/${bookingId}`, init)
            .then(response => {
              if (response.status === 204) {
                  setShowSuccess(false)
                  setShowBooking(true)
                  setMissingBooking(true)
              } else if (response.status === 404) {
                Promise.reject(`Booking ID ${bookingId} not found`);
              } else {
               // Promise.reject('Something unexpected went wrong :)');
               setErrors('Something unexpected went wrong :)');
              }
            })
            .catch(error => console.log(error));
        

    }

    //this page will remove selected bookings from a cart
    return(
   
        <div >

<h2>Delete Booking</h2>
<p>Please enter your booking id:</p>

<input type="number" id="booking" value={bookingId} onChange={updateBookingId}/>
<button onClick={searchBooking}>Search</button>


<div hidden={showBooking}>
   Below is your booking detail: 
   <tr>Booking Id: {bookings.bookingId} </tr>
   <tr>Package Id: {bookings.packageId}</tr>
   <tr>Customer Id: {bookings.customerId}</tr>
   <tr>Num of Guests: {bookings.numOfGuest}</tr>

   <button onClick={deleteBooking}>Delete Booking</button>
</div>

<div hidden={showSuccess}> The booking ID {bookingId} is successfully deleted!!!</div>

<div hidden={missingBooking}>The booking Id {bookingId} does not exist!!</div>
   
        </div>

       
    )

}

export default DeleteBooking