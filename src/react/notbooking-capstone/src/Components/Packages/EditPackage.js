import Errors from "../Errors"
import { useState } from "react";

function EditPackage(){

    const [errors,setErrors]=useState([])
    
    const [packageId,setPackageId] = useState(0)
    const [packageName,setPackageName] = useState("")
    const [tierId,setTierId] = useState(0)
    const [tierName,setTierName] = useState("")
    const [tierDesc,setTierDesc] = useState("")

    const handleNameChange = (event) => {setPackageName(event.target.value)}
    const handleTierIdChange = (event) => {setTierId(event.target.value)}
    const handleTierNameChange = (event) => {setTierName(event.target.value)}
    const handleTierDescChange = (event) => {setTierDesc(event.target.value)}

    const handleUpdateSubmit = () => {
        const updatePackage = {
            id: packageId,
            packageName: packageName,
            tierId: tierId,
            tierName: tierName,
            tierDesc: tierDesc
        }
    }

    const handleCancel = () =>{
        setPackageId(0)
        setPackageName("")
        setTierId(0)
        setTierName("")
        setTierDesc("")
    }

    return(
        <>
        <Errors errors={errors}/>

        <form onSubmit={handleUpdateSubmit} className="form-inline mx-2 my-4">
            <div>
                <input
                    className="form-control col-10"
                    id ="packageName"
                    type="text"
                    name="packageName"
                    value={packageName}
                    onChange={handleNameChange}
                    placeholder={packageName}
                />
            </div>

            <div>
                <input
                    className="form-control col-10"
                    id ="tierId"
                    type="int"
                    name="tierId"
                    value={tierId}
                    onChange={handleTierIdChange}
                    placeholder={tierId}
                />
            </div>

            <div>
                <input
                    className="form-control col-10"
                    id ="tierName"
                    type="text"
                    name="tierName"
                    value={tierName}
                    onChange={handleTierNameChange}
                    placeholder={tierName}
                />
            </div>

            <div>
                <input
                    className="form-control col-10"
                    id ="tierDesc"
                    type="text"
                    name="tierDesc"
                    value={tierDesc}
                    onChange={handleTierDescChange}
                    placeholder={tierDesc}
                />
            </div>
        </form>

        </>
    )
}

export default EditPackage