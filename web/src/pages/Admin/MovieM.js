import React, { useState } from 'react';
import './MovieM.css';

function MovieManagement() {
    const [movieDetails, setMovieDetails] = useState({
        title: '',
        language: '',
        popularity: '',
        poster_path: '',
        backdrop_path: '',
        release_day: '',
        state: '',
        category: '',
        trailer_path: '',
        cast: '',
        director: '',
        producer: '',
        synopsis: '',
        rating: '',

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
        <div className='movManBackground'>
        <div className="movie-management">
            <h2>Movie Details</h2>
            <form>
                <input type="text" name="title" placeholder="Movie Title" onChange={handleChange} />
                <input type="text" name="language" placeholder="Language" onChange={handleChange} />
                <input type="text" name="popularity" placeholder="Popularity" onChange={handleChange} />
                <input type="text" name="poster_path" placeholder="Poster_path" onChange={handleChange} />
                <input type="text" name="release_day" placeholder="Release_day" onChange={handleChange} />
                <textarea name="synopsis" placeholder="Synopsis" onChange={handleChange}></textarea>
                <input type="text" name="state" placeholder="State" onChange={handleChange} />
                <input type="text" name="category" placeholder="Category" onChange={handleChange} />
                <input type="text" name="rating" placeholder="Rating" onChange={handleChange} />
                <input type="text" name="trailer_path" placeholder="Trailer_path" onChange={handleChange} />
                <input type="text" name="cast" placeholder="Cast" onChange={handleChange} />
                <input type="text" name="director" placeholder="Director" onChange={handleChange} />
                <input type="text" name="producer" placeholder="Producer" onChange={handleChange} />

                {/* <label>
                    Archived:
                    <input type="checkbox" name="archived" onChange={handleCheckbox} />
                </label> */}
                <button type="submit">Update Movie Details</button>
            </form>
        </div>
        </ div>
    );
}

export default MovieManagement;
