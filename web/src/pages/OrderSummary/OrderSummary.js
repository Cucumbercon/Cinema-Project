import './OrderSummary.css';
import { React, useState, useEffect } from 'react';
import { useLocation, } from 'react-router-dom';

function Checkout() {
    const selectedSeatsCount = (useLocation().state.selectedSeatsCount);
    const [isDropdownVisible, setIsDropdownVisible] = useState(false);
    const [cardsList, setCardsList] = useState([]);

    const toggleDropdown = () => {
        setIsDropdownVisible(!isDropdownVisible);
    };

    const movieDetails = {
        title: "Avatar",
        adultTicket: selectedSeatsCount.adult,
        seniorTicket: selectedSeatsCount.senior,
        childTicket: selectedSeatsCount.child,
        tax: (selectedSeatsCount.adult * 15 + selectedSeatsCount.senior * 8 + selectedSeatsCount.child * 5) * 0.06,
        total: (selectedSeatsCount.adult * 15 + selectedSeatsCount.senior * 8 + selectedSeatsCount.child * 5) * 0.06 +
            (selectedSeatsCount.adult * 15 + selectedSeatsCount.senior * 8 + selectedSeatsCount.child * 5)
    };


    const saveCardButton = () => {
        const userID = localStorage.getItem('id');
        console.log(userID);
        if (!isDropdownVisible) {
            fetch(`http://localhost:8000/api/fetchCardInfo?id=${userID}`, {
                method: 'GET',
            }).then((response) => response.text())
                .then((data) => {
                    const parsedData = JSON.parse(data);

                    const cardNumbers = parsedData.map(card => card.cardNumber);

                    // Now set cardsList to the entire array
                    setCardsList(cardNumbers);
                    // console.log(cardNumbers[0]);

                })
                .catch((error) => {
                    console.error('Error searching movies:', error);
                });
        }
        console.log(cardsList);

    }

    const cancelButton = () => {
        window.history.back();
    }

    const checkoutButton = () => {

    }

    return (
        <div className="checkout-container">
            <div className="payment-section">
                <h1>Payment Information</h1>
                <p id="card">Credit/Debit Card</p>
                <button className="use-saved-card-btn" onClick={() => {
                    toggleDropdown();
                    saveCardButton();
                }}>
                    + Use Saved Card
                </button>

                {isDropdownVisible && (
                    <div>
                        <ul className="dropdown-list">
                            {cardsList.map((card, index) => (
                                <li id="cardList" key={index} onClick={() => console.log(`Selected Card: ${card}`)}>
                                    {card}
                                </li>
                            ))}
                        </ul>
                    </div>
                )}
                <hr />

                <div className="checkout-buttons" >
                    <form onSubmit={checkoutButton}>
                        <div className="card-input">
                            <input type="text" id="cardNumber" placeholder="Card Number" required />
                        </div>
                        <div className="card-details" >
                            <input type="text" placeholder="MM/YY" required />
                            <input type="text" placeholder="Security Number" required />
                            <input type="text" placeholder="ZIP Code" required />
                        </div>
                        <div className="promo-code-input">
                            <input id="promoText" type="text" placeholder="Promo Code" />
                            <button className="apply-promo-btn">Apply</button>
                        </div>
                        <div className="checanButton">
                            <div className='checkoutDiv'>
                                <button className="checkout-btn" type="submit">Checkout</button>
                            </div>
                            <div className='cancelDiv'>
                                <button className="cancel-btn" onClick={cancelButton}>Cancel</button>
                            </div>
                        </div>


                    </form>
                    {/* <button className="checkout-btn" type="submit" >Checkout</button> */}
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
