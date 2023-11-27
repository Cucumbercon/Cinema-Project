import React, { useState, useEffect } from 'react';
import { useLocation } from 'react-router-dom';
import './MovieDetails.css';

function MovieDetails() {

    const { state } = useLocation();

    const [movie, setMovie] = useState(state.data);

    const [selectedDate, setSelectedDate] = useState(null);
    const [selectedTime, setSelectedTime] = useState(null);

    if (!movie) {
        return <div>Loading...</div>;
    }

    const dates = ["10/01", "10/02", "10/03"];
    const times = ["12:00pm", "3:15pm", "6:30pm", "9:45pm"];

    const handleDateClick = (date) => {
        setSelectedDate(date);
    };

    const handleTimeClick = (time) => {
        setSelectedTime(time);
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
                <p><strong>Duration:</strong> {movie.duration}</p>
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
                <button onClick={() => alert('Redirect to Showtimes')}>View Seats</button>
            </div>
        </div>
    );
}

export default MovieDetails;
