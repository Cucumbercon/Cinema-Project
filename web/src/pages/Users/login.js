/* Title: Login Page
*  Description: Login page for the web users to login
*/

//Todo: add login functionality and connect register here? button to register page 
//  Match font with Signup page - Sean Pastore


import React, { useState } from "react";
import './login.css'

export const Login = (props) => {
    const [email, setEmail] = useState('');
    const [pass, setPass] = useState('');


    // todo: add login functionality
    const handleSubmit = (e) => {
        e.preventDefault();
        console.log(email);
    }

    return (
        <div className="auth-form-container">
            <form className="login-form" onSubmit={handleSubmit}>
                <label htmlFor="email">Email</label>
                <input value={email} onChange={(e) => setEmail(e.target.value)} type="email" placeholder="youremail@gmail.com" id="email" name="email" />
                <label htmlFor="password">Password</label>
                <input value={pass} onChange={(e) => setPass(e.target.value)} type="password" placeholder="********" id="password" name="password" />
                <button type="submit">Login</button>
            </form>

            <button className="link-btn" onClick={() => props.onFormSwitch('test')}>Don't have an account? Register here.</button>

        </div>
    )
}
