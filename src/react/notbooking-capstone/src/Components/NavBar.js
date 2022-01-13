import { useContext, useEffect } from "react";
import jwtDecode from "jwt-decode";
import {Link} from "react-router-dom"
import AuthContext from "./Security/AuthContext";

function NavBar(){

    const auth = useContext(AuthContext)

   return(
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
         
               <li><Link to ="/">Home</Link></li>
               <li><Link to ="/about">About</Link></li>
               <li><Link to ="/contact">Contact</Link></li>
            
               {!auth.user && (
                <>
                    <li><Link to="/login">Login</Link></li>
                    <li><Link to="/register">Register</Link></li>
                </>
                )} 
           
        {auth.user && (
            <div>
            <p>Hello {auth.user.username}!</p>
            <button onClick={() => auth.logout()} >Logout</button>
            </div>
        )}
       </nav>
   ) 
}

export default NavBar