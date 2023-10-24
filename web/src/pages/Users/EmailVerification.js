// EmailVerification.js
import React, { useState } from 'react';
import './EmailVerification.css';
import { useNavigate } from 'react-router-dom';



function EmailVerification() {
  const [code, setCode] = useState('');
  const [isValidCode, setIsValidCode] = useState(true);
  const navigate = useNavigate();

  const handleCodeChange = (e) => {
    const enteredCode = e.target.value;
    setCode(enteredCode);

    // Validates the code format (6 digits)
    const codeRegex = /^\d{6}$/;
    setIsValidCode(codeRegex.test(enteredCode));
  };

  const handleSubmit = (e) => {
    e.preventDefault();

   
    if (isValidCode) {
      
      console.log('Code entered:', code);
      navigate("/registrationsuccess"); //direct to registration success page
    } else {
     
      console.error('Invalid code format');
    }
  };

  return (
    <div className="registration-page">
      <h1>Registration</h1>
      <p>Enter the 6-digit code sent to your email address:</p>
      <form onSubmit={handleSubmit}>
        <input
          type="text"
          placeholder="XXXXXX"
          value={code}
          onChange={handleCodeChange}
        />
        {!isValidCode && <p className="error-message">Invalid code format</p>}
        <button type="submit">Register</button>
      </form>
    </div>
  );
}

export default EmailVerification;
