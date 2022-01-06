import { Link } from "react-router-dom"

function SelectLocation(props){

    return(
        // look at examples of dynamic routing to account for each scenario

        <>
        <p><Link to={`/${"nyc"}`} >NYC</Link></p>
        <p><Link to={`/${"chicago"}`}  >Chicago</Link></p>
        <p><Link to={`/${"la"}`}  >Los Angeles</Link></p>
        </>
    )

}

export default SelectLocation