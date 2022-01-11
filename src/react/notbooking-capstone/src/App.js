import About from "./Components/About"
import Contact from "./Components/Contact"
import NavBar from "./Components/NavBar"
import Home from "./Components/Home"
import NotFound from "./Components/NotFound"
import Login from "./Components/Security/Login"
import Register from "./Components/Security/Register"

import jwt_decode from "jwt-decode";
import { useState, useEffect } from "react"
import {BrowserRouter as Router, Route,Switch} from "react-router-dom"
import ViewEvents from "./Components/Events/ViewEvents"
import AddEvents from "./Components/Events/AddEvents"
import ViewPackage from "./Components/Packages/ViewPackage"
import AddPackage from "./Components/Packages/AddPackage"
import SelectTier from "./Components/Selections/SelectTier"
import Packages from "./Components/Packages/Packages"
import AuthContext from "./Components/Security/AuthContext"

const TOKEN_KEY = "user-api-token"

function App(){
    
    const [user, setUser] = useState(null);
    const [initialized,setInitialized] = useState(false);

    useEffect(()=>{
      const token = localStorage.getItem(TOKEN_KEY);

      if(token){login(token)}

      setInitialized(true);
    },[])

    const login = (token) =>{
      const {id,sub:username,roles:userRoles} = jwt_decode(token);

      const roles = userRoles?.split(",");

      const user = {
        id,
        username,
        roles,
        token,
        hasRole(role){
          return this.roles.includes(role);
        },
      }

      console.log(user);

      setUser(user);

      return user;
    };

    const logout = ()=> {
      localStorage.removeItem(TOKEN_KEY)
      setUser(null);
    };
      
    const auth = {
      user: user? {...user}:null,
      login,
      logout,
    }



    return(
            <AuthContext.Provider value = {auth}>
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

                    <Route path={`/city/:location`}>   <SelectTier/>   </Route>
                    <Route path={`/package/:location/:tierId`}></Route>

                    <Route path = "/login">   <Login />   </Route>
                    <Route path = "/register">   <Register />   </Route>

                    <Route>
                        <NotFound />
                    </Route>                                  
                </Switch>
            </Router>
            </AuthContext.Provider>
    )
}

export default App