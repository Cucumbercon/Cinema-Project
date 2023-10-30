import React, { useState } from 'react';
import './Forgotpass.css'

function Forgotpass() {
    const [email, setEmail] = useState('');
    const [newPassword, setNewPassword] = useState('');
    const [confirmNewPassword, setConfirmNewPassword] = useState('');
    const [errorMessage, setErrorMessage] = useState('');
    const [successMessage, setSuccessMessage] = useState('');

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
        if (email !== 'example@example.com') { // TODO: check if email exists in database
            // TODO: check if email exists in database
            setErrorMessage('This email is not registered');
            // TODO: check if email exists in database


        } else if (newPassword !== confirmNewPassword) {
            setErrorMessage('Passwords do not match');
        } else {
            // TODO: handle password change logic
            setSuccessMessage('Password successfully changed!');
        }
    };

    return (
        <form onSubmit={handleSubmit} className="forgot-pass">
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
            <button type="submit">Confirm</button>
        </form>
    );
}

export default Forgotpass;
