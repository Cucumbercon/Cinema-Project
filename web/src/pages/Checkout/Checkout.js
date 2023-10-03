import React from 'react';
import './Checkout.css';

function Checkout() {
    const movieDetails = {
        title: "Avatar",
        adultTicket: 3,
        seniorTicket: 0,
        childTicket: 1,
        tax: 2.5,
        total: 45
    };

    return (
        <div className="checkout-container">
            <div className="payment-section">
                <h1>Payment Information</h1>
                <p>Credit/Debit Card</p>
                <div className="card-input">
                    <input type="text" placeholder="Card Number" />
                    <button className="use-saved-card-btn">+ Use Saved Card</button>
                </div>
                <div className="card-details">
                    <input type="text" placeholder="MM/YY" />
                    <input type="text" placeholder="Security Number" />
                    <input type="text" placeholder="ZIP Code" />
                </div>
                <div className="promo-code-input">
                    <input type="text" placeholder="Promo Code" />
                    <button className="apply-promo-btn">Apply</button>
                </div>
                <div className="checkout-buttons">
                    <button className="checkout-btn">Checkout</button>
                    <button className="cancel-btn">Cancel</button>
                </div>
            </div>
            <div className="order-summary-section">
                <h1>Order Summary</h1>
                <p>{movieDetails.title}</p>
                <div className="ticket-details">
                    <p>Adult Ticket x {movieDetails.adultTicket}</p>
                    <p>Senior Ticket x {movieDetails.seniorTicket}</p>
                    <p>Child Ticket x {movieDetails.childTicket}</p>
                    <p>Taxes: ${movieDetails.tax.toFixed(2)}</p>
                    <hr />
                    <p className="total">Total: ${movieDetails.total.toFixed(2)}</p>
                </div>
            </div>
        </div>
    );
}

export default Checkout;
