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
        // 获取用户ID
        const userId = localStorage.getItem('id');
        console.log(userId);
        // 使用正确的API地址
        fetch(`http://localhost:8000/api/orderHistory?userId=${userId}`, {
            method: 'GET',
            headers: {
                //'Authorization': `Bearer ${localStorage.getItem('authToken')}`, // 或其他认证方式
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
            console.log(data);
            setOrders(data);
        })
        .catch((error) => {
            console.error('Error fetching order history:', error);
        });
    }, []); // 空依赖数组表示这个effect只在组件挂载时运行一次


    return (
    <div className="parent-container">
        <div className="order-history-container">
            <h1>Your Order History</h1>
            <div className="orders">
                {orders.map(order => (
                    <div key={order.id} className="order">
                    {/* <div key={order.id} className="order-item"> */}
                        <h3>{order.movieTitle}</h3>


                            <p><strong>Booking Number:</strong> {order.orderId}</p>
                            <p><strong>Order Time:</strong> {order.orderTime}</p>
                            <p><strong>Ticket Number:</strong> {order.ticketNumber?order.ticketNumber:45}</p>
                            {/*<p><strong>Date:</strong> {order.date}</p>*/}
                            <p><strong>Details:</strong> {order.describe}</p>
                            <p><strong>Amount Spent:</strong> ${order.total}</p>
                    </div>
                ))}

            <button className="home-btn" onClick={() => navigate('/')}>
              Home
             </button>
            </div>
            
        </div>
        </div>
    );
}

export default OrderHistory;
