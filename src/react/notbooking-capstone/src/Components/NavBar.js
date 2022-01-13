import { useContext, useEffect } from "react";
import jwtDecode from "jwt-decode";
import {Link} from "react-router-dom"
import AuthContext from "./Security/AuthContext";
import notbooking from "./notbookinglogo.jpeg"
import nyc from "./NYC.jpeg"


function NavBar(){

    const auth = useContext(AuthContext)

   return(
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
             <Link to ="/" ><img src={notbooking} width="60" height="60" /></Link>
        
               <ul><Link to ="/">Home</Link></ul>
               <ul><Link to ="/about">About</Link></ul>
               <ul><Link to ="/contact">Contact</Link></ul>
            
               {!auth.user && (
                <>
                    <ul><Link to="/login">Login</Link></ul>
                    <ul><Link to="/register">Register</Link></ul>
                </>
                )} 
           
        {auth.user && (
            <>
        
            <ul><Link to ="/booking/list">My&nbsp;Bookings</Link></ul>
           
            <p class="text-light bg-dark ">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Hello&nbsp;{auth.user.firstName}! &nbsp;&nbsp;</p>
            <button onClick={() => auth.logout()} >&nbsp;Logout</button>
            </>
        )}
       </nav>
   ) 
}

export default NavBar