import React from 'react';
import './OrderSummary.css';

function OrderSummary() {
    const movieDetails = {
        poster: './web/src/pages/OrderSummary/MoviePoster.png',
        title: "The Creator",
        adultTicket: 3,
        seniorTicket: 0,
        childTicket: 1,
        tax: 2.5,
        total: 45
    };

    return (
        <div className="order-summary-container">
            <div className="movie-section">
                <img src={movieDetails.poster} alt={movieDetails.title + ' poster'} className="movie-poster"/>
                <h2>{movieDetails.title}</h2>
            </div>
            <div className="summary-section">
                <h1>Order Summary</h1>
                <div className="ticket-details">
                    <p>Adult Ticket x {movieDetails.adultTicket}</p>
                    <p>Senior Ticket x {movieDetails.seniorTicket}</p>
                    <p>Child Ticket x {movieDetails.childTicket}</p>
                    <p>Taxes: ${movieDetails.tax.toFixed(2)}</p>
                    <hr/>
                    <p className="total">Total: ${movieDetails.total.toFixed(2)}</p>
                </div>
                <div className="buttons">
                    <button className="confirm-btn">Confirm</button>
                    <button className="cancel-btn">Cancel</button>
                </div>
            </div>
        </div>
    );
}

export default OrderSummary;