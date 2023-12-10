import React, { useState, useEffect } from 'react';
import { useLocation, useNavigate } from 'react-router-dom';
import './MovieDetails.css';

function MovieDetails() {



    const { state } = useLocation();
    const navigate = useNavigate();

    const [movie, setMovie] = useState(state.data);


    const [dates, setDates] = useState([]);
    const [times, setTimes] = useState([]);
    const [selectedDate, setSelectedDate] = useState(null);
    const [selectedTime, setSelectedTime] = useState(null);

    useEffect(() => {
        fetch(`http://localhost:8000/api/getSchedule?id=${movie.id}`, {
            method: 'GET',
        }).then(response => response.json())
            .then(data => {
                const newDates = data.map(item => {
                    const date = new Date(item.startTime);
                    return `${date.getMonth() + 1}/${date.getDate()}`;
                  });
          
                  const newTimes = data.map(item => {
                    const startTime = new Date(item.startTime);
                    return `${startTime.toLocaleTimeString('en-US', { hour: '2-digit', minute: '2-digit' })}`;
                  });
          
                  setDates(newDates);
                  setTimes(newTimes);
            })
            .catch(error => {
                console.error('Error fetching movie schedule:', error);
            });

    }, [movie.id]);

    if (!movie) {
        return <div>Loading...</div>;
    }


    const handleDateClick = (date) => {
        setSelectedDate(date);
    };

    const handleTimeClick = (time) => {
        setSelectedTime(time);
    };



    //function to handle the logic for adding the show clickable show times and navigating to movie seat booking
    const handleViewSeatsClick = () => {
        const isLoggedIn = localStorage.getItem('isLoggedIn') === 'true';

        if (!selectedDate || !selectedTime) {
            alert('Please select a date and time.');
            return;
        }

        if (!isLoggedIn) {
            // Redirect to the login page with a state indicating the user was redirected
            navigate('/login', { state: { fromBookingPage: true } });
        } else {
            navigate('/movieseatbooking', { state: { movie, selectedDate, selectedTime } });
        }
    };
    return (
        <div className="movie-details-container">
            {/* Trailer Section */}
            <div className="trailer-section">
                <iframe title="movieTrailer" width="100%" height="400" src={movie.trailerPath} frameBorder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share" allowFullScreen></iframe>
            </div>

            {/* Movie Details */}
            <div className="details-section">
                <h2>{movie.title}</h2>
                <p><strong>Duration:</strong> {movie.backdropPath} minutes</p>
                <p><strong>Description:</strong> {movie.synopsis}</p>
                <p><strong>Genre:</strong> {movie.category}</p>
                <p><strong>Releasedate:</strong> {movie.date}</p>
                {/* Check if the castAndCrew exists before accessing its properties */}
                {movie.castAndCrew && (
                    <p><strong>Cast & Crew:</strong> {movie.castAndCrew.join(', ')}</p>
                )}
            </div>

            <div className="showtime-section">
                <h2>Showing Times</h2>
                <div className="date-selector">
                    {dates.map((date, index) => (
                        <button
                            key={index}
                            className={`date-button ${selectedDate === date ? 'selected' : ''}`}
                            onClick={() => handleDateClick(date)}
                        >
                            {date}
                        </button>
                    ))}
                </div>
                <div className="time-selector">
                    {times.map((time, index) => (
                        <button
                            key={index}
                            className={`time-button ${selectedTime === time ? 'selected' : ''}`}
                            onClick={() => handleTimeClick(time)}
                        >
                            {time}
                        </button>
                    ))}
                </div>
            </div>

            {/* Next Step Button */}
            <div className="next-step">
                <button onClick={handleViewSeatsClick}>View Seats</button>
            </div>
        </div>
    );
}

export default MovieDetails;