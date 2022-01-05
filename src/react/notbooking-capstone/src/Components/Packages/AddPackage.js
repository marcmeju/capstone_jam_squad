import { useState } from "react"
import { Link } from "react-router-dom"

const DEFAULT_PACKAGE = {
    packageName: "",
    tierId:0,
    tierName:"",
    tierDesc:""
}

function AddPackage(){

    const [packageName,setPackageName] = useState("")
    const [tierId,setTierId] = useState(0)
    const [tierName,setTierName] = useState("")
    const [tierDesc,setTierDesc] = useState("")

    const handleNameChange = (event) => {setPackageName(event.target.value)}
    const handleIdChange = (event) => {setTierId(event.target.value)}
    const handleTierNameChange = (event) => {setTierName(event.target.value)}
    const handleTierDescChange = (event) => {setTierDesc(event.target.value)}

    const handleSubmit = (event) => {
        event.preventDefault();

        const newPackage = {
            packageName,
            tierId,
            tierName,
            tierDesc
        }
    }

    const handleCancel = () =>{
        setPackageName("")
        setTierId(0)
        setTierName("")
        setTierDesc("")
    }

    return(
        <form onSubmit={handleSubmit} className="form-inline mx-2 my-4">
            <div>
                <input
                    className="form-control col-10"
                    id="packageName"
                    type="text"
                    name="packageName"
                    value={packageName}
                    onChange={handleNameChange}
                    placeholder="Package Name"
                />
            </div>

            <div>
                <input
                    className="form-control col-10"
                    id="tierId"
                    type="int"
                    name="tierId"
                    value={tierId}
                    onChange={handleIdChange}
                    placeholder="Tier ID"
                />
            </div>

            <div>
                <input
                    className="form-control col-10"
                    id="tierName"
                    type="text"
                    name="tierName"
                    value={tierName}
                    onChange={handleTierNameChange}
                    placeholder="Tier Name"
                />
            </div>

            <div>
                <input
                    className="form-control col-10"
                    id="tierDesc"
                    type="text"
                    name="tierDesc"
                    value={tierDesc}
                    onChange={handleTierDescChange}
                    placeholder="Description"
                />
            </div>

            <button className="btn btn-success ml-2" type="submit">
                Add Package
            </button>
            <Link to="/" className="btn btn-warning ml-2" onClick={handleCancel}>Cancel</Link>

        </form>
    )

}

export default AddPackage