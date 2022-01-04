import About from "./Components/About"
import Contact from "./Components/Contact"
import NavBar from "./Components/NavBar"
import Home from "./Components/Home"

import {BrowserRouter as Router, Route,Switch} from "react-router-dom"

function App(){
    return(
        <>
            
            <Router>
            <NavBar />
                <Switch>
                    <Route path = "/about">
                        <About />
                    </Route>

                    <Route path = "/contact">
                        <Contact />
                    </Route>

                    <Route path = "/">
                        <Home />
                    </Route>                    
                </Switch>
            </Router>
        </>
    )
}

export default App