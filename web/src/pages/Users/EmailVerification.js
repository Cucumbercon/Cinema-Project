// EmailVerification.js
import React, { useState } from 'react';
import './EmailVerification.css';

function EmailVerification() {
  const [code, setCode] = useState('');
  const [isValidCode, setIsValidCode] = useState(true);

  const handleCodeChange = (e) => {
    const enteredCode = e.target.value;
    setCode(enteredCode);

    // Validate the code format (6 digits)
    const codeRegex = /^\d{6}$/;
    setIsValidCode(codeRegex.test(enteredCode));
  };

  const handleSubmit = (e) => {
    e.preventDefault();

    // Check if the code is valid
    if (isValidCode) {
      // Code is valid; you can proceed with the registration process
      // For this example, we'll just log the code to the console
      console.log('Code entered:', code);
    } else {
      // Code is not valid; you can show an error message to the user
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
          placeholder="Enter 6-digit code"
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
