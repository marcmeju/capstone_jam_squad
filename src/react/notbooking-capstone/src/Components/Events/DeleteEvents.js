import React, {useEffect, useState} from "react";
import { Link, useHistory, useParams } from 'react-router-dom';

function DeleteEvents(){

    const handleDelete = (event) => {
        event.preventDefault();
        handleDeleteSubmit
    }

    const handleDeleteSubmit = () => {
        const init = {
            method: "DELETE",
        }
    }

    const handleCancel = () =>{
        setEventType ("");
        setEventName ("");
        setEventDate ("");
        setEventPrice ("");
        setLocationId ("");
        setContactId ("");
    }

    return(
        <form onSubmit={handleDelete} className="form-inline mx-2 my-4">
            <p>Are you sure you want to delete this event?</p>
            <p>Event Type: {eventType} | Event Name: {eventName} | Event Date: {eventDate}</p>
            <Link to="/events/view" className="btn btn-success ml-2" type="submit">Delete</Link>
            <Link to="/events/view" onClick={handleCancel}>Cancel</Link>
        </form>
    )

}

export default DeleteEvents