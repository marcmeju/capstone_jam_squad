import { useState, useEffect, useContext } from "react";
import { Link, useHistory, useParams } from "react-router-dom";
import Errors from '../Errors';
import AuthContext from "../Security/AuthContext";
const TOKEN_KEY = "user-api-token"

function EditBooking(props){


    const [packages,setPackages] = useState([])
    const [numOfGuests, setnumOfGuests]= useState(1);
    const [editBookingView, setEditBookingView] = useState(false)
    const [errors, setErrors] = useState([]);
    const [showSuccessMessage, setShowSuccessMessage] = useState(true)
    const [showFailedMessage, setShowFailedMessage] = useState(true)
    const [bookingId, setBookingId] = useState(0)

    const auth = useContext(AuthContext)
    const token = localStorage.getItem(TOKEN_KEY);
    console.log("This is the edit cart page",token);

    useEffect(() =>{
        const getData = async () => {
            try{
                const response = await fetch(`http://localhost:8080/package/${props.packageId}`);
                const data = await response.json();
                setPackages(data);
            }catch(error){
                console.log(error);
            };
        }
        getData();
    }, [props.packageId]);
    console.log("This is package list", packages);

  
  
    function updatePrice(e){
     console.log(e.target.value);
     setnumOfGuests(e.target.value);
    }

    function confirmBooking(){

        const newBooking = {packageId:props.packageId, customerId:auth.user.customerId, numOfGuest:numOfGuests};
        console.log("This is the booking object", newBooking)

        const init = {
            method: 'POST', // GET by default
            headers: {
              'Content-Type': 'application/json',
              'Authorization': 'Bearer '+token
            },
            body: JSON.stringify(newBooking)
          };
      
          fetch('http://localhost:8080/booking', init)
            .then(response => {
              if (response.status === 201 || response.status === 400) {
                return response.json();
              }
              return Promise.reject('Something unexpected went wrong :)');
            })
            .then(data => {
              // we either created the record...
              if (data.bookingId) {
                // redirect the user back to the /todos route
                setBookingId(data.bookingId)
                setEditBookingView(true)
                setShowSuccessMessage(false)
                
              } else {
                // we have error messages
                setErrors(data);
                setShowFailedMessage(false)
              }
            })
            .catch(error => console.log(error));
        };
    


    const handleSubmit = event => {
        event.preventDefault();
        console.log("Inside Submit", event)
        confirmBooking()
    }


    return(
        <div>

            <div hidden={editBookingView}>
    
            <p>Cart:</p>
            <table>
                <thead>
                    <tr>
                        <th scope = "col"> Items </th>
                        <th scope = "col"> Details  </th>
                      </tr>
                </thead>
            <tbody>
            <tr><td> {}</td></tr>
            <tr><td>Package Name:</td><td>{packages.packageName  }</td></tr>
            <tr> <td>Package Tier :</td><td>{packages.tierId}</td> </tr>
            <tr><td>Booking Start Date :</td><td>{props.bookingStartDate}</td></tr>
            <tr><td>Booking End Date :</td><td>{props.bookingEndDate}</td></tr>
            <tr><td>Number of guests(Max 15guests) : </td><td><input type="number" id="quantity" min="1" max="15" value={numOfGuests} onChange={updatePrice}/></td></tr>
            <tr><td>Package Price:</td><td>${packages.packagePrice}</td></tr>
            <tr><td>Total Booking Price:</td><td>${packages.packagePrice*numOfGuests}</td></tr>
            </tbody>
            </table>

            <form onSubmit={handleSubmit}>

            

            <button type="submit"> Confirm Booking </button>

            <Link to={`/package/${props.location}/${packages.tierId}`} ><button >Cancel and Go Back</button>
                  </Link>

            

            </form>
            </div>

            <div hidden={showSuccessMessage}><p>Booking SuccessFul!!! Please note the booking confirmation Id : {bookingId} </p>
            <Link to={`/`} ><button >Go Home</button>
                  </Link></div>
            <div hidden={showFailedMessage}><p>"Booking failed!!! Unable to Book!!!</p></div>


        </div>
    )

}

export default EditBooking