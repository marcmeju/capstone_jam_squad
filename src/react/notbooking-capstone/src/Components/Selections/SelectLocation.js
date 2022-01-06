import { Link } from "react-router-dom"

function SelectLocation(){

    return(
        // look at examples of dynamic routing to account for each scenario

        <>
        <Link to="/nyc" className="btn btn-primary btn-sm">NYC</Link>
        <Link to="/chicago" className="btn btn-primary btn-sm">Chicago</Link>
        <Link to="/la" className="btn btn-primary btn-sm">Los Angelos</Link>
        </>
    )

}

export default SelectLocation