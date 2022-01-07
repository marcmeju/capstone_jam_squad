import React from "react";
import { useState, useEffect } from "react";
import { Link, useHistory, useParams } from "react-router-dom";


function Packages(){

    console.log("this is test")

    const [packageId,setPackageId] = useState(0)
    // const { location, tierId } = useParams();
    const tierId = 1;
    const location = "nyc";

    //const [location,setLocation] = useState("")
    const [contact,setContact] = useState("")
    const [packageList, setPackageList] = useState([]);
    const [eventList, setEventList] = useState([]);
    const [displayEvent, setDisplayEvent] = useState(true);
    //const [date]

    //generate packages given a tier id and location
     //code to get package contents from id
    //and event id

    console.log(tierId, "  ", location)

    function filterCityTier(input){
      let Tier1 = input.filter(pkg => pkg.tierId === tierId && pkg.packageName.startsWith(location.substring(0,1).toUpperCase())) 
    
      return Tier1;
    }
   function showEvent(id){
       setPackageId(id);
       //console.log("inside show Event ", id);
       getEventData();
     
       
   }

    // const getData = () => {
        
    //       fetch("http://localhost:8080/package")
    //       .then(response => response.json())
    //       .then(data => console.log("UseEffect = ", data));
    // }

    const getData = async () => {
        try{
            const response = await fetch("http://localhost:8080/package");
            const data = await response.json();
            setPackageList(filterCityTier(data));
        }catch(error){
            console.log(error);
        };
    }

    const getEventData = async () => {
        try{
            const response = await fetch(`http://localhost:8080/package/${packageId}/details`);
            const data =  await response.json();
            console.log("this is data ==> ", data)
            setEventList(data);
            setDisplayEvent(false) ;

        }catch(error){
            console.log(error);
        };
//         fetch(`http://localhost:8080/package/${packageId}/details`,{
//             method: "GET", 
//             headers: {
//                 "Accept":"application/json"
//             }
//         })
//         .then(response => {
//             if (response.status!== 200){
//                 return Promise.reject("response is not 200 ok")
//             }
//             return response.json();
//         })
//         .then(data => {
//  // setEventList(data), 
//  console.log(data)
//  //setDisplayEvent(false))

       // })
           

    }
      
        useEffect(() =>{
            getData();
        },[]);

      return(<div >
        
     <table className = "Package table">
         <thead>
             <tr>
                
                 <th scope="col">Package Name</th>
                 <th scope="col">Tier </th>
                 <th scope="col">Package Price</th>
                
               
               </tr>
         </thead>
         <tbody>
             {packageList.map((packages)=>(
                 <tr key ={packages.packageId}>
                 <td> {packages.packageName}</td>
                 <td> {packages.tierId}</td>
                 <td> {packages.packagePrice}</td>
                 <td><button onClick = {()=> showEvent(packages.packageId)}>View Events</button></td>
        
                 </tr>
            ))}
         </tbody>
     </table>

     <div hidden={displayEvent}>
    
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
     </div>);
}

export default Packages