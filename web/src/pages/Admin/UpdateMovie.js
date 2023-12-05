    import React, { useState, useEffect } from 'react';
    import { useNavigate } from 'react-router-dom';

    import './MovieM.css';

    function MovieManagement(props) {
        const navigate = useNavigate();
        const [archivedMovies, setArchivedMovies] = useState([]);
        const [currentMovies, setCurrentMovies] = useState([]);
        const [upcomingMovies, setUpcomingMovies] = useState([]);
        const [moviesList, setMoviesList] = useState([]);
        const [movieDetails, setMovieDetails] = useState({
            id: '',
            title: '',
            language: '',
            popularity: '',
            posterPath: '',
            backdropPath: '',
            date: '',
            state: '',
            category: '',
            trailerPath: '',
            synopsis: '',
            cast: '',
            director: '',
            producer: '',
            rating: '',
            duration: ''
        });
        const fetchMovies = async () => {
            try {
                const archivedResponse = await fetch('http://localhost:8000/api/getArchiveMovie');
                const archivedMovies = await archivedResponse.json();
                
                const currentResponse = await fetch('http://localhost:8000/api/getAvailableMovie');
                const currentMovies = await currentResponse.json();

                const upcomingResponse = await fetch('http://localhost:8000/api/getUpComingMovie');
                const upcomingMovies = await upcomingResponse.json();
                console.log(currentMovies)
                const combinedMovies = [
                    { id:"AM", title: '=== Archived Movies ===', isCategory: true },
                    ...archivedMovies,
                    { id:"CM", title: '=== Current Movies ===', isCategory: true },
                    ...currentMovies,
                    { id:"UM", title: '=== Upcoming Movies ===', isCategory: true },
                    ...upcomingMovies
                ];

                setMoviesList(combinedMovies);
            } catch (error) {
                console.error('Error fetching movies:', error);
            }
        };
        useEffect(() => {
            fetchMovies();
        }, []);


        const handleMovieSelect = (selectedMovieId) => {
            const selectedMovie = moviesList.find(movie => movie.id && movie.id.toString() === selectedMovieId);
            if (selectedMovie) {
                setMovieDetails(selectedMovie);
            }
        };

        const handleChange = (e) => {
            const { name, value } = e.target;
            setMovieDetails(prev => ({ ...prev, [name]: value }));
        };


        const handleSubmit = (e) => {
            e.preventDefault();
            console.log(movieDetails.date)
            if (/^\d{4}-\d{2}-\d{2}$/.test(movieDetails.date) === false) {
                alert("Sorry, the format of release_day is incorrect");
            } else {
                fetch('http://localhost:8000/api/updateMovieInfo', {
                    method: 'POST',
                    body: JSON.stringify(movieDetails),
                    headers: {
                        'Content-Type': 'application/json'
                    }
                })
                    .then(function (response) {
                        if (response.status === 200) {
                            alert('Movie updated successfully!');
                            fetchMovies();
                            return response.json();
                        } else if (response.status === 400) {
                            // Handle movie already exists in the db
                        } else {
                            console.error('Request failed with status:', response.status);
                            return Promise.reject('Request failed');
                        }
                    })
                    .catch(function (error) {
                        console.error('Error:', error);
                    });
            }
        };

        const handleArchiveMovie = () => {
            const ArchiveMovie = async () => {
                const movieToUpdate = { ...movieDetails, state: -1 };
                try {
                    const response = await fetch('http://localhost:8000/api/updateMovieInfo', {
                        method: 'POST',
                        body: JSON.stringify(movieToUpdate),
                        headers: {
                            'Content-Type': 'application/json'
                        }
                    });
                    if (response.ok) {
                        alert('Movie archived successfully!');
                        fetchMovies();
                        movieDetails.state=-1;
                    } else {
                        alert(`Failed to archive movie.`);
                    }
                } catch (error) {
                    console.error('Error:', error);
                    alert('Error archiving movie');
                }
            };
            ArchiveMovie();
        };

        return (
            <div className='movManBackground'>
                <div className="movie-management">
                    <h2 className='movieMh2'>Movie Details</h2>
                    {/* Dropdown box to select a movie */}
                    <div>
                        <label>Select Movie:</label>
                        <select onChange={(e) => handleMovieSelect(e.target.value)}>
                            <option value="" disabled selected>Select a movie</option>
                            {moviesList.map(movie => {
                                 if (movie.isCategory) {
                                    // separator
                                    return <option key={movie.id} disabled>{movie.title}</option>;
                                } else {
                                    // movie info
                                    return <option key={movie.id} value={movie.id}>{movie.title}</option>;
                                }
                            })}
                        </select>
                    </div>
                    <form onSubmit={handleSubmit}>
                        <div className='movieField grid'>
                            <div className='movieField'>
                                Title
                                <input type="text" name="title" value={movieDetails.title || ''} placeholder="Title" onChange={handleChange} required/>
                            </div>
                            <div className='movieField'>
                                Language
                                <input type="text" name="language" value={movieDetails.language || ''} placeholder="Language" onChange={handleChange} required />
                            </div>
                        </div>

                        <div className='movieField grid'>
                            <div className='movieField'>
                                Popularity
                                <input type="text" name="popularity" value={movieDetails.popularity || '0' }placeholder="Popularity" onChange={handleChange} required />
                            </div>
                            <div className='movieField'>
                                Poster Path
                                <input type="text" name="poster_path" value={movieDetails.posterPath || ''} placeholder="Poster_path" onChange={handleChange} required />

                            </div>
                        </div>

                        <div className='movieField grid'>
                            <div className='movieField'>
                                Release Day
                                <input
                                    type="text"
                                    name="release_day"
                                    placeholder="Release_day in YYYY-MM-DD format"
                                    value={movieDetails.date}

                                    required
                                />
                            </div>
                            <div className='movieField'>
                                State (Archive: -1, Upcoming: 0, Available: 1)
                                <input type="text" name="state" value={movieDetails.state} placeholder="State" onChange={handleChange} required />
                            </div>
                        </div>

                        <div className='movieField grid'>
                            <div className='movieField'>
                                Category
                                <input type="text" name="category" value={movieDetails.category||''} placeholder="Category" onChange={handleChange} required />
                            </div>
                            <div className='movieField'>
                                Rating
                                <input type="text" name="rating" value={movieDetails.rating||''} placeholder="Rating" onChange={handleChange} />
                            </div>
                        </div>

                        <div className='movieField grid'>
                            <div className='movieField'>
                                Trailer Path
                                <input type="text" name="trailer_path" value={movieDetails.trailerPath||''} placeholder="Trailer_path" onChange={handleChange} required />
                            </div>
                            <div className='movieField'>
                                Cast
                                <input type="text" name="cast" value={movieDetails.cast||''} placeholder="Cast" onChange={handleChange} required />
                            </div>
                        </div>

                        <div className='movieField grid'>
                            <div className='movieField'>
                                Director
                                <input type="text" name="director" value={movieDetails.director||''} placeholder="Director" onChange={handleChange} required />
                            </div>
                            <div className='movieField'>
                                Producer
                                <input type="text" name="producer" value={movieDetails.producer||''} placeholder="Producer" onChange={handleChange} required />
                            </div>
                        </div>

                        <div className='movieField grid'>
                            <div className='movieField'>
                                Duration(Minutes)
                                <input type="text" name="duration" value={movieDetails.duration|| '0'} placeholder="Duration" onChange={handleChange} required />
                            </div>
                            <div className='movieField'>
                                Synosis
                                <input type="text" name="synopsis" value={movieDetails.synopsis ||''} placeholder="Synopsis" onChange={handleChange} required />
                            </div>
                        </div>

                        <button type="submit">Update Movie</button>
                        {/* Button to remove the selected movie */}
    <button
    type="button"
    onClick={handleArchiveMovie}
    style={{
        backgroundColor: '#ff0000', // Red color
        color: 'white',
        padding: '10px 20px',
        border: 'none',
        borderRadius: '5px',
        cursor: 'pointer',
        margintop: '10px',
        width: '100%',
    }}
    >
    Archive Movie
    </button>

                    </form>
                </div>
            </ div>
        );
    }

    export default MovieManagement;


