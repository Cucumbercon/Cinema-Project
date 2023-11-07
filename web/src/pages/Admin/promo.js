import React, { useState, useEffect } from 'react';
import './promo.css';

function Promotion(props) {
    const [showAddPromo, setShowAddPromo] = useState(false);
    // 初始化状态时包含示例促销代码
    const [promoCodes, setPromoCodes] = useState([
        {
            promotionCode: "SAMPLEPROMO",
            description: "This is a sample promotion description.",
            discountAmount:0.9,
            startDate: "2023-12-31",
            endDate:"2024-01-01",
            status: true
        }
    ]);

    useEffect(() => {
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
    }, []);

    const handleAddPromo = () => {
        setShowAddPromo(!showAddPromo);
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
                <div className="add-promo-form">
                    <input placeholder="Promo Code" />
                    <input type="date" placeholder="Promo Validity" />
                    <input type="number" placeholder="Discount Percentage" />
                    <button>Submit</button>
                </div>
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
