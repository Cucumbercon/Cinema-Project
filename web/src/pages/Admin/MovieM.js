import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';


import './MovieM.css';

function MovieManagement(props) {

    const navigate = useNavigate();

    const [releaseDay, setReleaseDay] = useState("");

    const [movieDetails, setMovieDetails] = useState({
        id: crypto.randomUUID(),
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

    useEffect(() => {
        setMovieDetails((prevMovieDetails) => ({
            ...prevMovieDetails,
            release_day: releaseDay, // 更新 movieDetails 中的 release_day
        }));
    }, [releaseDay]);

    if (localStorage.getItem('isAdmin') != 'true') {
        return <div>You do not have the permission to access this page.</div>;
    }

    const handleChange = (e) => {
        const { name, value } = e.target;
        setMovieDetails(prev => ({ ...prev, [name]: value }));
    };

    const handleDayChange = (e) => {
        let inputValue = e.target.value;
        if (/^\d{0,4}(-\d{0,2}(-\d{0,2})?)?$/.test(inputValue)) {
            if (inputValue.length === 4 || inputValue.length === 7) {
                inputValue += "-";
            }
            setReleaseDay(inputValue);
        }

        console.log("e: " + e.target.value);
        console.log("releaseday: " + releaseDay);
    };

    const handleKeyDown = (e) => {
        if (e.key === "Backspace") {
            setReleaseDay("");
        }
    };

    const handleSubmit = (e) => {
        e.preventDefault();

        if (/^\d{4}-\d{2}-\d{2}$/.test(releaseDay) === false) {
            alert("Sorry, the format of release_day is incorrect");
        } else {

            // console.log(movieDetails);
            fetch('http://localhost:8000/api/addMovie', {
                method: 'POST',
                body: JSON.stringify(movieDetails),
                headers: {
                    'Content-Type': 'application/json'
                }
            })
                .then(function (response) {
                    if (response.status === 200) {
                        // if 200，direct to homepage，and update the page
                        navigate('/');
                        return response.json();
                    } else if (response.status === 400) {
                        // if 400, to inform the admin that the movie already exists in the db (popupwindow,or somethingelse)
                    } else {
                        // if return other HTTP status, inform the admin as well
                        console.error('Request failed with status:', response.status);
                        return Promise.reject('Request failed');
                    }
                })
                .catch(function (error) {
                    // if error occurs other than http, direct to error page or something else
                    console.error('Error:', error);
                });

        }

    }

    return (
        <div className='movManBackground'>
            <div className="movie-management">
                <h2 className='movieMh2'>Movie Details</h2>
                <form onSubmit={handleSubmit}>
                    <div className='movieField grid'>
                        <div className='movieField'>
                            Title
                            <input type="text" name="title" placeholder="Movie Title" onChange={handleChange} required />
                        </div>
                        <div className='movieField'>
                            Language
                            <input type="text" name="language" placeholder="Language" onChange={handleChange} required />
                        </div>
                    </div>

                    <div className='movieField grid'>
                        <div className='movieField'>
                            Popularity
                            <input type="text" name="popularity" placeholder="Popularity" onChange={handleChange} required />
                        </div>
                        <div className='movieField'>
                            Poster Path
                            <input type="text" name="poster_path" placeholder="Poster_path" onChange={handleChange} required />

                        </div>
                    </div>

                    <div className='movieField grid'>
                        <div className='movieField'>
                            Release Day
                            <input
                                type="text"
                                name="release_day"
                                placeholder="Release_day in YYYY-MM-DD format"
                                value={releaseDay}
                                onChange={handleDayChange}
                                onKeyDown={handleKeyDown}

                                required
                            />
                        </div>
                        <div className='movieField'>
                            State (Upcoming: 0, Available: 1)
                            <input type="text" name="state" placeholder="State" onChange={handleChange} required />
                        </div>
                    </div>

                    <div className='movieField grid'>
                        <div className='movieField'>
                            Category
                            <input type="text" name="category" placeholder="Category" onChange={handleChange} required />
                        </div>
                        <div className='movieField'>
                            Rating
                            <input type="text" name="rating" placeholder="Rating" onChange={handleChange} required />
                        </div>
                    </div>

                    <div className='movieField grid'>
                        <div className='movieField'>
                            Trailer Path
                            <input type="text" name="trailer_path" placeholder="Trailer_path" onChange={handleChange} required />
                        </div>
                        <div className='movieField'>
                            Cast
                            <input type="text" name="cast" placeholder="Cast" onChange={handleChange} required />
                        </div>
                    </div>

                    <div className='movieField grid'>
                        <div className='movieField'>
                            Director
                            <input type="text" name="director" placeholder="Director" onChange={handleChange} required />
                        </div>
                        <div className='movieField'>
                            Producer
                            <input type="text" name="producer" placeholder="Producer" onChange={handleChange} required />
                        </div>
                    </div>

                    <div className='movieField grid'>
                        <div className='movieField'>
                            Duration(Minutes)
                            <input type="text" name="backdrop_path" placeholder="Duration" onChange={handleChange} required />
                        </div>
                        <div className='movieField'>
                            synosis
                            <input type="text" name="synopsis" placeholder="Synopsis" onChange={handleChange} required />
                        </div>
                    </div>

                    {/* <div className='synosis'>
                        Synopsis
                        <textarea name="synopsis" placeholder="Synopsis" onChange={handleChange}></textarea>
                    </div> */}

                    {/* <label>
                    Archived:
                    <input type="checkbox" name="archived" onChange={handleCheckbox} />
                </label> */}
                    <button type="submit">Add New Movie</button>
                </form>
            </div>
        </ div>
    );
}

export default MovieManagement;
