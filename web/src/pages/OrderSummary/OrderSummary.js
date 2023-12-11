import './OrderSummary.css';
import { React, useState, useEffect } from 'react';
import { useLocation, useNavigate } from 'react-router-dom';


function Checkout() {
    const navigate = useNavigate();

    const selectedSeatsCount = useLocation().state?.selectedSeatsCount;
    const userID = localStorage.getItem('id');
    const selectedSeat = (useLocation().state?.selectedSeat);
    console.log(selectedSeat);
    const [promoText, setPromoText] = useState('');
    const [email, setEmail] = useState('');
    const [isDropdownVisible, setIsDropdownVisible] = useState(false);
    const [cardsList, setCardsList] = useState([]);
    const location = useLocation();
    const [scheduleId, setScheduleId] = useState(null);

    const toggleDropdown = () => {
        setIsDropdownVisible(!isDropdownVisible);
    };

    const [movieDetails, setMovieDetails] = useState({
        title: location.state.movie.title,
        adultTicket: selectedSeatsCount.adult,
        seniorTicket: selectedSeatsCount.senior,
        childTicket: selectedSeatsCount.child,
        tax: (selectedSeatsCount.adult * 15 + selectedSeatsCount.senior * 8 + selectedSeatsCount.child * 5) * 0.06,
        discount: 0,
        total: (selectedSeatsCount.adult * 15 + selectedSeatsCount.senior * 8 + selectedSeatsCount.child * 5) * 0.06 +
            (selectedSeatsCount.adult * 15 + selectedSeatsCount.senior * 8 + selectedSeatsCount.child * 5)
    });

    const amount = selectedSeatsCount.adult + selectedSeatsCount.senior + selectedSeatsCount.child;

    const orderData = {
        user_id: localStorage.getItem("id"),
        payment_id: "",
        promote_id: 1,
        ticket_amount: amount,
        total: movieDetails.total,
        describe: "",
    }

    const applyPromoButton = () => {
        fetch(`http://localhost:8000/api/applyPromotion?code=${promoText}`, {
            method: 'GET',
        }).then((response) => response.text())
            .then((data) => {
                const parsedData = JSON.parse(data);

                setMovieDetails((prevDetails) => ({
                    ...prevDetails,
                    discount: parsedData.discount,
                    total: (selectedSeatsCount.adult * 15 + selectedSeatsCount.senior * 8 + selectedSeatsCount.child * 5) * 0.06 +
                        (selectedSeatsCount.adult * 15 + selectedSeatsCount.senior * 8 + selectedSeatsCount.child * 5) - parsedData.discount * 0.
                }));
                orderData.payment_id = parsedData.id;
            })
            .catch((error) => {
                console.error('Error searching movies:', error);
            });
    }


    const saveCardButton = () => {
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

    function handleCardClick(card) {
        const result = window.confirm(`Selected Card: ${card}. \nDo you want to proceed?`);

        if (result) {
            const movie = location.state.movie;
            orderData.payment_id = card;

            // get the user email
            fetch(`http://localhost:8000/api/getEmail?id=${userID}`, {
                method: 'GET',
            }).then((response) => response.text())
                .then((data) => {

                    // add order to db
                    fetch(`http://localhost:8000/api/addOrder`, {
                        method: 'POST',
                        body: JSON.stringify({
                            orderData,
                        }),
                    }).then((responsee) => responsee.text())
                        .then((dataa) => {
                            navigate("/ConfirmationPage", {
                                state: {
                                    movie,
                                    seat: selectedSeat,
                                    total: movieDetails.total,
                                    email: data,
                                },
                            });
                        })
                        .catch((error) => {
                            console.error('Error occurred:', error);
                            alert('Error occurs: ', error);
                        });

                })
                .catch((error) => {
                    console.error('Error occurred:', error);
                    alert('Error occurs: ', error);
                });



            // save the seat info into db


            // send order confirmation 
            fetch(`http://localhost:8000/api/sendOrderConfirmation`, {
                method: 'POST',
                body: JSON.stringify({
                    movie,
                    userID,
                    price: orderData.total,
                }),
            }).then((response) => response.text())
                .then((data) => {
                    console.log(data);
                })
                .catch((error) => {
                    console.error('Error occurred:', error);
                    alert('Error occurs: ', error);
                });
        } else {
            alert(`Payment has been cancelled.`);
        }
    }

    const cancelButton = () => {
        window.history.back();
    }

    const checkoutButton = (event) => {
        event.preventDefault();

        const result = window.confirm(`Do you want to proceed the order?`);

        if (result) {
            const movie = location.state.movie;

            // get the user email
            fetch(`http://localhost:8000/api/getEmail?id=${userID}`, {
                method: 'GET',
            }).then((response) => response.text())
                .then((data) => {

                    // add order to db
                    fetch(`http://localhost:8000/api/addOrder`, {
                        method: 'POST',
                        body: JSON.stringify({
                            orderData,
                        }),
                    }).then((responsee) => responsee.text())
                        .then((dataa) => {
                            navigate("/ConfirmationPage", {
                                state: {
                                    movie,
                                    seat: selectedSeat,
                                    total: movieDetails.total,
                                    email: data,
                                },
                            });
                        })
                        .catch((error) => {
                            console.error('Error occurred:', error);
                            alert('Error occurs: ', error);
                        });

                })
                .catch((error) => {
                    console.error('Error occurred:', error);
                    alert('Error occurs: ', error);
                });



            // save the seat info into db


            // send order confirmation 
            fetch(`http://localhost:8000/api/sendOrderConfirmation`, {
                method: 'POST',
                body: JSON.stringify({
                    movie,
                    userID,
                    price: orderData.total,
                }),
            }).then((response) => response.text())
                .then((data) => {
                    console.log(data);
                })
                .catch((error) => {
                    console.error('Error occurred:', error);
                    alert('Error occurs: ', error);
                });
        } else {
            alert(`Payment has been cancelled.`);
        }
    }
    useEffect(() => {
        const storedScheduleId = localStorage.getItem('scheduleId');
        if (storedScheduleId) {
            setScheduleId(storedScheduleId);
            console.log("schedule_id = " + storedScheduleId);
        } else {
            // err in getting schedule_id
            console.log('No scheduleId found in localStorage');
        }
    }, []); // only running at the beginning

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
                        <ul >
                            {cardsList.map((card, index) => (
                                <li id="cardList" key={index} onClick={() => handleCardClick(card)}>
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
                <p id="summaryTitle">{location.state.movie.title}</p>
                <div className="ticket-details">
                    <div className="promo-code-input">
                        <input id="promoText" type="text" placeholder="Promo Code" onChange={(e) => setPromoText(e.target.value)}
                        />
                        <button className="apply-promo-btn" onClick={applyPromoButton}>Apply</button>
                    </div>
                    <p>Adult Ticket x {movieDetails.adultTicket}: ${movieDetails.adultTicket * 15}</p>
                    <p>Senior Ticket x {movieDetails.seniorTicket}: ${movieDetails.seniorTicket * 8}</p>
                    <p>Child Ticket x {movieDetails.childTicket}: ${movieDetails.childTicket * 5}</p>
                    <p>Discount: -${movieDetails.discount}</p>
                    <p>Taxes: ${movieDetails.tax.toFixed(2)}</p>
                    <hr />
                    <p className="total">Total: ${movieDetails.total.toFixed(2)}</p>
                </div>
            </div>
        </div>
    );
}

export default Checkout;
