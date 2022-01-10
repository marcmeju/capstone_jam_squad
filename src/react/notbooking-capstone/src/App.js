import About from "./Components/About"
import Contact from "./Components/Contact"
import NavBar from "./Components/NavBar"
import Home from "./Components/Home"
import NotFound from "./Components/NotFound"

import { useState, useEffect } from "react"
import {BrowserRouter as Router, Route,Switch} from "react-router-dom"
import ViewEvents from "./Components/Events/ViewEvents"
import AddEvents from "./Components/Events/AddEvents"
import ViewPackage from "./Components/Packages/ViewPackage"
import AddPackage from "./Components/Packages/AddPackage"
import SelectTier from "./Components/Selections/SelectTier"


function App(){
    
    const [userStatus, setUserStatus] = useState({
        user: null,
        login(username) {
          // Use previous state to preserve login and logout methods when updating user
          setUserStatus((prev) => ({ ...prev, user: username }));
        },
        logout() {
          // "token" must match the name used in "/Login" route
          localStorage.removeItem("token");
          setUserStatus((prev) => ({ ...prev, user: null }));
        },
      });
      

    return(
            
            <Router>
            <NavBar />
                <Switch>
                    <Route exact path = "/">   <Home/>   </Route>      
                    <Route path = "/about">   <About />   </Route>
                    <Route path = "/contact">   <Contact />   </Route>
                    
                    <Route path = "/events/view">   <ViewEvents />   </Route>
                    <Route path = "/events/add">   <AddEvents />   </Route>
                    
                    <Route path ="/packages/view" >   <ViewPackage/>   </Route>
                    <Route path ="/packages/add" >   <AddPackage/>   </Route>

                    <Route path={`/tier/:location`}>   <SelectTier/>   </Route>
                    <Route path={`/package/:location/:tierId`}></Route>

                    <Route>
                        <NotFound />
                    </Route>                                  
                </Switch>
            </Router>
    )
}

export default App