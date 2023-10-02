import React, { useState } from 'react';
import './MovieM.css';

function MovieManagement() {
    const [movieDetails, setMovieDetails] = useState({
        title: '',
        genre: '',
        cast: '',
        director: '',
        producer: '',
        synopsis: '',
        review: '',
        thumbnail: '',
        trailer: '',
        rating: '',
        archived: false
    });

    const handleChange = (e) => {
        const { name, value } = e.target;
        setMovieDetails(prev => ({ ...prev, [name]: value }));
    };

    const handleCheckbox = (e) => {
        const { name, checked } = e.target;
        setMovieDetails(prev => ({ ...prev, [name]: checked }));
    };

    return (
        
        <div className="movie-management">
            <h2>Movie Details</h2>
            <form>
                <input type="text" name="title" placeholder="Movie Title" onChange={handleChange} />
                <input type="text" name="genre" placeholder="Genre" onChange={handleChange} />
                <input type="text" name="cast" placeholder="Cast" onChange={handleChange} />
                <input type="text" name="director" placeholder="Director" onChange={handleChange} />
                <input type="text" name="producer" placeholder="Producer" onChange={handleChange} />
                <textarea name="synopsis" placeholder="Synopsis" onChange={handleChange}></textarea>
                <textarea name="review" placeholder="Review" onChange={handleChange}></textarea>
                <input type="text" name="thumbnail" placeholder="Thumbnail URL" onChange={handleChange} />
                <input type="text" name="trailer" placeholder="Trailer URL" onChange={handleChange} />
                <input type="text" name="rating" placeholder="Rating" onChange={handleChange} />
                <label>
                    Archived:
                    <input type="checkbox" name="archived" onChange={handleCheckbox} />
                </label>
                <button type="submit">Update Movie Details</button>
            </form>
        </div>
    );
}

export default MovieManagement;
