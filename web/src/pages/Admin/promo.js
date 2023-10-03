import React, { useState } from 'react';
import './promo.css';

function Promotion() {
    const [showAddPromo, setShowAddPromo] = useState(false);
    const [promoCodes, setPromoCodes] = useState([]);

    const handleAddPromo = () => {
        // Push new promotions to promoCodes state here
        // Since we dont have any yet, It will show blank
        setShowAddPromo(!showAddPromo);
    };

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
                        <th>Promo Code Name</th>
                        <th>Promo Validity</th>
                        <th>Promo Status</th>
                    </tr>
                </thead>
                <tbody>
                    {promoCodes.map((promo, index) => (
                        <tr key={index}>
                            <td>{promo.name}</td>
                            <td>{promo.validity}</td>
                            <td>{promo.status ? '✔️' : '❌'}</td>
                        </tr>
                    ))}
                </tbody>
            </table>
        </div>
    );
}

export default Promotion;
