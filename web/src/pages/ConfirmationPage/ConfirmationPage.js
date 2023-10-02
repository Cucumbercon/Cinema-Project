import React from 'react';
import './ConfirmationPage.css';

function ConfirmationPage() {

    const confirmationData = {
        confirmationNumber: "XXXXX-XXX-XXX-XXX",
        movieTitle: " Avatar",
        date: " 10/01/2023",
        time: " 3:15pm",
        seats: " A1, A2, B4, B5",
        totalCost: " $40",
        email: " example@example.com"
    };

    return (
        <div className="confirmation-page">
            <h1>Thank you for booking with us!</h1>
            <div className="spacer"></div>
            <h2>Your Booking Confirmation Number is: <span>#{confirmationData.confirmationNumber}</span></h2>
            <div className="booking-details">
                <p><strong>Movie Title:</strong> {confirmationData.movieTitle}</p>
                <p><strong>Date:</strong> {confirmationData.date}</p>
                <p><strong>Time:</strong> {confirmationData.time}</p>
                <p><strong>Seats:</strong> {confirmationData.seats}</p>
                <p><strong>Total Cost:</strong> {confirmationData.totalCost}</p>
            </div>
            <p> <h3>A copy of your ticket has been sent to </ h3> <span>{confirmationData.email}</span></p>
        </div>
    );
}

export default ConfirmationPage;
