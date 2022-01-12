import{Link} from "react-router-dom"
import React,{useState,useEffect} from "react"
import { useParams } from "react-router-dom/cjs/react-router-dom.min";

function SelectTier(){
    
    const {location} = useParams();


    return(

        <>
    
        <p>Which Tier would you like to select from {location}</p>
    
        <Link to={`/package/${location}/${1}`}>
    
        <p>Package 1: Choose from packages with 2 days 4 events</p>
    
        </Link>
    
        <Link to={`/package/${location}/${2}`}>
    
        <p>Package 2: Choose from packages with 3 days 5 events</p>
    
        </Link>
    
        <Link to={`/package/${location}/${3}`}>
    
        <p>Package 3: Mix your own package for either 2 days 3 events, or 3 days 5 events </p>
    
        </Link>
    
        </>
    
    )


}

export default SelectTier