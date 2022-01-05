import { useState, useEffect } from "react";
import { Link } from "react-router-dom";

function ViewPackage(){

    const [packageId] = useState(0);
    const [packages,setPackages] = useState([])

        useEffect(() =>{
            const getData = async () => {
                try{
                    const response = await fetch("http://localhost:8080/api/notBooking/packages");
                    const data = await response.json();
                    setPackages(data);
                }catch(error){
                    console.log(error);
                };
            }
            getData();
        });

        return(
            <div>
                <Link to="/packages/add" className="btn btn-primary btn-sm">Add Packages</Link>
                <table className="table table-striped table-dark table-hover">
                    <thead>
                        <tr>
                            <th id="packageId">Package ID:</th>
                            <th id="tierId">Tier ID:</th>
                            <th id="Events">Event Name:</th>
                            <th id="locationofEvents">Location:</th>
                            <th id="contact">Contact Info:</th>
                            <th id="date">Date of Event:</th>
                        </tr>
                    </thead>

                    <tbody>
                        {packages.map((packge)=>(
                            <tr key={packageId}>
                                <td>{packge.packageId}</td>
                                <td>{packge.tierId}</td>
                                <td>{packge.tierName}</td>
                                {/* we need to figure out a way to extract the location information 
                                and event information from ids through queeries, 
                                or throuhg instances of package examples.
                                This will proobably be some sort of get statement that
                                filters packages based on location */}
                                <td>{packge.tierDesc}</td>
                                <td>
                            <div className="float-right">
                                <Link to={`/packages/edit/${packge.packageId}`} className="btn btn-primary btn-sm">
                                    Edit
                                </Link>

                                <Link to={`/packages/delete/${packge.packageId}`} className="btn btn-danger btn-sm ml-2">
                                    Delete
                                </Link>
                            </div>
                        </td>
                                </tr>
                        ))}
                    </tbody>
                </table>
            </div>
        )

}

export default ViewPackage