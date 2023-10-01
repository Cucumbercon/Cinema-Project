import React, { useState } from "react";
import "./signup.css";
//TODO: Add validation for email and password
//      Add functionality to Create Account button and link to login page 

export const Signup = (props) => {
    const [email, setEmail] = useState('');
    const [pass, setPass] = useState('');
    const [firstname, setFirstName] = useState('');
    const [lastname, setLastName] = useState('');
    const [phoneNumber, setPhoneNumber] = useState('');
    const [subscribe, setSubscribe] = useState(false);

    const userData = {
        "first name": firstname,
        "last name": lastname,
        "email": email,
        "password": pass,
        "phoneNumber": phoneNumber
    };

    const handleSubmit = async (e) => {
        e.preventDefault();

        // send post to spring
        const response = await fetch('http://localhost:8000/api/register', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({ userData }),
        });
            // if user register success
            if (response.status === 200) {
                // should direct to a new page!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
                console.log('Registration successful');
            } else if (response.status === 409) {
                // if already exists, show a pop-up window, or something else
                console.error('User already exists');
            } else {
                // deal with others error // maybe direct to a error page or error pop-up window
                console.log(response)
                console.error('Registration failed');
            }



        // if (response.ok) {
        //     // success
        //     console.log('Registration successful');
        // } else {
        //     // fail
        //     console.error('Registration failed');
        // }

        //logs the values to the console
        // console.log(email);
        // console.log(pass);
        // console.log(firstname); 
        // console.log(lastname);
        // console.log(phoneNumber); 
        // console.log(subscribe); 
    }

    return (
        <div className="auth-form-container">
            <div className="form-box"> {/* Container for labels */}
                <form className="register-form" onSubmit={handleSubmit}>
                    <div className="label-input">
                        <label htmlFor="first name">First Name</label>
                        <input
                            value={firstname}
                            name="first name"
                            required
                            onChange={(e) => setFirstName(e.target.value)}
                            id="first name"
                            placeholder="First Name"
                            type="first"
                        />
                    </div>
                    <div className="label-input">
                        <label htmlFor="Last Name">Last Name</label>
                        <input value={lastname} required name="Last Name" onChange={(e) => setLastName(e.target.value)} id="last name" type="last" placeholder="Last Name" />
                    </div>
                    <div className="label-input">
                        <label htmlFor="email">Email</label>
                        <input value={email} required onChange={(e) => setEmail(e.target.value)} type="email" placeholder="youremail@gmail.com" id="email" name="email" />
                    </div>
                    <div className="label-input">
                        <label htmlFor="password">Password</label>
                        <input value={pass} required onChange={(e) => setPass(e.target.value)} type="password" placeholder="********" id="password" name="password" />
                    </div>
                    <div className="label-input">
                        <label htmlFor="phoneNumber">Phone Number</label>
                        <input value={phoneNumber} required onChange={(e) => setPhoneNumber(e.target.value)} type="tel" placeholder="111-111-1111" id="phoneNumber" name="phoneNumber" />
                    </div>

                    {/* Checkbox for promotional emails */}
                    <div className="subscribe-checkbox">
                        <label>
                            <input type="checkbox"
                                checked={subscribe}
                                name="subCheckBox"
                                onChange={(e) => setSubscribe(e.target.checked)} />
                            Subscribe to promotional emails
                        </label>
                    </div>

                    <button type="submit">Create Account</button>
                </form>
            </div>
            <button className="link-btn" onClick={() => props.onFormSwitch('login')}>Already have an account? Login here.</button>
        </div>
    )
}
