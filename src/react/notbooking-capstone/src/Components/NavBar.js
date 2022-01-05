import {Link} from "react-router-dom"

function NavBar(){
   return(
       <nav>
           <ul>
               <li><Link to ="/">Home</Link></li>
           </ul>

           <ul>
               <li><Link to ="/about">About</Link></li>
           </ul>

           <ul>
               <li><Link to ="/contact">Contact</Link></li>
           </ul>

           <ul>
               <li><Link to ="/events/view">View Events</Link></li>
           </ul>

           <ul>
               <li><Link to ="/packages/view">View Packages</Link></li>
           </ul>
       </nav>
   ) 
}

export default NavBar