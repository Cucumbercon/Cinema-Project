import React, { useState, useEffect } from "react";
import "./UpdateProfile.css";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faUser } from "@fortawesome/free-solid-svg-icons";

export const UpdateProfile = (props) => {
    const [fullName, setFullName] = useState('');
    const [phoneNumber, setPhoneNumber] = useState('');
    const [subscribe, setSubscribe] = useState(false);
    const [creditCardNumber, setCreditCardNumber] = useState('');
    const [expirationDate, setExpirationDate] = useState('');
    const [password, setPassword] = useState('');
    const [confirmPassword, setConfirmPassword] = useState('');
    const [selectedProfilePicture, setSelectedProfilePicture] = useState(null);

    const handleProfilePictureUpload = (e) => {
        const file = e.target.files[0];

        if (file) {
            const imageUrl = URL.createObjectURL(file);
            setSelectedProfilePicture(imageUrl);
        }
    };

    // ToDO: function to load the user's existing information 
    const loadUserProfile = () => {
        // Fetch user's existing information
    };

    // TODO: function to update the user's information
    const handleSubmit = (e) => {
        e.preventDefault();
        // Check if password and confirmPassword match and update the user's password
        if (password === confirmPassword) {
            // Update the user's password here
        } else {
            // Display an error message indicating that passwords do not match
        }
    };

    // Calls the loadUserProfile function when the component mounts to load existing data
    useEffect(() => {
        loadUserProfile();
    }, []);

    return (
        <div className="update-profile-container">
            <div className="profile-picture-box">
                {selectedProfilePicture ? (
                    <img src={selectedProfilePicture} alt="Profile" />
                ) : (
                    <FontAwesomeIcon icon={faUser} size="5x" />
                )}
                <button
                    className="change-profile-picture-button"
                    onClick={() => document.getElementById('profilePictureInput').click()}
                >
                    Change Profile Picture
                </button>
                <input
                    type="file"
                    accept="image/*"
                    id="profilePictureInput"
                    style={{ display: 'none' }}
                    onChange={handleProfilePictureUpload}
                />
            </div>
            <div className="form-box">
                <form className="update-profile-form" onSubmit={handleSubmit}>
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
                            type="password"
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
                            type="password"
                            placeholder="Confirm Password"
                            id="confirmPassword"
                            name="confirmPassword"
                        />
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
        </div>
    );
};