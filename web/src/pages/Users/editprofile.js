/*  title: "Edit Profile",
*   This page is for editing the logged-in user's profile. 
*/

import React, { useState } from "react";
// TODO: Add validation for email and password
// Add functionality to update profile button

export const EditProfile = (props) => {
  const [email, setEmail] = useState('');
  const [pass, setPass] = useState('');
  const [phoneNumber, setPhoneNumber] = useState('');

  const handleSubmit = (e) => {
    e.preventDefault();
    
    // Perform an API call or update user information in your backend
    // You may want to add validation before making the API call

    console.log("Updated email:", email);
    console.log("Updated password:", pass);
    console.log("Updated phone number:", phoneNumber);
  }

  return (
    <div className="auth-form-container">
      <div className="form-box">
        <form className="edit-profile-form" onSubmit={handleSubmit}>
          <div className="label-input">
            <label htmlFor="email">Email</label>
            <input
              value={email}
              onChange={(e) => setEmail(e.target.value)}
              type="email"
              placeholder="youremail@gmail.com"
              id="email"
              name="email"
            />
          </div>
          <div className="label-input">
            <label htmlFor="password">New Password</label>
            <input
              value={pass}
              onChange={(e) => setPass(e.target.value)}
              type="password"
              placeholder="********"
              id="password"
              name="password"
            />
          </div>
          <div className="label-input">
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

          <button type="submit">Update Profile</button>
        </form>
      </div>
      <button className="link-btn" onClick={() => props.onFormSwitch('profile')}>Back to Profile</button>
    </div>
  )
}
