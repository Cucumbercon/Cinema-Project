import React, { useState, useEffect } from 'react'; 
import { useNavigate } from 'react-router-dom';
import './ScheduleMovie.css';

function ScheduleMovie() {
    const [selectedMovie, setSelectedMovie] = useState('');
    const [date, setDate] = useState('');
    const [startTime, setStartTime] = useState('');
    const [endTime, setEndTime] = useState('');
    const [ticketPrice, setTicketPrice] = useState('');
    const [movies, setMovies] = useState([]);
    // Dummy movie list - replace with actual data from backend and database.
    const navigate = useNavigate();
   // const movies = [
   //     { id: 1, title: "Movie 1" },
   //     { id: 2, title: "Movie 2" },
   //     // gotta connect to db
   //  ];

    const scheduleData = {
        selectedMovie,
        date,
        startTime,
        endTime,
        ticketPrice
    };

    const fetchMovies = async () => {
        try {
            // available
            const availableResponse = await fetch('http://localhost:8000/api/getAvailableMovie');
            if (!availableResponse.ok) {
                throw new Error('response fail');
            }
            const availableMovies = await availableResponse.json();
            //console.log(availableMovies);
    
            // upcoming
            const upcomingResponse = await fetch('http://localhost:8000/api/getUpComingMovie');
            if (!upcomingResponse.ok) {
                throw new Error('response fail');
            }
            const upcomingMovies = await upcomingResponse.json();
    
            // combine two lists
            const categorizedMovies = [
                { id: 'available', title: '===Available Movies===', isCategory: true },
                ...availableMovies,
                { id: 'upcoming', title: '===Upcoming Movies===', isCategory: true },
                ...upcomingMovies
            ];
    
            return categorizedMovies;
        } catch (error) {
            console.error('Fetch error:', error);
            return []; // error
        }
    };
    
    useEffect(() => {
        fetchMovies().then(movies => {
            // update movies
            setMovies(movies);
        });
    }, []);
    // I came up the method to connect with the backend and db    !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    // you are also able to seaching by movie title               !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

    // useEffect(() => { fetchMovies();}, []);
    // useEffect(() => { filterMovies(searchTerm);}, [movies, searchTerm]);

    // const fetchMovies = async () => {
    //     try {
    //         const response = await fetch('http://backend-url/api/movies');
    //         if (!response.ok) {
    //             throw new Error('Network response was not ok');
    //         }
    //         const data = await response.json();
    //         setMovies(data);
    //         setFilteredMovies(data);
    //     } catch (error) {
    //         console.error('Fetch error:', error);
    //     }
    // };
    // const filterMovies = (searchTerm) => {
    //     if (!searchTerm) {
    //         setFilteredMovies(movies);
    //         return;
    //     }
    //     const filtered = movies.filter(movie => 
    //         movie.title.toLowerCase().includes(searchTerm.toLowerCase())
    //     );
    //     setFilteredMovies(filtered);
    // };

     const handleSubmit = (e) => {
         e.preventDefault();
         console.log({ selectedMovie, date, startTime, endTime, ticketPrice });
         fetch('http://localhost:8000/api/scheduleMovie', {
                method: 'POST',
                body: JSON.stringify(scheduleData),
                headers: {
                    'Content-Type': 'application/json'
                }
            })
                .catch(function (error) {
                    // if error occurs other than http, direct to error page or something else
                    console.error('Error:', error);
                });

     };

    return (
        <div className="schedule-movie-container">
            <h1>Schedule a Movie</h1>
            <form onSubmit={handleSubmit}>
                <div className="form-group">
                    <label htmlFor="movieSelect">Select Movie:</label>
                    <select id="movieSelect" value={selectedMovie} onChange={e => setSelectedMovie(e.target.value)}>
                        <option value="">--Select a Movie--</option>
                        {movies.map(movie => {
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

                <div className="form-group">
                    <label htmlFor="date">Date:</label>
                    <input type="date" id="date" value={date} onChange={e => setDate(e.target.value)} />
                </div>

                <div className="form-group">
                    <label htmlFor="startTime">Start Time:</label>
                    <input type="time" id="startTime" value={startTime} onChange={e => setStartTime(e.target.value)} />
                </div>

                <div className="form-group">
                    <label htmlFor="endTime">End Time:</label>
                    <input type="time" id="endTime" value={endTime} onChange={e => setEndTime(e.target.value)} />
                </div>

                <div className="form-group">
                    <label htmlFor="ticketPrice">Ticket Price ($):</label>
                    <input type="number" id="ticketPrice" value={ticketPrice} onChange={e => setTicketPrice(e.target.value)} />
                </div>

                <button type="submit" className="submit-btn">Schedule Movie</button>
            </form>
        </div>
    );
}

export default ScheduleMovie;
