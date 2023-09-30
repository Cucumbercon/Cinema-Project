import React, {useState} from 'react'
import './MovieDetails.css';

function MovieDetails() {
    
    const movie = {

        title: "THE CREATOR",
        duration: "2h 13m",
        description: "A thief who enters the dreams of others to obtain secrets finds his task complicated by a mysterious rival.",
        genre: "Action/Adventure, Sci-Fi/Fantasy",
        releasedate: "Friday,Sep 29,2023",
        castAndCrew: ["Leonardo DiCaprio", "Joseph Gordon-Levitt", "Ellen Page"]
    }

    const dates = ["10/01", "10/02", "10/03"];
    const times = ["12:00pm", "3:15pm", "6:30pm", "9:45pm"];

    return (
        

        <div className="movie-details-container">
            {/* Trailer Section */}
            <div className="trailer-section">
                <iframe title="movieTrailer" width="100%" height="400"src="https://www.youtube.com/embed/ex3C1-5Dhb8?si=BdOtxV-4tg0npeZB" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share" allowfullscreen></iframe>
            </div>
            
            {/* Movie Details */}
            <div className="details-section">
                <h2>{movie.title}</h2>
                <p><strong>Duration:</strong> {movie.duration}</p>
                <p><strong>Description:</strong> {movie.description}</p>
                <p><strong>Genre:</strong> {movie.genre}</p>
                <p><strong>Releasedate:</strong> {movie.releasedate}</p>
                <p><strong>Cast & Crew:</strong> {movie.castAndCrew.join(', ')}</p>
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
