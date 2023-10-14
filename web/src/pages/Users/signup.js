  import React, { useState } from "react";
  import "./registration.css"; // Import the CSS file

  export const Signup = (props) => {
  
    // State variables
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [fullName, setFullName] = useState('');
    const [phoneNumber, setPhoneNumber] = useState('');
    const [subscribe, setSubscribe] = useState(false);
    const [homeStreet, setHomeStreet] = useState('');
    const [homeCity, setHomeCity] = useState('');
    const [homeState, setHomeState] = useState('');
    const [homeZipCode, setHomeZipCode] = useState('');

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

      const formatDate = expirationDate + "-01";

      const userData = {
        email,
        password,
        fullName,
        phoneNumber,
        homeCity,
        homeState,
        homeStreet,
        homeZipCode,
        subscribe,
        includeCreditCardInfo,
        creditCardNumber,
        formatDate,
        street,
        city,
        state,
        zipCode,
      };

      fetch('http://localhost:8000/api/register', {
        method: 'POST',
        body: JSON.stringify(userData),
        headers: {
          'Content-Type': 'application/json'
        }
      }).then(function (response) {
        if (response.status === 200) {
          /** signup successfully, 
           * Need to do somethin here
           * should pop-up window to inform user
           * or direct to sign up page 
           * Or other things
           * */ 

          return response.json();
        } else if (response.status === 406) {
          /**
           * means email already exists
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
      <div className="auth-container">
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

        {/* Home Address */}
  <div className="label-container">
    <label htmlFor="homeAddress" style={{ textDecoration: includeCreditCardInfo ? 'underline' : 'none' }}>Home Address</label>
    <div className="address-container">
      {/* Street */}
      <div className="label-container">
        <label htmlFor="street">Street</label>
        <input
          type="text"
          id="street"
          name="street"
          value={homeStreet}
          onChange={(e) => setHomeStreet(e.target.value)}
          className="input-field"
          placeholder="Street"
        />
      </div>

      {/* City */}
      <div className="label-container">
        <label htmlFor="city">City</label>
        <input
          type="text"
          id="city"
          name="city"
          value={homeCity}
          onChange={(e) => setHomeCity(e.target.value)}
          className="input-field"
          placeholder="City"
        />
      </div>

      {/* State */}
      <div className="label-container">
        <label htmlFor="state">State</label>
        <input
          type="text"
          id="state"
          name="state"
          value={homeState}
          onChange={(e) => setHomeState(e.target.value)}
          className="input-field"
          placeholder="State"
        />
      </div>

      {/* Zip Code */}
      <div className="label-container">
        <label htmlFor="zipCode">Zip Code</label>
        <input
          type="text"
          id="zipCode"
          name="zipCode"
          value={homeZipCode}
          onChange={(e) => setHomeZipCode(e.target.value)}
          className="input-field"
          placeholder="Zip Code"
        />
      </div>
    </div>
  </div>



            
            

            {/* Checkbox for including Credit Card Info & Address*/}
            <div className="subscribe-checkbox">
              <label>
                <input
                  type="checkbox"
                  checked={includeCreditCardInfo}
                  onChange={(e) => setIncludeCreditCardInfo(e.target.checked)}
                />
                Include Credit Card Information & Billing Address
              </label>
            </div>

            {/* Credit Card Number (conditionally rendered) */}
            {includeCreditCardInfo && (
              <div className="label-container">
                <label htmlFor="creditCardNumber">Credit Card Number</label>
                <input
                  type="text"
                  required
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
                  required
                  id="expirationDate"
                  name="expirationDate"
                  value={expirationDate}
                  onChange={(e) => setExpirationDate(e.target.value)}
                  className="input-field" 
                  placeholder="YYYY-MM"
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
                  required
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
                  required
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
                  required
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
                  required  
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

        {/* Checkbox for promotional emails */}
        <div className="subscribe-checkbox">
              <label>
                <input
                  type="checkbox"
                  checked={subscribe}
                  onChange={(e) => setSubscribe(e.target.checked)}
                />
                Subscribe to promotional emails
              </label>
            </div>

        {/* Login link */}
        <button className="link-btn" onClick={() => props.onFormSwitch('login')}>
          Already have an account? Login here.
        </button>
      </div>
    );
  }