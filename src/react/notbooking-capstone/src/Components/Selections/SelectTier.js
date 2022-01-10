import{Link} from "react-router-dom"
import React,{useState,useEffect} from "react"
import { useParams } from "react-router-dom/cjs/react-router-dom.min";

function SelectTier(){
    
    const {location} = useParams();
<<<<<<< HEAD
    const [tiers,setTiers] = useState([])

    useEffect(()=>{
        const getData = async() =>{
            try{
                const response = await fetch("http://localhost:8080/tier")
                const data = await response.json();
                setTiers(data)
            }catch(error){
                console.log(error)
            }
        }
        getData()
        console.log(tiers)
    })

    return(tiers &&
    <>

    <div>
        <table>
            <thead>
                <tr>
                    <th>Which Tier would you like to select from {location}</th>
                    <th></th>
                </tr>
            </thead>
            <tbody>
                {tiers.map((tier)=>(
                   <tr key={tier.tierId}>
                       <Link to={`/package/${location}/${tier.tierId}`}>
                       <td>{tier.tierName} : {tier.tierDescription} </td>
                       </Link>
                   </tr> 
                ))}
            </tbody>
        </table>
    </div>



    
    {/* <Link to={`/${location}/${1}`}>
=======

    console.log(`/${location}/${1}`);
    
    return(
    <>
    <p>Which Tier would you like to select from {location}</p>
    <Link to={`/package/${location}/${1}`}>
>>>>>>> 9dbf6649a915b12c07a3a608fb7b7013f30920e1
    <p>Package 1: Choose from packages with 2 days 4 events</p>
    </Link>
    <Link to={`/package/${location}/${2}`}>
    <p>Package 2: Choose from packages with 3 days 5 events</p>
    </Link>
    <Link to={`/package/${location}/${3}`}>
    <p>Package 3: Mix your own package for either 2 days 3 events, or 3 days 5 events </p>
    </Link> */}
    </>
    )
}

export default SelectTier