import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom'; // import useHistory hook
import { encrypt } from './encryption';
import './Forgotpass.css'
import { FaHome } from 'react-icons/fa';

function Forgotpass() {
    const [email, setEmail] = useState('');
    const [newPassword, setNewPassword] = useState('');
    const [confirmNewPassword, setConfirmNewPassword] = useState('');
    const [errorMessage, setErrorMessage] = useState('');
    const [successMessage, setSuccessMessage] = useState('');
    
    const navigate = useNavigate();

    const handleEmailChange = (event) => {
        setEmail(event.target.value);
    };

    const handleNewPasswordChange = (event) => {
        setNewPassword(event.target.value);
    };

    const handleConfirmNewPasswordChange = (event) => {
        setConfirmNewPassword(event.target.value);
    };

    const handleSubmit = (event) => {
        event.preventDefault();
        if (newPassword !== confirmNewPassword) {
            setErrorMessage('Passwords do not match');
        } else {
            // TODO: handle password change logic
            const pass = (encrypt(newPassword));
            const confirmPass = (encrypt(confirmNewPassword));
            const userData = {
                email, pass, confirmPass
            };
            console.log(userData);

            fetch('http://localhost:8000/api/forgotpassword', {
                method: 'POST',
                body: JSON.stringify(userData),
                headers: {
                    'Content-Type': 'application/json'
                }
            });

            
            setSuccessMessage('Password successfully changed!');
        }
    };

    const handleLoginClick = () => {
        navigate('/login'); // redirect to login page
    };

    return (
        
        <form onSubmit={handleSubmit} className="forgot-pass">
             <button className="home-btn" onClick={() => navigate('/')} style={{backgroundColor: '#ff6600'}}><FaHome size={24}/></button>
            <label>
                Email:
                <input type="email" value={email} onChange={handleEmailChange} />
            </label>
            <label>
                New Password:
                <input type="password" value={newPassword} onChange={handleNewPasswordChange} />
            </label>
            <label>
                Re-Type New Password:
                <input type="password" value={confirmNewPassword} onChange={handleConfirmNewPasswordChange} />
            </label>
            {errorMessage && <p className="error-message">{errorMessage}</p>}
            {successMessage && <p className="success-message">{successMessage}</p>}
            {successMessage && <p>Go to <a href="/login" style={{color: '#ff6600', textDecoration: 'underline'}}>Login</a></p>} {/* added link to redirect to login page */}
            <button type="submit">Confirm</button>
            
        </form>
    );
}

export default Forgotpass;