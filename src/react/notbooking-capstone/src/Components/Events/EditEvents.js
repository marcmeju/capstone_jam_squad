import Errors from "../Errors"
import { useState } from "react";

function EditEvents(){

    const [errors, setErrors] = useState([]);

    const [eventId] = useState(0);
    const [eventType,setEventType] = useState("");
    const [eventName,setEventName] = useState("");
    const [eventDate,setEventDate] = useState("");
    const [eventPrice,setEventPrice] = useState("");
    const [locationId,setLocationId] = useState(0);
    const [contactId,setContactId] = useState(0);

    const handleTypeChange = (event) => {setEventType(event.target.value)}
    const handleNameChange = (event) => {setEventName(event.target.value)}
    const handleDateChange = (event) => {setEventDate(event.target.value)}
    const handlePriceChange = (event) => {setEventPrice(event.target.value)}
    const handleLocationChange = (event) => {setLocationId(event.target.value)}
    const handleContactChange = (event) => {setContactId(event.target.value)}

    const handleUpdate= (event) => {
        event.preventDefault();
        handleUpdateSubmit
    }

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
        setLocationId (0);
        setContactId (0);
    }

return(
    <>
        <Errors errors={errors}/>
        <form onSubmit={handleUpdate} className="form-inline mx-2 my-4">
            <div>
             <input 
                className = "form-control col-10"
                id = "eventType"
                type="text"
                name="eventType"
                value={eventType}
                onChange ={handleTypeChange}
                placeholder={eventType}
            />
            </div>
            <div>
            <input 
                className = "form-control col-10"
                id = "eventName"
                type="text"
                name="eventName"
                value={eventName}
                onChange = {handleNameChange}
                placeholder ={eventName}
            />
            </div>
            <div>
            <input 
                className = "form-control col-10"
                id = "eventDate"
                type="text"
                name="eventDate"
                value={eventDate}
                onChange={handleDateChange}
                placeholder={eventDate}
            />
            </div>
            <div>
            <input 
                className = "form-control col-10"
                id = "eventPrice"
                type="text"
                name="eventPrice"
                value={eventPrice}
                onChange={handlePriceChange}
                placeholder={eventPrice}
            />
            </div>
            <div>
            <input 
                className = "form-control col-10"
                id = "locationId"
                type="text"
                name="locationId"
                value={locationId}
                onChange = {handleLocationChange}
                placeholder={locationId}
            />
            </div>
            <div>
            <input 
                className = "form-control col-10"
                id = "contactId"
                type="text"
                name="contactId"
                value={contactId}
                onChange = {handleContactChange}
                placeholder={contactId}
            />
            </div>
            <button className="btn btn-success ml-2" type="submit">
                Add Event
            </button>
            <Link to="/" className="btn btn-warning ml-2" onClick={handleCancel}>Cancel</Link>

        </form>
    </>
)
}

export default EditEvents