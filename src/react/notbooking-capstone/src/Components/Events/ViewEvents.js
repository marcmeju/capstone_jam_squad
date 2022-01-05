import { Link } from "react-router-dom"
import { useState,useEffect } from "react";

function ViewEvents(){

    const [eventId] = useState(0);
    // const [eventType,setEventType] = useState("");
    // const [eventName,setEventName] = useState("");
    // const [eventDate,setEventDate] = useState("");
    // const [eventPrice,setEventPrice] = useState("");
    // const [locationId,setLocationId] = useState("");
    // const [contactId,setContactId] = useState("");

    const [events,setEvents] = useState([])
    const [evnt,setEvent] = useState({})

    //setEvent(events.filter.eventId)


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
            <Link to="/events/add" className="btn btn-primary btn-sm">Add Events</Link>
            <table className="table table-striped table-dark table-hover">
                <thead>
                    <tr>
                        <th id="eventId">Event Id:</th>
                        <th id="eventType">Event Type: </th>
                        <th id="eventName">Event Name:</th>
                        <th id="eventDate">Event Date:</th>
                        <th id="eventPrice">Event Price</th>
                        <th id="locationId">Location</th>
                        <th id="contactId">Contact Id</th>
                        <th></th>
                        <th></th>
                    </tr>
                </thead>

                <tbody>
                    {events.map((evnt) => (
                    <tr key={eventId}>
                        <td>{evnt.eventId}</td>
                        <td>{evnt.eventType}</td>
                        <td>{evnt.eventName}</td>
                        <td>{evnt.setEventDate}</td>
                        <td>{evnt.eventPrice}</td>
                        <td>{evnt.locationId}</td>
                        <td>{evnt.contactId}</td>
                        <td>
                            <div className="float-right">
                                <Link to={`/events/edit/${evnt.eventId}`} className="btn btn-primary btn-sm">
                                    Edit
                                </Link>

                                <Link to={`/events/delete/${evnt.eventId}`} className="btn btn-danger btn-sm ml-2">
                                    Delete
                                </Link>
                                
                            </div>
                        </td>
                    </tr>
                    ))}
                </tbody>
            </table>
        </div>
    )
}

export default ViewEvents