import Errors from "../Errors"
import { useState } from "react";

function EditEvents(){

    const [errors, setErrors] = useState([]);

    const [eventId] = useState(0);
    const [eventType,setEventType] = useState("");
    const [eventName,setEventName] = useState("");
    const [eventDate,setEventDate] = useState("");
    const [eventPrice,setEventPrice] = useState("");
    const [locationId,setLocationId] = useState("");
    const [contactId,setContactId] = useState("");

    const handleTypeChange = (event) => {setEventType(event.target.value)}
    const handleNameChange = (event) => {setEventName(event.target.value)}
    const handleDateChange = (event) => {setEventDate(event.target.value)}
    const handlePriceChange = (event) => {setEventPrice(event.target.value)}
    const handleLocationChange = (event) => {setLocationId(event.target.value)}
    const handleContactChange = (event) => {setContactId(event.target.value)}

    const handleUpdateSubmit = () =>{
        const updatedEvent = {
            id: eventId,
            eventType: eventType,
            eventName: eventName,
            eventDate: eventDate,
            eventPrice: eventPrice,
            locationId: locationId,
            contactId: contactId
        }
    }
    
    const handleCancel = () =>{
        setEventType ("");
        setEventName ("");
        setEventDate ("");
        setEventPrice ("");
        setLocationId ("");
        setContactId ("");
    }

return(
    <>
        <Errors errors={errors}/>
        <form onSubmit={handleUpdateSubmit} className="form-inline mx-2 my-4">
            
        </form>
    </>
)
}

export default EditEvents