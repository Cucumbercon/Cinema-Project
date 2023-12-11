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
    const [schedules, setSchedules] = useState([]); // 存储完整的日程数据
    const [selectedScheduleId, setSelectedScheduleId] = useState(null); // 存储选中的scheduleId
    useEffect(() => {
        fetch(`http://localhost:8000/api/getSchedule?id=${movie.id}`, {
            method: 'GET',
        }).then(response => response.json())
            .then(data => {
                setSchedules(data);
                console.log('Fetched data:', data); 
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
        const scheduleId = findScheduleId(date, selectedTime);
        setSelectedScheduleId(scheduleId); // 更新选中的scheduleId
    };

    const handleTimeClick = (time) => {
        setSelectedTime(time);
        const scheduleId = findScheduleId(selectedDate, time);
        setSelectedScheduleId(scheduleId); // 更新选中的scheduleId
    };

    const handleGoToHomeClick = () => {
        navigate('/');
    };

    const findScheduleId = (date, time) => {
        const schedule = schedules.find(sch => {
            const startTime = new Date(sch.startTime);
            const formattedDate = `${startTime.getMonth() + 1}/${startTime.getDate()}`;
            const formattedTime = startTime.toLocaleTimeString('en-US', { hour: '2-digit', minute: '2-digit' });
            return formattedDate === date && formattedTime === time;
        });
        return schedule ? schedule.scheduleId : null;
    };


    //function to handle the logic for adding the show clickable show times and navigating to movie seat booking
    const handleViewSeatsClick = () => {
        const isLoggedIn = localStorage.getItem('isLoggedIn') === 'true';
        console.log(selectedScheduleId);
        if (selectedScheduleId) {
            localStorage.setItem('scheduleId', selectedScheduleId);
        }
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
                    {dates !== null && dates !== undefined
                        ? dates.map((date, index) => (
                            <button
                                key={index}
                                className={`date-button ${selectedDate === date ? 'selected' : ''}`}
                                onClick={() => handleDateClick(date)}
                            >
                                {date}
                            </button>
                        ))
                        : <p>Loading dates...</p>
                    }
                </div>
                <div className="time-selector">
                    {times !== null && times !== undefined
                        ? times.map((time, index) => (
                            <button
                                key={index}
                                className={`time-button ${selectedTime === time ? 'selected' : ''}`}
                                onClick={() => handleTimeClick(time)}
                            >
                                {time}
                            </button>
                        ))
                        : <p>Loading times...</p>
                    }
                </div>
            </div>

            {/* Next Step Button */}
            <div className="next-step">
                 <button onClick={handleGoToHomeClick}>Go back Home</button>
                 
                <button onClick={handleViewSeatsClick}>View Seats</button>
                
            </div>
        </div>
    );
}

export default MovieDetails;