import React, { useState, useEffect } from "react";
import { encrypt } from './encryption';
import "./UpdateProfile.css";
import { FaHome } from 'react-icons/fa';
import { useNavigate } from "react-router-dom";

export const UpdateProfile = (props) => {
  const navigate = useNavigate();
  const [fullName, setFullName] = useState('');
  const [email, setEmail] = useState('');
  const [phoneNumber, setPhoneNumber] = useState('');
  const [subscribe, setSubscribe] = useState(false);
  const [creditCardNumber, setCreditCardNumber] = useState('');
  const [oldCreditCardNumber, setOldCreditCardNumber] = useState('');
  const [expirationDate, setExpirationDate] = useState('');
  const [password, setPassword] = useState('');
  const [confirmPassword, setConfirmPassword] = useState('');
  const [paymentID, setPaymentID] = useState('');
  const [street, setStreet] = useState('');
  const [city, setCity] = useState('');
  const [state, setState] = useState('');
  const [zipCode, setZipCode] = useState('');
  const [homeStreet, setHomeStreet] = useState('');
  const [homeCity, setHomeCity] = useState('');
  const [homeState, setHomeState] = useState('');
  const [homeZipCode, setHomeZipCode] = useState('');
  const [currentPassword, setCurrentPassword] = useState('');
  const [updateSuccess, setUpdateSuccess] = useState(false);
  const [creditCards, setCreditCards] = useState([]);
  const [selectedCreditCard, setSelectedCreditCard] = useState('');
  const [isCardLimitReached, setIsCardLimitReached] = useState(false);




  // ToDO: function to load the user's existing information
  // storageUtils.js

  function getUserIdFromStorage() {
    const userId = localStorage.getItem('id');
    console.log(userId);
    return userId ? parseInt(userId, 10) : null;
  }

  const loadUserProfile = () => {
    const userId = getUserIdFromStorage();
    fetch(`http://localhost:8000/api/loadprofile?userId=${userId}`, {
      method: 'GET'
    })
      .then((response) => response.text())
      .then((data) => {
        const parsedData = JSON.parse(data);
        setFullName(parsedData.fullName);
        setEmail(parsedData.email);
        setPhoneNumber(parsedData.phoneNumber);
        setSubscribe(parsedData.subscribe);
        setHomeStreet(parsedData.street);
        setHomeCity(parsedData.city);
        setHomeState(parsedData.state);
        setHomeZipCode(parsedData.zipCode);

        console.log('loadUserProfile', fullName)
      })
      .catch((error) => {
        console.error('Error sending message to Spring:', error)
      });
  };

  const loadUserCard = () => {
    const userId = getUserIdFromStorage();
    fetch(`http://localhost:8000/api/loadprofilecard?userId=${userId}`, {
      method: 'GET'
    })
      .then((response) => response.text())
      .then((data) => {
        const parsedData = JSON.parse(data);
        console.log(parsedData);
        setCreditCards(parsedData);
        setIsCardLimitReached(parsedData.length >= 3);
      })
      .catch((error) => {
        console.error('Error sending message to Spring:', error)
      });
  };

  // TODO: function to update the user's information
  const handleSubmit = (e) => {
    e.preventDefault();
    // Check if password and confirmPassword match and update the user's password
    if (password === confirmPassword) {
      
      
      // Update the user's password here
      const pass = encrypt(password);
      const passConfirm = encrypt(confirmPassword);
      const passCurrent = encrypt(currentPassword);
      const encryptedCardNumber = encrypt(creditCardNumber);
      const userId = getUserIdFromStorage();
      const changeInfo = {
        userId,
        paymentID,
        fullName,
        email,
        phoneNumber,
        subscribe,
        encryptedCardNumber,
        oldCreditCardNumber,
        expirationDate,
        pass,
        passConfirm,
        selectedCreditCard,
        street,
        city,
        state,
        zipCode,
        homeStreet,
        homeCity,
        homeState,
        homeZipCode,
        passCurrent,
      };
      console.log(changeInfo)
      fetch('http://localhost:8000/api/updateprofile', {
        method: 'POST',
        body: JSON.stringify(changeInfo),
        headers: {
          'Content-Type': 'application/json'
        }
      })
      .then((response) => {
        if (response.ok) {
            setUpdateSuccess(true);
            // 重新加载用户资料和信用卡信息
            loadUserProfile();
            loadUserCard();
            alert('Profile updated successfully!');
        } else {
            setUpdateSuccess(false);
            alert('There was an error updating the profile.');
        }
    })
    .catch((error) => {
        console.error('Error:', error);
    });
    } else {
      // Display an error message indicating that passwords do not match
      console.error('Password does not match with confirm password');
    }
  };

  // Calls the loadUserProfile function when the component mounts to load existing data
  useEffect(() => {
    getUserIdFromStorage();
    loadUserProfile();
    loadUserCard();
    console.log(creditCards);
  }, []);


  const handleCardSelection = (selectedCardNumber) => {
    const card = creditCards.find(c => c.cardNumber === selectedCardNumber);
    if (card) {
      setPaymentID(card.paymentID);
      setOldCreditCardNumber(card.cardNumber);
      setCreditCardNumber(card.cardNumber);
      setExpirationDate(card.expDate);
      setStreet(card.billingStreet);
      setCity(card.billingCity);
      setState(card.billingState);
      setZipCode(card.billingZipCode);
      setSelectedCreditCard(selectedCardNumber);
    }
  };

  const handleAddNewCard = () => {
    setCreditCardNumber('');
    setExpirationDate('');
    setStreet('');
    setCity('');
    setState('');
    setZipCode('');
    setSelectedCreditCard('new'); 
  };

  return (
    <div className="form-box">
      <form className="update-profile-form" onSubmit={handleSubmit}>
      <button className="home-btn" onClick={() => navigate('/')} style={{backgroundColor: '#ff6600'}}><FaHome size={24}/></button>
        <div className="form-groups">
          <div class="form-group-panel">
            {/* Email */}
            <div className="input-container">
              <label htmlFor="email">Email</label>
              <input
                value={email}
                readOnly
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
            {/* Current Password */}
            <div className="input-container">
              <label htmlFor="currentPassword">Current Password</label>
              <input
                value={currentPassword}
                onChange={(e) => setCurrentPassword(e.target.value)}
                type="currentpassword" // Use type "password" to hide entered characters
                placeholder="Current Password"
                id="currentPassword"
                name="currentPassword"
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
            {/* Text for current password */}
            <p className="password-description" style={{color: "red"}}>
                Please input <u>current password</u> to change passwords.
              </p>
            </div>
              

          <div class="form-group-break"></div>
          <div class="form-group-panel">
            {/* Payment Card */}
            <div className="input-container">
              <label htmlFor="paymentCard">Payment Card</label>
              <select
                value={selectedCreditCard}
                onChange={(e) => e.target.value === 'new' ? handleAddNewCard() : handleCardSelection(e.target.value)}
                id="paymentCard"
                name="paymentCard"
                className="select-input"
              >
                <option value="">Select Payment Card</option>
                {!isCardLimitReached && <option value="new">Add Payment Card</option>}
                {creditCards.map((card, index) => (
                  <option key={index} value={card.cardNumber}>
                    {card.cardNumber}
                  </option>
                ))}
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
                placeholder="YYYY-MM"
                id="expirationDate"
                name="expirationDate"
              />
            </div>
            {/* Street */}
            <div className="input-container">
              <label htmlFor="street">Street</label>
              <input
                value={street}
                onChange={(e) => setStreet(e.target.value)}
                type="text"
                placeholder="Street"
                id="street"
                name="street"
              />
            </div>
            {/* City */}
            <div className="input-container">
              <label htmlFor="city">City</label>
              <input
                value={city}
                onChange={(e) => setCity(e.target.value)}
                type="text"
                placeholder="City"
                id="city"
                name="city"
              />
            </div>
            {/* State */}
            <div className="input-container">
              <label htmlFor="state">State</label>
              <input
                value={state}
                onChange={(e) => setState(e.target.value)}
                type="text"
                placeholder="State"
                id="state"
                name="state"
              />
            </div>
            {/* Zip Code */}
            <div className="input-container">
              <label htmlFor="zipCode">Zip Code</label>
              <input
                value={zipCode}
                onChange={(e) => setZipCode(e.target.value)}
                type="text"
                placeholder="Zip Code"
                id="zipCode"
                name="zipCode"
              />
            </div>
          </div>
          <div class="form-group-break"></div>
          <div class="form-group-panel">
            {/* Home Street */}
            <div className="input-container">
              <label htmlFor="homeStreet">Home Street</label>
              <input
                value={homeStreet}
                onChange={(e) => setHomeStreet(e.target.value)}
                type="text"
                placeholder="Home Street"
                id="homeStreet"
                name="homeStreet"
              />
            </div>
            {/* Home City */}
            <div className="input-container">
              <label htmlFor="homeCity">Home City</label>
              <input
                value={homeCity}
                onChange={(e) => setHomeCity(e.target.value)}
                type="text"
                placeholder="Home City"
                id="homeCity"
                name="homeCity"
              />
            </div>
            {/* Home State */}
            <div className="input-container">
              <label htmlFor="homeState">Home State</label>
              <input
                value={homeState}
                onChange={(e) => setHomeState(e.target.value)}
                type="text"
                placeholder="Home State"
                id="homeState"
                name="homeState"
              />
            </div>
            {/* Home Zip Code */}
            <div className="input-container">
              <label htmlFor="homeZipCode">Home Zip Code</label>
              <input
                value={homeZipCode}
                onChange={(e) => setHomeZipCode(e.target.value)}
                type="text"
                placeholder="Home Zip Code"
                id="homeZipCode"
                name="homeZipCode"
              />
            </div>
          </div>
        {/* Success Message */}
        {/*updateSuccess && (
          <div className="success-message">
            You have successfully updated your profile!
          </div>
        )}*/}


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
  );
};
