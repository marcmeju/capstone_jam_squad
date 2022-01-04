import { useState } from "react"
import { Link } from "react-router-dom";


const DEFAULT_EVENT = {
    eventType: "",
    eventName: "",
    eventDate: "",
    eventPrice: "",
    locationId: "",
    contactId: ""
}

function AddEvents(){

    
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

    const handleSubmit = (event) => {
        event.preventDefault();

        const newEvent = {
            eventType,
            eventName,
            eventDate,
            eventPrice,
            locationId,
            contactId
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
        <form onSubmit={handleSubmit} className="form-inline mx-2 my-4">
            <div>
            <input 
                className = "form-control col-10"
                id = "eventType"
                type="text"
                name="eventType"
                value={eventType}
                onChange ={handleTypeChange}
                placeholder="Event Type"
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
                placeholder = "Event Name"
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
                placeholder="Date"
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
                placeholder="Price"
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
                placeholder="Location"
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
                placeholder="Contact"
            />
            </div>
            <button className="btn btn-success ml-2" type="submit">
                Add Event
            </button>
            <Link to="/" className="btn btn-warning ml-2" onClick={handleCancel}>Cancel</Link>

        </form>
    )
    

}

export default AddEvents