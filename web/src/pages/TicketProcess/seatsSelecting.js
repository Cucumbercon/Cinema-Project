import React, { useState, useEffect } from 'react';
import { useNavigate, useLocation } from 'react-router-dom';
import './seatsSelecting.css';

function MovieSeatBooking() {
    const navigate = useNavigate();
    const movie = (useLocation().state.movie);
    // These are the state variables
    const [seats, setSeats] = useState(generateSeats());
    const [selectedMovie, setSelectedMovie] = useState({ index: 0, price: 5 });
    const [selectedSeat, setSelectedSeat] = useState([]);
    const [selectedSeatsCount, setSelectedSeatsCount] = useState({
        child: 0,
        adult: 0,
        senior: 0,
    });

    useEffect(() => {
        // This Loads the selected movie from local storage when the component mounts
        const storedMovieIndex = localStorage.getItem('selectedMovieIndex');
        if (storedMovieIndex !== null) {
            const index = parseInt(storedMovieIndex, 10);
            setSelectedMovie({
                index,
                price: parseFloat(localStorage.getItem('selectedMoviePrice')),
            });
        }
    }, []);

    // generates the initial seat data with alphabetical rows and digit columns
    function generateSeats() {
        const rows = 7;
        const seatsInRow = 9;
        const alphabet = 'ABCDEFGHIJKLMNOPQRSTUVWXYZ';
        let generatedSeats = [];

        for (let i = 0; i < rows; i++) {
            let row = [];
            for (let j = 0; j < seatsInRow; j++) {
                const seatId = `${alphabet[i]}${j + 1}`; // Use letters for rows and digits for columns
                row.push({
                    id: seatId,
                    selected: false,
                    occupied: false,
                });
            }
            generatedSeats.push(row);
        }

        return generatedSeats;
    }

    // Function to generate row labels
    function generateRowLabels() {
        const alphabet = 'ABCDEFGHIJKLMNOPQRSTUVWXYZ';
        const rowLabels = document.querySelectorAll('.row');

        rowLabels.forEach((row, index) => {
            const label = document.createElement('div');
            label.className = 'row-label';
            label.textContent = alphabet[index];
            row.insertBefore(label, row.firstChild);
        });
    }

    const saveSeatToDatabase = (seatId, selected) => {
        // saves the seat information to the database change the url to the created api
        fetch('/api/saveSeat', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({
                seatId,
                selected,

            }),
        })
            .then(response => response.json())
            .then(data => {
                // Handle the response from the server if needed
                console.log('Seat saved to database:', data);
            })
            .catch(error => {
                console.error('Error saving seat to database:', error);
            });
    };

    useEffect(() => {
        // Calls the function to generate row labels 
        generateRowLabels();
    }, []);

    // Function to toggle seat selection
    const toggleSeatSelection = (rowIndex, seatIndex) => {
        const updatedSeats = [...seats];
        const seat = updatedSeats[rowIndex][seatIndex];

        if (!seat.occupied) {
            const seatType = getSeatType(selectedMovie.index);
            const updatedSelectedSeatsCount = { ...selectedSeatsCount };

            if (!seat.selected) {
                seat.selected = true;
                updatedSelectedSeatsCount[seatType]++;
                selectedSeat.push(seat.id);
                setSelectedSeat([...selectedSeat]);
            } else if (seat.selected && updatedSelectedSeatsCount[seatType] > 0) {
                seat.selected = false;
                updatedSelectedSeatsCount[seatType]--;
                const seatIndexToRemove = selectedSeat.indexOf(seat.id);
                if (seatIndexToRemove !== -1) {
                    selectedSeat.splice(seatIndexToRemove, 1);
                    setSelectedSeat([...selectedSeat]); // Ensure state trigger with a new reference
                }

            }

            setSeats(updatedSeats);
            setSelectedSeatsCount(updatedSelectedSeatsCount);
            saveSeatToDatabase(seat.id, seat.selected);

        }
    };


    // This is to handle any movie selection change
    const handleMovieChange = (e) => {
        const price = +e.target.value;
        const index = e.target.selectedIndex;
        setSelectedMovie({ index, price });
        localStorage.setItem('selectedMovieIndex', index);
        localStorage.setItem('selectedMoviePrice', price);
    };

    //  This is to calculate the total price based on the selected seats
    const calculateTotalPrice = () => {
        const { child, adult, senior } = selectedSeatsCount;
        return child * 5 + adult * 15 + senior * 8;
    };

    // This is to get the seat type based on movie index
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

    // Function to handle the checkout process
    const handleCheckout = () => {
        if (selectedSeatsCount.adult > 0 || selectedSeatsCount.senior > 0 || selectedSeatsCount.child > 0) {
            console.log(selectedSeatsCount);
            navigate('/ordersummary', {
                state: { selectedSeatsCount, movie, selectedSeat}
            });
        } else {
            alert('Please select a seat');
        }
        // console.log(selectedSeat)

    }

    return (
        <div>
            <div className='title'>{useLocation().state.movie.title}</div>
            <div className="movie-container">
                <label>Please Select a seat</label>
                <br />
                <label>Age Category</label>
                <select id="movie" value={selectedMovie.price} onChange={handleMovieChange}>
                    <option value="5">Child($5)</option>
                    <option value="15">Adult($15)</option>
                    <option value="8">Senior($8)</option>
                </select>
            </div>

            <div className="centered-container">
                <div className="container">
                    <div className="screen">
                    </div>

                    {seats.map((row, rowIndex) => (
                        <div key={rowIndex} className="row">

                            {row.map((seat, seatIndex) => (
                                <div
                                    key={seatIndex}
                                    className={`seat ${seat.selected ? 'selected' : ''} ${seat.occupied ? 'occupied' : ''
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

            {/* Checkout button */}
            <button className="checkout-button" onClick={handleCheckout}>
                Confirm Seats
            </button>
        </div>
    );
}

export default MovieSeatBooking;
