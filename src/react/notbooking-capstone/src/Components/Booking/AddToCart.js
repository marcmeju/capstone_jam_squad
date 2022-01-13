import useState from "react"

function AddBooking(){

    // this page will have the URL /${location} which for now will be one of three values
    // on this page, the form will be a 
    const [bookingId,setBookingId] = useState (0)
    const [packageId,setPackageId] = useState (0)
    const [customerId,setCustomerId] = useState(0)
    const [numGuests,setNumGuests] = useState(0)

    const [cart,setCart] = useState([])
    const [packages,setPackages] = useState([])
    const [events,setEvents] = useState([])

    //use package Id to populate packages

    //use packageId to populate events

    //use number of guests to calculate price

    return(
<>
</>
    )
}

export default AddBooking