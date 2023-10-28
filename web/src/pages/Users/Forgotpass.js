import React, { useState } from 'react';
import './Forgotpass.css'

function Forgotpass() {
    const [currentPassword, setCurrentPassword] = useState('');
    const [newPassword, setNewPassword] = useState('');
    const [confirmNewPassword, setConfirmNewPassword] = useState('');

    const handleCurrentPasswordChange = (event) => {
        setCurrentPassword(event.target.value);
    };

    const handleNewPasswordChange = (event) => {
        setNewPassword(event.target.value);
    };

    const handleConfirmNewPasswordChange = (event) => {
        setConfirmNewPassword(event.target.value);
    };

    const handleSubmit = (event) => {
        event.preventDefault();
        // TODO: handle password change logic
    };

    return (
        <form onSubmit={handleSubmit}>
            <label>
                Current Password:
                <input type="password" value={currentPassword} onChange={handleCurrentPasswordChange} />
            </label>
            <label>
                New Password:
                <input type="password" value={newPassword} onChange={handleNewPasswordChange} />
            </label>
            <label>
                Re-Type New Password:
                <input type="password" value={confirmNewPassword} onChange={handleConfirmNewPasswordChange} />
            </label>
            <button type="submit">Confirm</button>
        </form>
    );
}

export default Forgotpass;
