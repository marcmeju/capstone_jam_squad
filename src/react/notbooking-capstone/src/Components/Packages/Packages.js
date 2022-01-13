import React from "react";
import { useState, useEffect } from "react";
import { Link, useHistory, useParams } from "react-router-dom";
import ViewEvents from '../Events/ViewEvents'
import VipPackage from "../Events/VipEvents";

function Packages(){


    const [packageId,setPackageId] = useState(0)
    const { location, tierId } = useParams();
    const [contact,setContact] = useState("")
    const [packageList, setPackageList] = useState([]);
    const [eventList, setEventList] = useState([]);
    const [displayEvent, setDisplayEvent] = useState(true);
    const [vipPackage, setVipPackage] = useState(false)

    function filterCityTier(input){
      let Tier1 = input.filter(pkg => pkg.tierId == tierId && pkg.packageName.startsWith(location.substring(0,1).toUpperCase())) 
      return Tier1;
    }

    function showEvent(id){
       setPackageId(id);
       setDisplayEvent(false) ;        
   }
    const getData = async () => {
        try{
            const response = await fetch("http://localhost:8080/package");
            const data = await response.json();
            setPackageList(filterCityTier(data));
           }catch(error){
            console.log(error);
        };

    }

    const showVip = () => {
        if(tierId == 3){
            setVipPackage(true)
        }

    }

    const getEventData = async () => {
        try{
            const response = await fetch(`http://localhost:8080/package/${packageId}/details`);
            const data =  await response.json();
            setEventList(data);
            setDisplayEvent(false) ;
        }catch(error){
            console.log(error);
        };  
    }
      
            useEffect(() => {
            getData();
            //showVip();
        },[]);

        

      return(<div>

    <div hidden={vipPackage}>
        
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
                 <td> ${packages.packagePrice}</td>
                 {/* <td><button onClick = {()=> showEvent(packages.packageId)}>View Events</button></td> */}
                 <td><Link to={`/events/${location}/${tierId}/${packages.packageId}`}>
                     <button> View Events</button>
                     </Link></td>
                 </tr>
            ))}
         </tbody>
     </table>
     <Link to={`/city/${location}`} ><button > Go Back</button>
                  </Link>
     </div>

     {/* <div hidden={displayEvent}>
        <ViewEvents packageId={packageId} /> */}
        
        
       
    

     
     </div>); 
}

export default Packages