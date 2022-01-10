import { Link } from "react-router-dom"
import React,{ useEffect, useState } from "react"

function SelectLocation(){

    const [locations,setLocations] = useState([])
    const [distinct,setDistinct] = useState([])
    
    useEffect(()=>{
        const getData= async () =>{
            try{
                const response = await fetch("http://localhost:8080/location")
                const data = await response.json();
                setLocations(data)
            }catch(error){
                console.log(error)
            }
            let temp = [...new Set(locations.map(loc=>loc.locationCity))].sort();
            setDistinct(temp)
        }
        getData();
    });

    return( distinct && 
        // look at examples of dynamic routing to account for each scenario
        <>
            <div>
                <table>
                    <thead>
                        <tr>
                            <th id="city">Choose a location to explore your vacation!</th>
                        </tr>
                    </thead>

                    <tbody>
                        {distinct.map((loc)=>(
                            <tr>
                              <td><Link to={`/tier/${loc}`}>{loc}</Link></td>
                            </tr>   
                        ))}
                    </tbody>

                </table>
            </div>
        </>
                
    )

}

export default SelectLocation