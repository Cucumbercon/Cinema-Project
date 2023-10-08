import React, { useState } from "react";
import "./registration.css"; // Import the CSS file

export const Signup = (props) => {
  // State variables
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [fullName, setFullName] = useState('');
  const [phoneNumber, setPhoneNumber] = useState('');
  const [subscribe, setSubscribe] = useState(false);

  // Optional fields
  const [includeCreditCardInfo, setIncludeCreditCardInfo] = useState(false);
  const [creditCardNumber, setCreditCardNumber] = useState('');
  const [expirationDate, setExpirationDate] = useState('');
  const [street, setStreet] = useState('');
  const [city, setCity] = useState('');
  const [state, setState] = useState('');
  const [zipCode, setZipCode] = useState('');

  // Form submission handler
  const handleSubmit = (e) => {
    e.preventDefault();

    // Log form values to the console
    console.log("Email:", email);
    console.log("Password:", password);
    console.log("Full Name:", fullName);
    console.log("Phone Number:", phoneNumber);
    console.log("Subscribe:", subscribe);
    
    if (includeCreditCardInfo) {
      console.log("Credit Card Number:", creditCardNumber);
      console.log("Expiration Date:", expirationDate);
      console.log("Street:", street);
      console.log("City:", city);
      console.log("State:", state);
      console.log("Zip Code:", zipCode);
    }

  }


  return (
    <div className="auth-form-container">
      <div className="form-box">
        <form className="register-form" onSubmit={handleSubmit}>
          
          {/* Full Name */}
          <div className="label-container">
            <label htmlFor="fullName">Full Name *</label>
            <input
              type="text"
              id="fullName"
              name="fullName"
              value={fullName}
              onChange={(e) => setFullName(e.target.value)}
              className="input-field" 
              placeholder="Full Name"
              required
            />
          </div>

          {/* Email */}
          <div className="label-container">
            <label htmlFor="email">Email *</label>
            <input
              type="email"
              id="email"
              name="email"
              value={email}
              onChange={(e) => setEmail(e.target.value)}
              className="input-field" 
              placeholder="youremail@gmail.com"
              required
            />
          </div>

          {/* Password */}
          <div className="label-container">
            <label htmlFor="password">Password *</label>
            <input
              type="password"
              id="password"
              name="password"
              value={password}
              onChange={(e) => setPassword(e.target.value)}
              className="input-field" 
              placeholder="********"
              required
            />
          </div>

          {/* Phone Number */}
          <div className="label-container">
            <label htmlFor="phoneNumber">Phone Number *</label>
            <input
              type="tel"
              id="phoneNumber"
              name="phoneNumber"
              value={phoneNumber}
              onChange={(e) => setPhoneNumber(e.target.value)}
              className="input-field" 
              placeholder="111-111-1111"
              required
            />
          </div>

          {/* Checkbox for including Credit Card Info & Address*/}
          <div className="subscribe-checkbox">
            <label>
              <input
                type="checkbox"
                checked={includeCreditCardInfo}
                onChange={(e) => setIncludeCreditCardInfo(e.target.checked)}
              />
              Include Credit Card Information & Address
            </label>
          </div>

          {/* Credit Card Number (conditionally rendered) */}
          {includeCreditCardInfo && (
            <div className="label-container">
              <label htmlFor="creditCardNumber">Credit Card Number</label>
              <input
                type="text"
                id="creditCardNumber"
                name="creditCardNumber"
                value={creditCardNumber}
                onChange={(e) => setCreditCardNumber(e.target.value)}
                className="input-field" 
                placeholder="XXXX-XXXX-XXXX-XXXX"
              />
            </div>
          )}

          {/* Expiration Date (conditionally rendered) */}
          {includeCreditCardInfo && (
            <div className="label-container">
              <label htmlFor="expirationDate">Expiration Date</label>
              <input
                type="text"
                id="expirationDate"
                name="expirationDate"
                value={expirationDate}
                onChange={(e) => setExpirationDate(e.target.value)}
                className="input-field" 
                placeholder="MM/YYYY"
              />
            </div>
          )}

          {/* Street (conditionally rendered) */}
          {includeCreditCardInfo && (
            <div className="label-container">
              <label htmlFor="street">Street</label>
              <input
                type="text"
                id="street"
                name="street"
                value={street}
                onChange={(e) => setStreet(e.target.value)}
                className="input-field" 
                placeholder="Street"
              />
            </div>
          )}

          {/* City (conditionally rendered) */}
          {includeCreditCardInfo && (
            <div className="label-container">
              <label htmlFor="city">City</label>
              <input
                type="text"
                id="city"
                name="city"
                value={city}
                onChange={(e) => setCity(e.target.value)}
                className="input-field" 
                placeholder="City"
              />
            </div>
          )}

          {/* State (conditionally rendered) */}
          {includeCreditCardInfo && (
            <div className="label-container">
              <label htmlFor="state">State</label>
              <input
                type="text"
                id="state"
                name="state"
                value={state}
                onChange={(e) => setState(e.target.value)}
                className="input-field" 
                placeholder="State"
              />
            </div>
          )}

          {/* Zip Code (conditionally rendered) */}
          {includeCreditCardInfo && (
            <div className="label-container">
              <label htmlFor="zipCode">Zip Code</label>
              <input
                type="text"
                id="zipCode"
                name="zipCode"
                value={zipCode}
                onChange={(e) => setZipCode(e.target.value)}
                className="input-field" 
                placeholder="Zip Code"
              />
            </div>
          )}

          {/* Submit button */}
          <button type="submit">Create Account</button>
        </form>
      </div>

      {/* Login link */}
      <button className="link-btn" onClick={() => props.onFormSwitch('login')}>
        Already have an account? Login here.
      </button>
    </div>
  );
}
