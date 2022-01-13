import { Link, useParams } from "react-router-dom"
import { useState,useEffect } from "react";

function ViewEvents(){

    const [eventId] = useState(0);
    const { location, tierId, packageId} = useParams();
    
    const [packageNameField, setPackageNameField] = useState("");
    const [events,setEvents] = useState([]);
    const [evnt,setEvent] = useState({});

    const [eventList, setEventList] = useState([]);

    useEffect(() =>{
        const getData = async () => {
            try{
                const response = await fetch(`http://localhost:8080/package/${packageId}/details`);
                const data = await response.json();
                setEventList(data);
                setPackageNameField([...new Set(data.map(x =>x.packageName))][0]);
                console.log("this is view event data", data)
            }catch(error){
                console.log(error);
            };
        }
        getData();
    }, [packageId]);

    function uniquePackageName(){

    }
   
    return(
        <div>
            <p></p>
             <tr><th scope="col">Package Name:</th> <th scope="col">{packageNameField}</th></tr>
             <p></p>
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
     <Link to={`/booking/cart/${packageId}`} >
        <button >Book Package</button>
                  </Link>
                
                  <Link to={`/package/${location}/${tierId}`} ><button > Go Back</button>
                  </Link>

        </div>
    )
}

export default ViewEvents