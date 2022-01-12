import { useState } from "react"

function Event(props){

    const [eventType,setType] = useState("")
    const [eventName,setEventName] = useState("")
    const [eventDate,setEventDate] = useState("")
    //it might be useful to have this as an object
    //or array so that we can call the parts of the contact
    //same with location
    const [contact,setContact] = useState({})
    const [location,setLocation] = useState("")

    //pull event info from an event id

    
}

export default Event