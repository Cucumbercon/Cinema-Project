import React, { useState, useEffect } from "react";
import { encrypt } from './encryption';
import { useNavigate, useLocation } from 'react-router-dom';
import { FaHome } from 'react-icons/fa';

import './login2.css';

export const Login = (props) => {
  const navigate = useNavigate();
  const location = useLocation();
  const [email, setEmail] = useState('');
  const [plainPass, setPlainPass] = useState('');
  const [rememberMe, setRememberMe] = useState(false);


  useEffect(() => {
    if (location.state && location.state.fromBookingPage) {
      alert('You need to log in first!');
    }
  }, []);

  const handleSubmit = (e) => {
    e.preventDefault();

    const pass = encrypt(plainPass);

    const loginInfo = {
      email, pass, rememberMe,
    };
    console.log(loginInfo)
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
            localStorage.setItem('isLoggedIn', 'true');
            if (jsonUser.type === 1)
              localStorage.setItem('isAdmin', 'true');
            localStorage.setItem('name', jsonUser.fullName);
            localStorage.setItem('id', jsonUser.id);
            return navigate("/");

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
        alert('Email and/or password do not match, please try again');
        console.error('Request failed with status: ', response);
      } else if (response.status === 401) {
        /**
         * 
         * means the user is not active
         * Need to do something here
         * 
         * 
         */
        alert('Sorry, you account is inactive');
        console.error('Request failed with status: ', response);
      } else {
        /**
         * other errors
         * maybe direct to error page, and put the response status into error page
         * 
         * 
         *  */
        alert('request failed');
        console.error('Request failed with status: ', response.status);
        return Promise.reject('request fail');
      }
    })
    .catch(function (error) {
      console.error('Unhandled error:', error);
    });

    

  }

  return (

    <div className="auth-form-container">

      <form className="login-form" onSubmit={handleSubmit}>
        <button className="home-btn" onClick={() => navigate('/')} style={{ backgroundColor: '#ff6600' }}><FaHome size={24} /></button>

        <label htmlFor="email">Email</label>
        <input
          value={email}
          required
          onChange={(e) => setEmail(e.target.value)}
          type="email"
          placeholder="youremail@gmail.com"
          id="email"
          name="email"
        />
        <label htmlFor="password">Password</label>
        <input
          value={plainPass}
          required
          onChange={(e) => setPlainPass(e.target.value)}
          type="password"
          placeholder="********"
          id="password"
          name="password"
        />
        <div className="login-remember-container">
          <button className="login-btn" type="login">Login</button>
          <div className="remember-me">
            <input
              type="checkbox"
              id="rememberMe"
              name="rememberMe"
              checked={rememberMe}
              onChange={(e) => setRememberMe(e.target.checked)}
            />
            <label htmlFor="rememberMe">Remember me</label>
          </div>
        </div>
      </form>


      <button className="forgot-pass-btn" onClick={() => navigate('/forgotpass')}>
        Forgot Password?
      </button>
      <button className="log-btn" onClick={() => navigate('/signup')}>
        Don't have an account? Register here.
      </button>

    </div>
  )
}