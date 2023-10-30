import React, { useState, useEffect } from "react";
import "./UpdateProfile.css";


export const UpdateProfile = (props) => {
        const [fullName, setFullName] = useState('');
        const [email, setEmail] = useState('');
        const [phoneNumber, setPhoneNumber] = useState('');
        const [subscribe, setSubscribe] = useState(false);
        const [creditCardNumber, setCreditCardNumber] = useState('');
        const [expirationDate, setExpirationDate] = useState('');
        const [password, setPassword] = useState('');
        const [confirmPassword, setConfirmPassword] = useState('');
        const [selectedPaymentCard, setSelectedPaymentCard] = useState('');


        

        // ToDO: function to load the user's existing information 
        const loadUserProfile = () => {
            fetch('http://localhost:8000/api/loadprofile', {
                method: 'GET'
            })
                .then((response) => response.text())
                .then((data) => {
                    const parsedData = JSON.parse(data);
                })

        };

        // TODO: function to update the user's information
        const handleSubmit = (e) => {
            e.preventDefault();
            // Check if password and confirmPassword match and update the user's password
            if (password === confirmPassword) {
                // Update the user's password here
                const pass = encrypt(password);
                
                const changeInfo = {
                    fullName,
                    email,
                    phoneNumber,
                    subscribe,
                    creditCardNumber,
                    expirationDate,
                    pass,
                    confirmPassword,
                    selectedPaymentCard
                };
                console.log(changeInfo)
                fetch('http://localhost:8000/api/updateprofile', {
                    method: 'POST',
                    body: JSON.stringify(loginInfo),
                    headers: {
                        'Content-Type': 'application/json'
                    }
                });
            } else {
                // Display an error message indicating that passwords do not match
                console.error('Password does not match with confirm password');
            }
        };

        // Calls the loadUserProfile function when the component mounts to load existing data
        useEffect(() => {
            loadUserProfile();
        }, []);

        return (
                
                <div className="form-box">
                    <form className="update-profile-form" onSubmit={handleSubmit}>
                         {/* Email */}
                         <div className="input-container">
                            <label htmlFor="email">Email</label>
                            <input
                                value={email}
                                onChange={(e) => setEmail(e.target.value)}
                                type="updateemail"
                                placeholder="Email"
                                id="email"
                                name="email"
                            />
                        </div>
                        {/* Full Name */}
                        <div className="input-container">
                            <label htmlFor="fullName">Full Name</label>
                            <input
                                value={fullName}
                                onChange={(e) => setFullName(e.target.value)}
                                type="text"
                                placeholder="Full Name"
                                id="fullName"
                                name="fullName"
                            />
                        </div>
                       
                        {/* Phone Number */}
                        <div className="input-container">
                            <label htmlFor="phoneNumber">Phone Number</label>
                            <input
                                value={phoneNumber}
                                onChange={(e) => setPhoneNumber(e.target.value)}
                                type="tel"
                                placeholder="111-111-1111"
                                id="phoneNumber"
                                name="phoneNumber"
                            />
                        </div>
                        {/* Password */}
                        <div className="input-container">
                            <label htmlFor="password">Password</label>
                            <input
                                value={password}
                                onChange={(e) => setPassword(e.target.value)}
                                type="updatepassword"
                                placeholder="New Password"
                                id="password"
                                name="password"
                            />
                        </div>
                        {/* Confirm Password */}
                        <div className="input-container">
                            <label htmlFor="confirmPassword">Confirm Password</label>
                            <input
                                value={confirmPassword}
                                onChange={(e) => setConfirmPassword(e.target.value)}
                                type="updatepassword"
                                placeholder="Confirm Password"
                                id="confirmPassword"
                                name="confirmPassword"
                            />
                        </div>
                        {/* Payment Card */}
                        <div className="input-container">
                            <label htmlFor="paymentCard">Payment Card</label>
                            <select
                                value={selectedPaymentCard}
                                onChange={(e) => setSelectedPaymentCard(e.target.value)}
                                id="paymentCard"
                                name="paymentCard"
                                className="select-input" // add a class to the select element
                            >
                                <option value="">Select Payment Card</option>
                                <option value="Payment Card 1">Payment Card 1</option>
                                <option value="Payment Card 2">Payment Card 2</option>
                                <option value="Payment Card 3">Payment Card 3</option>
                            </select>
                        </div>
                        
                        {/* Credit Card Number */}
                        <div className="input-container">
                            <label htmlFor="creditCardNumber">Credit Card Number</label>
                            <input
                                value={creditCardNumber}
                                onChange={(e) => setCreditCardNumber(e.target.value)}
                                type="text"
                                placeholder="XXXX-XXXX-XXXX-XXXX"
                                id="creditCardNumber"
                                name="creditCardNumber"
                            />
                        </div>
                        {/* Expiration Date */}
                        <div className="input-container">
                            <label htmlFor="expirationDate">Expiration Date</label>
                            <input
                                value={expirationDate}
                                onChange={(e) => setExpirationDate(e.target.value)}
                                type="text"
                                placeholder="MM/YYYY"
                                id="expirationDate"
                                name="expirationDate"
                            />
                        </div>
                        {/* Checkbox for promotional emails */}
                        <div className="subscribe-checkbox">
                            <label>
                                <input
                                    type="checkbox"
                                    checked={subscribe}
                                    onChange={(e) => setSubscribe(e.target.checked)}
                                />
                                Opt in or out for promotional emails
                            </label>
                        </div>
                        <button type="submit">Update Profile</button>
                    </form>
                </div>
            
        );    };

