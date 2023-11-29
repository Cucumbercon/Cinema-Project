import React, { useState, useEffect } from 'react';
import './OrderHistory.css';

function OrderHistory() {
    const [orders, setOrders] = useState([]);

    useEffect(() => {
        // Fetch orders from backend or local storage
        // This is just a placeholder example
        const fetchedOrders = [
            {
                id: 1,
                movieTitle: "Movie 1",
                date: "2023-10-15",
                seats: ["A1", "A2"],
                amountSpent: 20
                
            },

            {
                id: 2,
                movieTitle: "Movie 2",
                date: "2023-10-15",
                amountSpent: 20,
                seats: ["A1", "A2"]
            },
            {
                id: 2,
                movieTitle: "Movie 2",
                date: "2023-10-15",
                amountSpent: 20,
                seats: ["A1", "A2"]
            },
            {
                id: 2,
                movieTitle: "Movie 2",
                date: "2023-10-15",
                amountSpent: 20,
                seats: ["A1", "A2"]
            },
            {
                id: 2,
                movieTitle: "Movie 2",
                date: "2023-10-15",
                amountSpent: 20,
                seats: ["A1", "A2"]
            },

            
            // ... other orders
        ];
        setOrders(fetchedOrders);
    }, []);

    return (
        <div className="order-history-container">
            <h1>Your Order History</h1>
            <div className="orders">
                {orders.map(order => (
                    <div key={order.id} className="order">
                        <h3>{order.movieTitle}</h3>
                        <p><strong>Date:</strong> {order.date}</p>
                        <p><strong>Seats:</strong> {order.seats.join(', ')}</p>
                        <p><strong>Amount Spent:</strong> ${order.amountSpent}</p>
                    </div>
                ))}
            </div>
        </div>
    );
}

export default OrderHistory;
