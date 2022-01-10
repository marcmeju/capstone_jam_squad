import { useContext, useEffect } from "react";
import jwtDecode from "jwt-decode";
import {Link} from "react-router-dom"
import AuthContext from "./Security/AuthContext";

function NavBar(){

    const auth = useContext(AuthContext)

   return(
       <nav>
           <ul>
               <li><Link to ="/">Home</Link></li>
               <li><Link to ="/about">About</Link></li>
               <li><Link to ="/contact">Contact</Link></li>
                
               <li><Link to ="/events/view">View Events</Link></li>       
               <li><Link to ="/packages/view">View Packages</Link></li>
           
               {!auth.user && (
                <>
                    <li><Link to="/login">Login</Link></li>
                    <li><Link to="/register">Register</Link></li>
                </>
                )}
           </ul>
        {auth.user && (
            <div>
            <p>Hello {auth.user.username}!</p>
            <button onClick={() => auth.logout()} className="btn btn-primary">Logout</button>
            </div>
        )}
       </nav>
   ) 
}

export default NavBar