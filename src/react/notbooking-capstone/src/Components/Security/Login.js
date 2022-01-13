import { useContext, useState } from 'react';
import { Link, useHistory } from 'react-router-dom';
import AuthContext from './AuthContext';
import jwtDecode from 'jwt-decode';
import Errors from '../Errors';


function Login() {

  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [errors, setErrors] = useState([]);

  const auth = useContext(AuthContext);

  const history = useHistory();

  const usernameOnChangeHandler = (event) => {
    setUsername(event.target.value);
  };

  const passwordOnChangeHandler = (event) => {
    setPassword(event.target.value);
  };

  const formSubmitHandler = (event) => {
    event.preventDefault();

    const authAttempt = {
      username,
      password
    };

    const init = {
      method: 'POST', 
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(authAttempt)
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
  };

  return (
    <>
      <h2 className="my-4">Login</h2>
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
        <div className="mt-5">
          <button className="btn btn-success" type="submit">
            <i className="bi bi-plus-circle-fill"></i> Login</button>
          <Link to="/" className="btn btn-warning ml-2">
            <i className="bi bi-x"></i> Cancel
          </Link>
        </div>
       
        <div>
          <Link to = "/register">
          <p> Don't have an account? </p>
        
          </Link>
        </div>
      </form>
    </>
  );
}

export default Login;