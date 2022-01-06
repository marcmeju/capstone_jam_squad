import { Link, useParams } from "react-router-dom/cjs/react-router-dom.min"

function SelectTier(){
    //  This page will have a description of each tier along with a few examples
    //  The tricky part of this page will be displaying them vertically accross the page
    //  Before seting up this page I'll need to do some research into how to make them appear vertically
    const {location} = useParams();
    return(
    <>
    <p>Which Tier would you like to select from {location}</p>
    <Link to={`/${location}/${1}`}>
    <p>Package 1: Choose from packages with 2 days 4 events</p>
    </Link>
    <Link to={`/${location}/${2}`}>
    <p>Package 2: Choose from packages with 3 days 5 events</p>
    </Link>
    <Link to={`/${location}/${3}`}>
    <p>Package 3: Mix your own package for either 2 days 3 events, or 3 days 5 events </p>
    </Link>
    </>
    )
}

export default SelectTier