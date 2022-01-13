import { Link } from "react-router-dom"
import nyc from "../NYC.jpeg"
import chicago from "../Chicago.jpeg"
import la from "../la.jpg"

import React,{ useEffect, useState } from "react"

function SelectLocation(){



    return( 
        // look at examples of dynamic routing to account for each scenario
        <>
            <div>
                <table>
                <tbody>
        <tr>
            <td>New York Package</td><td><Link to={`/city/${"nyc"}`} ><img src={nyc} alt="nyc" width="600" height="400" /></Link></td>
        </tr>
        <td>&nbsp;</td>
        <tr>
        <td>Chicago Package   </td> <td><Link to={`/city/${"chicago"}`}  ><img src={chicago} alt="chicago" width="600" height="400" /></Link></td>

        </tr>
        <tr><td>     &nbsp;     </td></tr>
        <tr><td>Los Angeles Package </td><td ><Link to={`/city/${"la"}`}  ><img src={la} alt="la" width="600" height="400" /></Link></td></tr>

        
        
        </tbody>

                </table>
            </div>
        </>
                
    )

}

export default SelectLocation