import { useState, useEffect } from "react";
import { Link } from "react-router-dom";

function ViewBooking(){

    const [bookingId] = useState(0)
    const [packages,setPackage] = useState([])
    const [events,setEvents] = useState([])


    useEffect(() =>{
        const getData = async () => {
            try{
                const response = await fetch("http://localhost:8080/api/notBooking/packages");
                const data = await response.json();
                setPackages(data);
            }catch(error){
                console.log(error);
            };
        }
        getData();
    });

    useEffect(() =>{
        const getData = async () => {
            try{
                const response = await fetch("http://localhost:8080/api/notBooking/events");
                const data = await response.json();
                setEvents(data);
            }catch(error){
                console.log(error);
            };
        }
        getData();
    });

    return(
        <div>
            <p>Cart:</p>
            <p>Packages: {packages}</p>
            <p>Events: {events}</p>
            {/* There will need to be a way to 
            display events grouped by package
            as well as a way to get the prices easily */}
            <p>Total Price:</p>
            
            <Link to="/checkout">Checkout</Link>

        </div>
    )
}

export default ViewBooking