import React, { useState } from "react";
import "./adminSignup.css";
import { encrypt } from '../Users/encryption';
import { useNavigate } from "react-router-dom";

export const AdminSignup = (props) => {
  const navigate = useNavigate();


  // State variables
  const [email, setEmail] = useState('');
  const [showEmailExistsModal, setShowEmailExistsModal] = useState(false);
  const [plainPassword, setPlainPassword] = useState('');
  const [fullName, setFullName] = useState('');
  const [phoneNumber, setPhoneNumber] = useState('');
  const [subscribe, setSubscribe] = useState(false);
  const [homeStreet, setHomeStreet] = useState('');
  const [homeCity, setHomeCity] = useState('');
  const [homeState, setHomeState] = useState('');
  const [homeZipCode, setHomeZipCode] = useState('');
  let creditCardNumber = '';
  let formatDate = '';

  // Optional fields

  const [includeCreditCardInfo, setIncludeCreditCardInfo] = useState(false);
  const [plainCreditCardNumber, setPlainCreditCardNumber] = useState('');
  const [expirationDate, setExpirationDate] = useState('');
  const [street, setStreet] = useState('');
  const [city, setCity] = useState('');
  const [state, setState] = useState('');
  const [zipCode, setZipCode] = useState('');

  // Form submission handler
  const handleSubmit = (e) => {
    e.preventDefault();

    const password = (encrypt(plainPassword));

    if (plainCreditCardNumber !== '') {
      creditCardNumber = encrypt(plainCreditCardNumber);
    }

    if (expirationDate !== '') {
      console.log(expirationDate + "-01");
      formatDate = encrypt(expirationDate + "-01");
    }


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

    console.log(userData);

    fetch('http://localhost:8000/api/register', {
      method: 'POST',
      body: JSON.stringify(userData),
      headers: {
        'Content-Type': 'application/json'
      }
    }).then(function (response) {
      if (response.status === 200) {
        navigate('/emailverification'); // redirecting to email verification page

        return response.json();
      } else if (response.status === 406) {

        setShowEmailExistsModal(true); // Show the email exists modal

        console.error('Request failed with status: ', response);
      } else {

        navigate('/notfound'); // redirecting to not found page

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
          <div className={`form-groups ${includeCreditCardInfo ? 'form-groups-height-selected' :'form-groups-height'}`}>
            <div class="form-group-panel">
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
                  value={plainPassword}
                  onChange={(e) => setPlainPassword(e.target.value)}
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
            </div>
            <div class="form-group-break"></div>
            <div class="form-group-panel">

              {/* Home Address */}
              <div className="label-container">

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

            </div>         
        
          </div>


          {showEmailExistsModal && (
            <div className="modal">
              <div className="modal-content">
                <p>Your email has already been used. Please choose a different email.</p>
                <button onClick={() => setShowEmailExistsModal(false)}>Close</button>
              </div>
            </div>
          )}

         
          {/* Submit button */}
          <button type="submit">Create Account</button>
        </form>
      </div>

      {/* Login link */}
      <button className="link-btn" onClick={() => navigate('/login')}>
        Already have an account? Login here.
      </button>
    </div>
  );
}