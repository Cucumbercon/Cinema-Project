import React, { useState } from "react";
import "./registration.css";
import { encrypt } from './encryption';
import { useNavigate } from "react-router-dom";
import { FaHome } from 'react-icons/fa';

export const Signup = (props) => {
  const navigate = useNavigate();
  const [passwordMatchError, setPasswordMatchError] = useState(false);

  // State variables
  const [email, setEmail] = useState('');
  const [showEmailExistsModal, setShowEmailExistsModal] = useState(false);
  const [plainPassword, setPlainPassword] = useState('');
  const [currentPassword, setCurrentPassword] = useState('');
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
    

    if (plainPassword !== currentPassword) {
      // Passwords do not match, set the error
      alert('Passwords do not match');
      return;
    }

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
        sendEmailToServer();
        navigate('/emailverification', { state: { email: email } }); // redirecting to email verification page

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
  const sendEmailToServer = async () => {
    const requestData = {
        email: email, // 使用之前从state中获取的email
        type: 1
    };

    try {
        const response = await fetch('http://localhost:8000/api/sendEmail', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(requestData)
        });

        if (response.ok) {
            const responseData = await response.json();
            console.log(responseData);
            // 处理响应数据，例如显示成功消息或进行其他操作
        } else {
            console.error('Request failed:', response.statusText);
            // 处理错误情况，例如显示错误消息
        }
    } catch (error) {
        console.error('There was an error sending the request:', error);
    }
};

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

              <div className="label-container">
                <label htmlFor="currentpassword">Re-type Password *</label>
                <input
                  type="password"
                  id="currentpassword"
                  name="currentpassword"
                  value={currentPassword}
                  onChange={(e) => setCurrentPassword(e.target.value)}
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
            
            {includeCreditCardInfo &&<div class="form-group-break"></div>}
            {includeCreditCardInfo && <div class="form-group-panel">
              {/* Credit Card Number (conditionally rendered) */}
              
                <div className="label-container">
                  <label htmlFor="creditCardNumber">Credit Card Number</label>
                  <input
                    type="text"
                    required
                    id="creditCardNumber"
                    name="creditCardNumber"
                    value={plainCreditCardNumber}
                    onChange={(e) => setPlainCreditCardNumber(e.target.value)}
                    className="input-field"
                    placeholder="XXXX-XXXX-XXXX-XXXX"
                  />
                </div>

              {/* Expiration Date (conditionally rendered) */}
        
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
              

              {/* Street (conditionally rendered) */}
           
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
              

              {/* City (conditionally rendered) */}
            
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
              

              {/* State (conditionally rendered) */}
            
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
              

              {/* Zip Code (conditionally rendered) */}
           
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
              
            </div>}
          </div>


          {showEmailExistsModal && (
            <div className="modal">
              <div className="modal-content">
                <p style={{color: "black"}}>Your email has already been used. Please choose a different email.</p>
                <button onClick={() => setShowEmailExistsModal(false)}>Close</button>
              </div>
            </div>
          )}

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
      <button className="link-btn" onClick={() => navigate('/login')}>
        Already have an account? Login here.
      </button>
    </div>
  );
}