import { useContext, useState } from 'react';
import { Link, useHistory } from 'react-router-dom';
import AuthContext from './AuthContext';
import Errors from '../Errors'

function Register() {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [confirmPassword, setConfirmPassword] = useState('');
  const [firstname, setFirstname] = useState('');
  const [lastname, setLastname] = useState('');
  const [phonenumber, setPhonenumber] = useState('');
  const [email, setEmail] = useState('');
  const [errors, setErrors] = useState([]);

  const auth = useContext(AuthContext);

  const history = useHistory();

  const usernameOnChangeHandler = (event) => {
    setUsername(event.target.value);
  };

  const passwordOnChangeHandler = (event) => {
    setPassword(event.target.value);
  };

  const confirmPasswordOnChangeHandler = (event) => {
    setConfirmPassword(event.target.value);
  };

  const firstnameOnChangeHandler = (event) => {
    setFirstname(event.target.value);
  };

  const lastnameOnChangeHandler = (event) => {
    setLastname(event.target.value);
  };

  const phonenumberOnChangeHandler = (event) => {
    setPhonenumber(event.target.value);
  };

  const emailOnChangeHandler = (event) => {
    setEmail(event.target.value);
  };

  const formSubmitHandler = (event) => {
    event.preventDefault();


    setErrors([]);

    if (password !== confirmPassword) {
      setErrors(['Password and confirm password don\'t match']);
      return;
    }

    const newUser = {
      username,
      password,
      roleid: 2
    };

    const contact = {
      phone: phonenumber,
      email,
      contactType: "Customer"
    }

    let contactId = 0;
    let userId = 0;

    const init = {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(newUser)
    };

    fetch('http://localhost:8080/create_account', init)
      .then(response => {
        if (response.status === 201 || response.status === 400) {
          return response.json();
        }
        return Promise.reject('Something unexpected went wrong :)');
      })
      .then(data => {
        console.log("data returned from create account ", data)
        if (data.appUserId) {
          userId = data.appUserId

          console.log("Entering Contact")

          const continit = {
            method: 'POST',
            headers: {
              'Content-Type': 'application/json'
            },
            body: JSON.stringify(contact)
          };

          fetch(`http://localhost:8080/contact`, continit)
            .then(response => {
              if (response.status === 201 || response.status === 400) {
                return response.json();
              } return Promise.reject('Something unexpected went wrong :)');

            })
            .then(data => {
              if (data.contactId) {
                console.log("contact created", data.contactId)

                console.log("Entering Customer Creation")
            contactId= data.contactId;

            const customer = {
              customerFirstName: firstname,
              customerLastName: lastname,
              userId,
              contactId
            }
    
            const custinit = {method: 'POST', 
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(customer)
    };
    
    
            fetch(`http://localhost:8080/customer`, custinit)
        .then(response => {
          if (response.status === 201 || response.status === 400) {
            return response.json();
          } return Promise.reject('Something unexpected went wrong :)');
          
        })
        .then(data => {
          if(data.id){
            console.log("Customer Created successfully: ", data.id)
    
            history.push("/")
            
          }
        })
        .catch(error => console.log(error));

              }
            })
            .catch(error => console.log(error));
          const init = {
            method: 'POST',
            headers: {
              'Content-Type': 'application/json'
            },
            body: JSON.stringify(newUser)
          };

          fetch('http://localhost:8080/authenticate', init)
            .then(response => {
              if (response.status === 200) {
                return response.json();
              } else if (response.status === 403) {
                return null;
              }
              return Promise.reject('Something unexpected went wrong :)');
            })
            .then(data => {
              if (data) {
                auth.login(data.jwt_token);
                history.push('/');
              } else {

                setErrors(['Login failure.']);
              }
            })
            .catch(error => console.log(error));
        } else {

          setErrors(data.messages)
        }
      })
      .catch(error => console.log(error));










  };

  return (
    <>
      <h2 className="my-4">Register</h2>
      <Errors errors={errors} />
      <form onSubmit={formSubmitHandler}>
        <div className="form-group">
          <label htmlFor="username">Username:</label>
          <input className="form-control" type="text" id="username" name="username"
            value={username} onChange={usernameOnChangeHandler} />
        </div>
        <div className="form-group">
          <label htmlFor="password">Password:</label>
          <input className="form-control" type="password" id="password" name="password"
            value={password} onChange={passwordOnChangeHandler} />
        </div>
        <div className="form-group">
          <label htmlFor="confirmPassword">Confirm Password:</label>
          <input className="form-control" type="password" id="confirmPassword" name="confirmPassword"
            value={confirmPassword} onChange={confirmPasswordOnChangeHandler} />
        </div>
        <div className="form-group">
          <label htmlFor="username">Firstname:</label>
          <input className="form-control" type="text" id="firstname" name="firstname"
            value={firstname} onChange={firstnameOnChangeHandler} />
        </div>
        <div className="form-group">
          <label htmlFor="username">Lastname:</label>
          <input className="form-control" type="text" id="lastname" name="lastname"
            value={lastname} onChange={lastnameOnChangeHandler} />
        </div>

        <div className="form-group">
          <label htmlFor="username">Phone Number:</label>
          <input className="form-control" type="tel" id="phonenumber" name="phonenumber"
            value={phonenumber} onChange={phonenumberOnChangeHandler} />
        </div>
        <div className="form-group">
          <label htmlFor="username">Email:</label>
          <input className="form-control" type="email" id="email" name="email"
            value={email} onChange={emailOnChangeHandler} />
        </div>

        <div className="mt-5">
          <button className="btn btn-success" type="submit">
            <i className="bi bi-plus-circle-fill"></i> Register</button>
          <Link to="/" className="btn btn-warning ml-2">
            <i className="bi bi-x"></i> Cancel
          </Link>
        </div>
      </form>
    </>
  );
}

export default Register;