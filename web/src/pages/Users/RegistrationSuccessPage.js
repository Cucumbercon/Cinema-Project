import React from 'react';
import { Link } from 'react-router-dom';
import './RegistrationSuccessPage.css'; 

function RegistrationSuccessPage() {
  return (
    <div className="display-center"> 
      <h1>Registration Successful</h1>
      <p>Your account has been successfully registered!</p>
      <Link to="/moviebooking">
        <button style={{backgroundColor: "#ff6600"}}>Back to Movie Booking</button>
      </Link>
    </div>
  );
}

export default RegistrationSuccessPage;
