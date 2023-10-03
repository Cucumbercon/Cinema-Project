import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import './seatsSelecting.css';

function MovieSeatBooking() {
    const [seats, setSeats] = useState(generateSeats());
    const [selectedMovie, setSelectedMovie] = useState({ index: 0, price: 5 });
    const [selectedSeatsCount, setSelectedSeatsCount] = useState({
        child: 0,
        adult: 0,
        senior: 0,
    });

    useEffect(() => {
        const storedMovieIndex = localStorage.getItem('selectedMovieIndex');
        if (storedMovieIndex) {
            setSelectedMovie({
                index: storedMovieIndex,
                price: parseFloat(localStorage.getItem('selectedMoviePrice'))
            });
        }
    }, []);

    function generateSeats() {
        const rows = 7;
        const seatsInRow = 9;
        let generatedSeats = [];
        for (let i = 0; i < rows; i++) {
            let row = [];
            for (let j = 0; j < seatsInRow; j++) {
                row.push({
                    selected: false,
                    occupied: Math.random() < 0.3
                });
            }
            generatedSeats.push(row);
        }
        return generatedSeats;
    }

    const toggleSeatSelection = (rowIndex, seatIndex) => {
        const updatedSeats = [...seats];
        const seat = updatedSeats[rowIndex][seatIndex];
    
        if (!seat.occupied) {
            const seatType = getSeatType(selectedMovie.index);
            const updatedSelectedSeatsCount = { ...selectedSeatsCount };
    
            if (!seat.selected) {
                seat.selected = true;
                updatedSelectedSeatsCount[seatType]++;
            } else if (seat.selected && updatedSelectedSeatsCount[seatType] > 0) {
                
                seat.selected = false;
                updatedSelectedSeatsCount[seatType]--;
            }
    
            setSeats(updatedSeats);
            setSelectedSeatsCount(updatedSelectedSeatsCount);
            localStorage.setItem('selectedSeats', JSON.stringify(updatedSeats));
        }
    };
    
    

    const handleMovieChange = (e) => {
        const price = +e.target.value;
        const index = e.target.selectedIndex;
        setSelectedMovie({ index, price });
        localStorage.setItem('selectedMovieIndex', index);
        localStorage.setItem('selectedMoviePrice', price);
    };

    const calculateTotalPrice = () => {
        const { child, adult, senior } = selectedSeatsCount;
        return child * 5 + adult * 15 + senior * 8;
    };

    const getSeatType = (movieIndex) => {
        switch (movieIndex) {
            case 0:
                return 'child';
            case 1:
                return 'adult';
            case 2:
                return 'senior';
            default:
                return 'child';
        }
    };

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

            <div className="centered-container"> 
                <div className="container">
                    <div className="screen"></div>
                    {seats.map((row, rowIndex) => (
                        <div key={rowIndex} className="row">
                            {row.map((seat, seatIndex) => (
                                <div
                                    key={seatIndex}
                                    className={`seat ${seat.selected ? 'selected' : ''} ${
                                        seat.occupied ? 'occupied' : ''
                                    }`}
                                    onClick={() => toggleSeatSelection(rowIndex, seatIndex)}
                                ></div>
                            ))}
                        </div>
                    ))}
                </div>
            </div>

            <p className="text">
                You have selected {selectedSeatsCount.child} Child seats, {selectedSeatsCount.adult} Adult seats, and {selectedSeatsCount.senior} Senior seats for a total price of $<span>{calculateTotalPrice()}</span>
            </p>

            <p className="Ready to pay">Lets go ready to pay!</p>
        </div>
    );
}

export default MovieSeatBooking;
