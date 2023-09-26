import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import './seatsSelecting.css';

function MovieSeatBooking() {
    const [seats, setSeats] = useState(generateSeats()); // Generate initial seat status
    const [selectedMovie, setSelectedMovie] = useState({ index: 0, price: 5 }); // Default to Child($5)
    
    useEffect(() => {
        // Populate UI from localStorage
        const storedSeats = JSON.parse(localStorage.getItem('selectedSeats'));
        if (storedSeats) {
            setSeats(storedSeats);
        }
        const storedMovieIndex = localStorage.getItem('selectedMovieIndex');
        if (storedMovieIndex) {
            setSelectedMovie(prev => ({ ...prev, index: storedMovieIndex }));
        }
    }, []);

    function generateSeats() {
        // Based on your provided HTML, I assumed 7 rows of 9 seats each.
        const rows = 7;
        const seatsInRow = 9;
        let generatedSeats = [];
        for (let i = 0; i < rows; i++) {
            let row = [];
            for (let j = 0; j < seatsInRow; j++) {
                row.push({
                    selected: false,
                    occupied: Math.random() < 0.3 // Just for simulation; ~30% seats are occupied initially
                });
            }
            generatedSeats.push(row);
        }
        return generatedSeats;
    }

    const toggleSeatSelection = (rowIndex, seatIndex) => {
        const updatedSeats = [...seats];
        if (!updatedSeats[rowIndex][seatIndex].occupied) {
            updatedSeats[rowIndex][seatIndex].selected = !updatedSeats[rowIndex][seatIndex].selected;
        }
        setSeats(updatedSeats);
        localStorage.setItem('selectedSeats', JSON.stringify(updatedSeats));
    };

    const handleMovieChange = e => {
        const price = +e.target.value;
        const index = e.target.selectedIndex;
        setSelectedMovie({ index, price });
        localStorage.setItem('selectedMovieIndex', index);
        localStorage.setItem('selectedMoviePrice', price);
    };

    const totalSelectedSeats = seats.flat().filter(seat => seat.selected).length;

    return (
        <div>
            <div className="movie-container">
                <label> Please Select a seat </label>
                <br />
                <label> Age Category </label>
                <select id="movie" value={selectedMovie.price} onChange={handleMovieChange}>
                    <option value="5">Child($5)</option>
                    <option value="15">Adult($15)</option>
                    <option value="8">Senior($8)</option>
                </select>
            </div>
            
            <div className="container">
                <div className="screen"></div>
                {seats.map((row, rowIndex) => (
                    <div key={rowIndex} className="row">
                        {row.map((seat, seatIndex) => (
                            <div 
                                key={seatIndex}
                                className={`seat ${seat.selected ? 'selected' : ''} ${seat.occupied ? 'occupied' : ''}`} 
                                onClick={() => toggleSeatSelection(rowIndex, seatIndex)}
                            ></div>
                        ))}
                    </div>
                ))}
            </div>

            <p className="text">
                You have selected <span>{totalSelectedSeats}</span> seats for a price of $<span>{totalSelectedSeats * selectedMovie.price}</span>
            </p>

            <p className="Ready to pay">
                Lets go ready to pay!
            </p>
        </div>
    );
}

export default MovieSeatBooking;


