import { useState, useEffect, useContext } from "react";
import { Link, useHistory, useParams } from "react-router-dom";
import EditBooking from "./EditCart";



function ViewBooking(){

    const [bookingId] = useState(0)
    const [packages,setPackages] = useState([])
    const { packageId } = useParams();
    const [eventList, setEventList] = useState([]);
    const [bookingStartDate, setBookingStartDate] = useState("2020-02-02");
    const [bookingEndDate, setBookingEndDate] = useState("");
    const [showTable, setShowTable] = useState(true);
    const [location, setLocation] = useState("");

    



    useEffect(() =>{
        const getData = async () => {
            try{
                const response = await fetch(`http://localhost:8080/package/${packageId}`);
                const data = await response.json();
                setPackages(data);
                console.log("This is package data: ", data)

                const response1 = await fetch(`http://localhost:8080/package/${packageId}/details`);
                const data1 = await response1.json();
                setEventList(data1);
                let dateArray = [...new Set(data1.map(x => (x.eventDate.substring(0,10))))]
                setBookingStartDate(dateArray.sort()[0])
                setBookingEndDate(dateArray.reverse()[0])
                console.log("this is view event data",  data1)
                console.log("this is view event dataset dates",  [...new Set(data1.map(x=> x.locationState))])
                setShowTable(false)
                switch ([...new Set(data1.map(x=> x.locationState))][0]) {
                    case "NY":
                        setLocation("nyc")
                        break;
                    case "IL":
                        setLocation("chicago")
                        break;
                    case "CA":
                        setLocation("la")
                        break;
                  }


            }catch(error){
                console.log(error);
            };
        }
        getData();
    }, [packageId]);
    

    


   

    return(
   
        <div hidden={showTable}>
            <EditBooking packageId={packageId} bookingStartDate={bookingStartDate} bookingEndDate={bookingEndDate} location={location}/>
            
        </div>
       
    )
}

export default ViewBooking