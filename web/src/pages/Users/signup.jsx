import React, { useState } from "react";
import "./signup.css";

export const Signup = (props) => {
    const [email, setEmail] = useState('');
    const [pass, setPass] = useState('');
    const [fullName, setFullName] = useState(''); 
    const [phoneNumber, setPhoneNumber] = useState(''); 
    const [subscribe, setSubscribe] = useState(false); 
    const [creditCardNumber, setCreditCardNumber] = useState('');
    const [expirationDate, setExpirationDate] = useState('');

    const handleSubmit = (e) => {
        e.preventDefault();

        // Logs the values to the console
        console.log(email);
        console.log(pass);
        console.log(fullName); 
        console.log(phoneNumber); 
        console.log(subscribe); 
        console.log(creditCardNumber);
        console.log(expirationDate);
    }

    return (
        <div className="auth-form-container">
            <div className="form-box"> {/* Container for labels */}
                <form className="register-form" onSubmit={handleSubmit}>
                    <div className="label-input">
                        <label htmlFor="fullName">Full Name *</label> {/* Updated label to Full Name */}
                        <input value={fullName} name="fullName" onChange={(e) => setFullName(e.target.value)} id="fullName" placeholder="Full Name" type="text" required />
                    </div>
                    <div className="label-input">
                        <label htmlFor="email">Email *</label>
                        <input value={email} onChange={(e) => setEmail(e.target.value)} type="email" placeholder="youremail@gmail.com" id="email" name="email" required />
                    </div>
                    <div className="label-input">
                        <label htmlFor="password">Password *</label>
                        <input value={pass} onChange={(e) => setPass(e.target.value)} type="password" placeholder="********" id="password" name="password" required />
                    </div>
                    <div className="label-input">
                        <label htmlFor="phoneNumber">Phone Number *</label>
                        <input value={phoneNumber} onChange={(e) => setPhoneNumber(e.target.value)} type="tel" placeholder="111-111-1111" id="phoneNumber" name="phoneNumber" required />
                    </div>
                    {/* New fields for credit card information */}
                    <div className="label-input">
                        <label htmlFor="creditCardNumber">Credit Card Number</label>
                        <input value={creditCardNumber} onChange={(e) => setCreditCardNumber(e.target.value)} type="text" placeholder="XXXX-XXXX-XXXX-XXXX" id="creditCardNumber" name="creditCardNumber" />
                    </div>
                    <div className="label-input">
                        <label htmlFor="expirationDate">Expiration Date</label>
                        <input value={expirationDate} onChange={(e) => setExpirationDate(e.target.value)} type="text" placeholder="MM/YYYY" id="expirationDate" name="expirationDate" />
                    </div>

                    {/* Checkbox for promotional emails */}
                    <div className="subscribe-checkbox">
                        <label>
                            <input type="checkbox" checked={subscribe} onChange={(e) => setSubscribe(e.target.checked)} />
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
