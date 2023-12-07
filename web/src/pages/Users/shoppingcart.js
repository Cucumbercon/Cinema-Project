import React, { useState, useEffect } from 'react';
import { useNavigate, useLocation } from 'react-router-dom';  // Import useNavigate from react-router-dom
import './shoppingcart.css';

const ShoppingCart = () => {
  const [cartItems, setCartItems] = useState([]);
  const navigate = useNavigate();  

  useEffect(() => {
    // Placeholder data for four tickets with seat information
    const placeholderTickets = [
      {
        movieName: 'Movie 1',
        movieTime: '12:00 PM',
        movieDate: '2023-12-01',
        seats: ['A1', 'A2', 'A3'],
      },
      {
        movieName: 'Movie 2',
        movieTime: '3:30 PM',
        movieDate: '2023-12-02',
        seats: ['B1', 'B2', 'B3'],
      },
      {
        movieName: 'Movie 3',
        movieTime: '6:45 PM',
        movieDate: '2023-12-03',
        seats: ['C1', 'C2', 'C3'],
      },
      {
        movieName: 'Movie 4',
        movieTime: '9:15 PM',
        movieDate: '2023-12-04',
        seats: ['D1', 'D2', 'D3'],
      },
    ];

    setCartItems(placeholderTickets);
  }, []);

  const handleCheckout = () => {
    // Implement checkout and navigate to the order summary page
    console.log('Checkout button clicked. Implement your checkout logic here.');

  };

  return (
    <div className="shopping-cart-container">
      <h2>Your Current Orders</h2>
      {cartItems.length === 0 ? (
        <p className="empty-cart-message">Your cart is empty.</p>
      ) : (
        <div>
          {cartItems.map((item, index) => (
            <div key={index} className="cart-item">
              <p><strong>Movie:</strong> {item.movieName}</p>
              <p><strong>Time:</strong> {item.movieTime}</p>
              <p><strong>Date:</strong> {item.movieDate}</p>
              <p><strong>Seats:</strong> {item.seats.join(', ')}</p>
            </div>
          ))}
          <button
            style={{
              display: 'block',
              width: '100%',
              padding: '10px',
              backgroundColor: '#ff6600',
              color: '#fff',
              border: 'none',
              borderRadius: '4px',
              cursor: 'pointer',
              fontSize: '16px',
              marginTop: '20px',
            }}
            onClick={handleCheckout}
          >
            Checkout (Needs to navigate to Ordersummary)
          </button>
        </div>
      )}
    </div>
  );
};

export default ShoppingCart;
