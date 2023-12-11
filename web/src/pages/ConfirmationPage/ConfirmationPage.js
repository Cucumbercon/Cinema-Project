import React from 'react';
import { useLocation, useNavigate, Link } from 'react-router-dom'; // Assuming you're using React Router
import './ConfirmationPage.css';

function ConfirmationPage() {

    const location = useLocation();

    const confirmationData = {
        confirmationNumber: "XXXXX-XXX-XXX-XXX",
        movieTitle: location.state.movie.title,
        date: location.state.selectedDate,
        time: location.state.selectedTime,
        seats: location.state.seat,
        totalCost: "$" + location.state.total,
        email: location.state.email,
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
            <p>
                <h3>A copy of your ticket has been sent to </h3>
                <span>{confirmationData.email}</span>
            </p>
            <p>
                <Link to="/" className="orange-underlined-link">Go back to homepage</Link>
            </p>
        </div>
    );
}

export default ConfirmationPage;
