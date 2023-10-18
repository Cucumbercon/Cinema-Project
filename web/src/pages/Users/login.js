import React, { useState } from "react";
import './login2.css';

export const Login = (props) => {
    const [email, setEmail] = useState('');
    const [pass, setPass] = useState('');

    const handleSubmit = (e) => {
        e.preventDefault();

        const loginInfo = {
          email, pass,
        };

        fetch('http://localhost:8000/api/login', {
        method: 'POST',
        body: JSON.stringify(loginInfo),
        headers: {
          'Content-Type': 'application/json'
        }
      }).then(function (response) {
        if (response.status === 200) {
          response.json()
            .then(function (jsonUser) {
              console.log(jsonUser.fullName);
              /**
               * you can receive user's name, id, and type(0 is customer, 1 is admin):
               * jsonUser.fullName, jsonUser.id, jsonUser.type
               * 
               * capture them here, if u need more user info, I can add them(e.g. address, phone number, email)
               * 
               * 
               * direct to a welcome page or popup-window or something else to inform the user login successfully
               * 
               *  
               * */ 
            })
            .catch(function (error) {
              console.error(error);
            });
        } else if (response.status === 406) {
          /**
           * means Email and/or password do not match
           * need to do something here to inform the user
           * 
           * 
           * 
           * */ 
          console.error('Request failed with status: ', response);
        } else {
          /**
           * other errors
           * maybe direct to error page, and put the response status into error page
           * 
           * 
           *  */ 

          console.error('Request failed with status: ', response.status);
          return Promise.reject('request fail');
        }
      });

    }

    return (
        <div className="auth-form-container">
            <form className="login-form" onSubmit={handleSubmit}>
                <label htmlFor="email">Email</label>
                <input
                  value={email}
                  onChange={(e) => setEmail(e.target.value)}
                  type="email"
                  placeholder="youremail@gmail.com"
                  id="email"
                  name="email"
                />
                <label htmlFor="password">Password</label>
                <input
                  value={pass}
                  onChange={(e) => setPass(e.target.value)}
                  type="password"
                  placeholder="********"
                  id="password"
                  name="password"
                />
                <button className="login-btn" type="login">Login</button>
            </form>
            
            <button className="log-btn" onClick={() => props.onFormSwitch('test')}>
              Don't have an account? Register here.
            </button>
        </div>
    )
}
