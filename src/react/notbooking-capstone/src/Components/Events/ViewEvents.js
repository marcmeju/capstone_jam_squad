import { Link } from "react-router-dom"
import { useState,useEffect } from "react";

function ViewEvents(props){

    const [eventId] = useState(0);
    // const [eventType,setEventType] = useState("");
    // const [eventName,setEventName] = useState("");
    // const [eventDate,setEventDate] = useState("");
    // const [eventPrice,setEventPrice] = useState("");
    // const [locationId,setLocationId] = useState("");
    // const [contactId,setContactId] = useState("");

    const [events,setEvents] = useState([]);
    const [evnt,setEvent] = useState({});
    const [packageId, setPackageId] = useState(props.packageId);
    const [eventList, setEventList] = useState([]);

    console.log("this is package id", props.packageId);
    useEffect(() =>{
        const getData = async () => {
            try{
                const response = await fetch(`http://localhost:8080/package/${props.packageId}/details`);
                const data = await response.json();
                setEventList(data);
                console.log("this is view event data", data)
            }catch(error){
                console.log(error);
            };
        }
        getData();
    }, [props.packageId]);

    return(
        <div>
            <table className = "Event table">
         <thead>
             <tr>
                
                 <th scope="col">Event Name</th>
                 <th scope="col">Event Date </th>
                 <th scope="col">City</th>
                 <th scope="col">State</th>
                 <th scope="col">Email</th>
                 <th scope="col">Phone</th>
                
               
               </tr>
         </thead>
         <tbody>
             {eventList.map((events)=>(
                <tr>
                 <td> {events.eventName}</td>
                 <td> {events.eventDate}</td>
                 <td> {events.locationCity}</td>
                 <td> {events.locationState}</td>
                 <td> {events.contactEmail}</td>
                 <td> {events.contactPhone}</td>
        
                 </tr>
            ))}
         </tbody>
     </table>
        </div>
    )
}

export default ViewEvents