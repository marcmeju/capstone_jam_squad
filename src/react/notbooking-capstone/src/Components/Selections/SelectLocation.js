import { Link } from "react-router-dom"

function SelectLocation(){

    return(
        // look at examples of dynamic routing to account for each scenario

        <>
        <Link to="/nyc">NYC</Link>
        <Link to="/chicago">Chicago</Link>
        <Link to="/la">Los Angelos</Link>
        </>
    )

}

export default SelectLocation