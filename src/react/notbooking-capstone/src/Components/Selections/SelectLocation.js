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
<<<<<<< HEAD
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
=======
        <p><Link to={`/tier/${"nyc"}`} >NYC</Link></p>
        <p><Link to={`/tier/${"chicago"}`}  >Chicago</Link></p>
        <p><Link to={`/tier/${"la"}`}  >Los Angeles</Link></p>
>>>>>>> 9dbf6649a915b12c07a3a608fb7b7013f30920e1
        </>
                
    )

}

export default SelectLocation