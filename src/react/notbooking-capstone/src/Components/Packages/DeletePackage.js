import React, {useEffect, useState} from "react";
import { Link, useHistory, useParams } from 'react-router-dom';


function DeletePackage(){

    const handleDelete = (event) =>{
        event.preventDefault()
        handleDeleteSubmit
    }

    const handleDeleteSubmit = () => {
        const init = {
            method: "DELETE",
        }
    }

    const handleCancel = () =>{
        setPackageName("")
        setTierId(0)
        setTierName("")
        setTierDesc("")
    }

    return(
        <form onSubmit={handleDelete} className="form-inline mx-2 my-4">
            <p>Are you sure you want to delete this package?</p>
            <p>Package Name: {packageName} | Tier: {tierName} </p>
            <Link to="/packages/view" className="btn btn-success ml-2" type="submit">Delete</Link>
            <Link to="/packages/view" className="btn btn-warning ml-2" onClick={handleCancel}>Cancel</Link>
        </form>
    )
}

export default DeletePackage