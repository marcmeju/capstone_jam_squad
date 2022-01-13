import { useState, useEffect, useContext } from "react";
import { Link, useParams } from "react-router-dom"
import Errors from '../Errors';
import AuthContext from "../Security/AuthContext";
const TOKEN_KEY = "user-api-token"


function BookingList(){

    const [bookingId, setBookingId] = useState(1);
    const [bookings, setBookings] = useState([]);
    const [errors, setErrors] = useState([]);


    const auth = useContext(AuthContext)
    const token = localStorage.getItem(TOKEN_KEY);
    console.log("This is the edit cart page",token);


    useEffect(() =>{
        const getBooking = async () => {

            console.log("This is the booking id:", bookingId)
            
            try{
                const response = await fetch(`http://localhost:8080/booking/customer/${auth.user.customerId}`);
                const data = await response.json();
                setBookings(data);
                console.log("This is booking data: ", data)
    
            }catch(error){
                console.log(error);
            };
        }
        getBooking();
    }, []);




    return(
        <div>
            
            <table className = "Booking List table">
         <thead>
            
             <tr>
                
                 <th scope="col">Booking Id</th>
                 <th scope="col">Package Id </th>
                 <th scope="col">Customer FirstName</th>
                 <th scope="col">Customer LastName</th>
                 <th scope="col">Number of Guests</th>
               
               </tr>
         </thead>
         
         <tbody>
             {bookings.map((booking)=>(
                <tr>
                 <td> {booking.bookingId}</td>
                 <td> {booking.packageId}</td>
                 <td> {auth.user.firstName}</td>
                 <td> {auth.user.lastName}</td>
                 <td> {booking.numOfGuest}</td>

           
        
                 </tr>
            ))}
         </tbody>
     </table>
     
                
                  <Link to={`/`} ><button > Go Home</button>
                  </Link>

        </div>

       
    )
}

export default BookingList