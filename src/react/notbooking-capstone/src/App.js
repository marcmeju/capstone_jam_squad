import About from "./Components/About"
import Contact from "./Components/Contact"
import NavBar from "./Components/NavBar"
import Home from "./Components/Home"
import NotFound from "./Components/NotFound"
import Login from "./Components/Security/Login"
import Register from "./Components/Security/Register"

import jwt_decode from "jwt-decode";
import { useState, useEffect } from "react"
import {BrowserRouter as Router, Route,Switch, Redirect} from "react-router-dom"
import ViewEvents from "./Components/Events/ViewEvents"
import AddEvents from "./Components/Events/AddEvents"
import ViewPackage from "./Components/Packages/ViewPackage"
import AddPackage from "./Components/Packages/AddPackage"
import SelectTier from "./Components/Selections/SelectTier"
import Packages from "./Components/Packages/Packages"
import AuthContext from "./Components/Security/AuthContext"
import ViewBooking from "./Components/Booking/ViewCart"
import DeleteBooking from "./Components/Booking/DeleteFromCart"

const TOKEN_KEY = "user-api-token"

function App(){
    
    const [user, setUser] = useState(null);
    const [initialized,setInitialized] = useState(false);
    const [customer,setCustomer] = useState({})

    useEffect(()=>{
      const token = localStorage.getItem(TOKEN_KEY);

      if(token){login(token)}
      
      setInitialized(true);
    },[])

    const findByUser = ()=>( user ? async() =>{
      try{
        const response = await fetch(`http:localhost:8080/customer/find/${user.username}`) 
        const data = await response.json();
        setCustomer({data});
      }catch(error){console.log(error)}
      console.log(customer)
      return customer;
    } : null);


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
      findByUser();
      
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

                    <Route path={`/city/:location`}> <SelectTier/> </Route>

                    <Route path={`/package/:location/:tierId`}> <Packages/> </Route>
                    <Route path ={`/events/:location/:tierId/:packageId`}><ViewEvents/></Route>
                    
                    <Route path = {`/booking/cart/:packageId`}> {user? <ViewBooking/> : <Redirect to ="/login"/> } </Route>
                    <Route path = {`/booking/delete`}><DeleteBooking/></Route>

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