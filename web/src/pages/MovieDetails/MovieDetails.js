import React, { useState, useEffect } from 'react';
import './MovieDetails.css';

function MovieDetails() {
    const [movie, setMovie] = useState(null);

    useEffect(() => {
        // API call to fetch movie information such as title, duration, etc.
        const fetchMovieData = async () => {
            try {
                //replace 'the_api_endpoint_here' with the actual API endpoint
                const response = await fetch('the_api_endpoint_here');
                const data = await response.json();
                setMovie(data);
            } catch (error) {
                console.error('Error fetching movie data:', error);
            }
        };

        fetchMovieData();
    }, []);

    if (!movie) {
        return <div>Loading...</div>;
    }

    const dates = ["10/01", "10/02", "10/03"];
    const times = ["12:00pm", "3:15pm", "6:30pm", "9:45pm"];

    return (
        <div className="movie-details-container">
            {/* Trailer Section */}
            <div className="trailer-section">
                <iframe title="movieTrailer" width="100%" height="400" src="https://www.youtube.com/embed/ex3C1-5Dhb8?si=BdOtxV-4tg0npeZB" frameBorder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share" allowFullScreen></iframe>
            </div>

            {/* Movie Details */}
            <div className="details-section">
                <h2>{movie.title}</h2>
                <p><strong>Duration:</strong> {movie.duration}</p>
                <p><strong>Description:</strong> {movie.description}</p>
                <p><strong>Genre:</strong> {movie.genre}</p>
                <p><strong>Releasedate:</strong> {movie.releasedate}</p>
                {/* Check if castAndCrew exists before accessing its properties */}
                {movie.castAndCrew && (
                    <p><strong>Cast & Crew:</strong> {movie.castAndCrew.join(', ')}</p>
                )}
            </div>

            <div className="showtime-section">
                <h2>Showing Times</h2>
                <div className="date-selector">
                    {dates.map((date, index) => (
                        <button key={index} className="date-button">{date}</button>
                    ))}
                </div>
                <div className="time-selector">
                    {times.map((time, index) => (
                        <button key={index} className="time-button">{time}</button>
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
