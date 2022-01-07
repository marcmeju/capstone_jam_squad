import { Link } from "react-router-dom"

function SelectLocation(props){

    return(
        // look at examples of dynamic routing to account for each scenario

        <>
        <p><Link to={`/tier/${"nyc"}`} >NYC</Link></p>
        <p><Link to={`/tier/${"chicago"}`}  >Chicago</Link></p>
        <p><Link to={`/tier/${"la"}`}  >Los Angeles</Link></p>
        </>
    )

}

export default SelectLocation