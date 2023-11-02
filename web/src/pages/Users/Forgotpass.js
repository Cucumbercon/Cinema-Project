import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { encrypt } from './encryption';
import './Forgotpass.css';
import { FaHome } from 'react-icons/fa';

function Forgotpass() {
    const [email, setEmail] = useState('');
    const [newPassword, setNewPassword] = useState('');
    const [confirmNewPassword, setConfirmNewPassword] = useState('');
    const [verificationCode, setVerificationCode] = useState('');
    const [showVerificationPopup, setShowVerificationPopup] = useState(false);
    const [errorMessage, setErrorMessage] = useState('');
    const [successMessage, setSuccessMessage] = useState('');
    const [isValidCode, setIsValidCode] = useState(true);

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

    const handleVerificationCodeChange = (event) => {
        setVerificationCode(event.target.value);

        const codeRegex = /^\d{6}$/;
        setIsValidCode(codeRegex.test(verificationCode));
    };

    const sendVerificationCode = () => {
        // TODO: Send the verification code to the user's email 
        // For now, generating a random 5-digit code
        const randomCode = Math.floor(10000 + Math.random() * 90000);
        console.log('Verification code sent to email:', randomCode);

        // Show the verification code popup
        setShowVerificationPopup(true);
    };

    const handleSubmit = async (event) => {
        event.preventDefault();
        if (newPassword !== confirmNewPassword) {
            setErrorMessage('Passwords do not match');
        } else {
            const requestData = {
                email: email
            };
            try {
                const response = await fetch('http://localhost:8000/api/sendForgotEmail', {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json'
                    },
                    body: JSON.stringify(requestData)
                });
        
                if (response.ok) {
                    const responseData = await response.json();
                    console.log(responseData);
                } else {
                    console.error('Request failed:', response.statusText);
                }
            } catch (error) {
                console.error('There was an error sending the request:', error);
            }
            setShowVerificationPopup(true);
        }
    };

    const handleVerificationSubmit = async (e) => {
        e.preventDefault();
        // checks the verification code please replace this with the actual verification logic
        if (isValidCode) { // Replace '12345' with the actual code
            
            const pass = encrypt(newPassword);
            const userData = {
                email,
                pass,
                verificationCode,
            };

            const response = await fetch('http://localhost:8000/api/verifyForgotCode', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(userData),
            });
            console.log(userData);

            if (response.ok) {

            } else {
                console.error('Invalid verification code');
            }

            //  the password change message
            setSuccessMessage('Password successfully changed! <a href="/login">Login</a>');

        } else {
            //wrong verification code
            setErrorMessage('Invalid verification code format');
        }
    };

    const handleLoginClick = () => {
        navigate('/login');
    };

    return (
        <form onSubmit={showVerificationPopup ? handleVerificationSubmit : handleSubmit} className="forgot-pass">
            <button className="home-btn" onClick={() => navigate('/')} style={{ backgroundColor: '#ff6600' }}>
                <FaHome size={24} />
            </button>
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
            {showVerificationPopup && (
                <label className="verification-code-label">
                    Verification Code:
                    <input type="text" value={verificationCode} onChange={handleVerificationCodeChange} placeholder="XXXXX" />
                </label>
            )}

            {errorMessage && <p className="error-message">{errorMessage}</p>}
            {successMessage && (
                <p className="success-message" dangerouslySetInnerHTML={{ __html: successMessage }}></p>
            )}
            {showVerificationPopup ? (
                <button type="button" onClick={handleVerificationSubmit} style={{ backgroundColor: '#ff6600' }}>
                    Confirm Code
                </button>
            ) : (
                <button type="submit">Send Email</button>
            )}
        </form>
    );
}

export default Forgotpass;
