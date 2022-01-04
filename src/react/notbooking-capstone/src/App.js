import About from "./Components/About"
import Contact from "./Components/Contact"
import NavBar from "./Components/NavBar"
import Home from "./Components/Home"
import NotFound from "./Components/NotFound"

import { useState, useEffect } from "react"
import {BrowserRouter as Router, Route,Switch} from "react-router-dom"
import ViewEvents from "./Components/Events/ViewEvents"
import AddEvents from "./Components/Events/AddEvents"


function App(){

    //make events and packages props to pass to view events and view packages
    
        
        const [packages,setPackages] = useState([])

        useEffect(() =>{
            const getData = async () => {
                try{
                    const response = await fetch("http://localhost:8080/api/notBooking/packages");
                    const data = await response.json();
                    setPackages(data);
                }catch(error){
                    console.log(error);
                };
            }
            getData();
        });
    

    return(
            
            <Router>
            <NavBar />
                <Switch>
                    <Route exact path = "/">
                        <Home />
                    </Route>      
                    
                    <Route path = "/about">
                        <About />
                    </Route>

                    <Route path = "/contact">
                        <Contact />
                    </Route>

                    <Route path = "/events/view">
                        <ViewEvents />
                    </Route>

                    <Route path = "/events/add">
                        <AddEvents />
                    </Route>

                    <Route>
                        <NotFound />
                    </Route>

                                  
                </Switch>
            </Router>
    )
}

export default App