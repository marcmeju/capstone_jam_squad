import { useState } from "react"

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

    const handleChange

    const handleSubmit

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
                onChange
            />
            </div>
            <div>
            <input 
                className = "form-control col-10"
                id = "eventName"
                type="text"
                name="eventName"
                value={eventName}
                onChange
            />
            </div>
            <div>
            <input 
                className = "form-control col-10"
                id = "eventDate"
                type="text"
                name="eventDate"
                value={eventDate}
                onChange
            />
            </div>
            <div>
            <input 
                className = "form-control col-10"
                id = "eventPrice"
                type="text"
                name="eventPrice"
                value={eventPrice}
                onChange
            />
            </div>
            <div>
            <input 
                className = "form-control col-10"
                id = "locationId"
                type="text"
                name="locationId"
                value={locationId}
                onChange
            />
            </div>
            <div>
            <input 
                className = "form-control col-10"
                id = "contactId"
                type="text"
                name="contactId"
                value={contactId}
                onChange
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