import SelectLocation from "./Selections/SelectLocation"

function Home(){
    return(
        <>
        <h2>Home</h2>
        <p>Which Location would you like to see events for?</p>
        <SelectLocation />
        </>
    );
}

export default Home;