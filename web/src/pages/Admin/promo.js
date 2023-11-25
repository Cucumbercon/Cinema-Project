import React, { useState, useEffect } from 'react';
import './promo.css';

function Promotion(props) {
    const [showAddPromo, setShowAddPromo] = useState(false);
    const [promoCodes, setPromoCodes] = useState([]);
    const [newPromo, setNewPromo] = useState({
        promotionCode: '',
        description: '',
        discountAmount: '',
        startDate: '',
        endDate: '',
        status: false // init is false
    });

    
    useEffect(() => {
        fetchPromotion();
    }, []);
    const fetchPromotion = () =>{
        fetch('http://localhost:8000/api/promotions', {
            method: 'GET',
        })
            .then((response) => response.text()) // Goto JSON structure
            .then((data) => {
                // transfer Json from string to Obj
                const parsedData = JSON.parse(data);
                setPromoCodes(parsedData)
                console.log('Promotion Data:', parsedData);
            })
            .catch((error) => {
                console.error('Error in fetch promo:', error);
            });
    }
    const handleAddPromo = () => {
        setShowAddPromo(!showAddPromo);
    };
    const handleInputChange = (e) => {
        const { name, value } = e.target;
        setNewPromo(prevState => ({
            ...prevState,
            [name]: value
        }));
    };

    const handleFormSubmit = (e) => {
        e.preventDefault();
        fetch('http://localhost:8000/api/addpromotion', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(newPromo)
        })
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            return response.text();
        })
        .then(data => {
            
            // send promo code if success added
            const userEmail = "null@null.test"; 
            sendPromotionEmail(userEmail, newPromo.promotionCode);
            alert('Promotion sended successfully');
            fetchPromotion();
        })
        .catch(error => {
            console.error('Error:', error);
            alert('Error adding promotion');
        });
    };
    
    const sendPromotionEmail = (email, promoCode) => {
        fetch('http://localhost:8000/api/sendEmail', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                email: email,
                type: 4, // 假设4代表发送促销信息
                promoCodeInfo: promoCode
            })
        })
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            return response.text();
        })
        .then(data => {
            console.log('Promotion email sent successfully');
        })
        .catch(error => {
            console.error('Error in send promo email:', error);
        });
    };

    if (localStorage.getItem('isAdmin') !== 'true') {
        return <div>You do not have the permission to access this page!</div>;
    }

    return (
        
        <div className="promotion-page">
            <div className="add-promo-bar" onClick={handleAddPromo}>
                Add Promotion
            </div>
            {showAddPromo && (
                <form className="add-promo-form" onSubmit={handleFormSubmit}>
                    <div className="form-group">
                        <label htmlFor="promotionCode">Promo Code:</label>
                        <input 
                            name="promotionCode"
                            placeholder="Promo Code" 
                            onChange={handleInputChange} />
                    </div>
                    <div className="form-group">
                        <label htmlFor="description">Description:</label>
                        <textarea 
                            name="description"
                            placeholder="Promo Description" 
                            onChange={handleInputChange} />
                    </div>
                    <div className="form-group">
                        <label htmlFor="discountAmount">Discount Amount:</label>
                        <input 
                            name="discountAmount"
                            type="number"
                            placeholder="Discount Amount" 
                            min="0"
                            max="1"
                            step="0.01" // min step 0.01
                            onChange={handleInputChange} 
                        />
                    </div>
                    <div className="form-group">
                        <label htmlFor="startDate">Start Date:</label>
                        <input 
                            name="startDate"
                            type="date"
                            placeholder="Start Date" 
                            onChange={handleInputChange} />
                    </div>
                    <div className="form-group">
                        <label htmlFor="endDate">End Date:</label>
                        <input 
                            name="endDate"
                            type="date" 
                            placeholder="End Date" 
                            onChange={handleInputChange} />
                    </div>
                    <button type="submit">Submit</button>
                </form>
            )}
            <table className="promo-table">
                <thead>
                    <tr>
                        <th>Promo Code</th>
                        <th>Promo Description</th>
                        <th>Discount Amount</th>
                        <th>Promo Start Time</th>
                        <th>Promo End Time</th>
                        <th>Promo Status</th>
                    </tr>
                </thead>
                <tbody>
                    {promoCodes.map((promo, index) => (
                        <tr key={index}>
                            <td>{promo.promotionCode}</td>
                            <td>{promo.description}</td>
                            <td>{promo.discountAmount}</td>
                            <td>{promo.startDate}</td>
                            <td>{promo.endDate}</td>
                            <td>{promo.status ? '✔️' : '❌'}</td>
                        </tr>
                    ))}
                </tbody>
            </table>
        </div>
    );
}

export default Promotion;
