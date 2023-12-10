import React, { useState, useEffect } from 'react';
import './OrderHistory.css';
import { useNavigate } from 'react-router-dom';

function OrderHistory() {
    const [orders, setOrders] = useState([]);
    const navigate = useNavigate();

    useEffect(() => {
        // Fetch orders from backend or local storage
        // This is just a placeholder example
        const fetchedOrders = [
            {
                id: 1,
                movieTitle: "Movie 1",
                date: "2023-10-15",
                seats: ["A1", "A2"],
                amountSpent: 20,
                purchaseStatus: "Purchased"
            },

            {
                id: 2,
                movieTitle: "Movie 2",
                date: "2023-11-21",
                amountSpent: 60,
                seats: ["C1", "C2"],
                purchaseStatus: "Failed"
            },
            
            // ... other orders
        ];
        setOrders(fetchedOrders);
    }, []);

    // path for how you connect to backend and database
    useEffect(() => {
        const userId = localStorage.getItem('userId');

        fetch(`http://localhost:8000/api/orders/history/${userId}`, {
            method: 'GET',
            headers: {
                'Authorization': `Bearer ${localStorage.getItem('authToken')}`, // or however you handle auth
                'Content-Type': 'application/json',
            },
        })
        .then((response) => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            return response.json();
        })
        .then((data) => {
            setOrders(data);
        })
        .catch((error) => {
            console.error('Error fetching order history:', error);
        });
    }, []);


    return (
    <div className="parent-container">
        <div className="order-history-container">
            <h1>Your Order History</h1>
            <div className="orders">
                {orders.map(order => (
                    <div key={order.id} className="order">
                    {/* <div key={order.id} className="order-item"> */}
                        <h3>{order.movieTitle}</h3>
                        <p><strong>Date:</strong> {order.date}</p>
                        <p><strong>Seats:</strong> {order.seats.join(', ')}</p>
                        <p><strong>Amount Spent:</strong> ${order.amountSpent}</p>
                        <p><strong>Status:</strong> {order.purchaseStatus}</p>
                    </div>
                ))}

            <button className="home-btn" onClick={() => navigate('/')}>
              Go back to home
             </button>
            </div>
            
        </div>
        </div>
    );
}

export default OrderHistory;
