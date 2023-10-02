import React, { useState } from 'react';
import './DashBoard.css';

function DashBoard() {
    const [activePanel, setActivePanel] = useState('updateDetails');

    const scheduleMovies = [
        { name: 'Inception', playingOn: 'Dec.4, 2023', movieTime: '3:00 PM', showroom: 'Room B', seatsLeft: 20 },
        // ... other scheduled movies
    ];

    return (
        <div className="movie-management">
            <div className="sidebar">
                <div className="section" onClick={() => setActivePanel('updateDetails')}>Update Movie Details</div>
                <div className="section" onClick={() => setActivePanel('scheduleChange')}>Schedule Movie to Change</div>
            </div>
            <div className="panel">
                {activePanel === 'updateDetails' && <div>/* Put Update Movie Details Content Here */</div>}
                {activePanel === 'scheduleChange' && (
                    <table className="schedule-table">
                        <thead>
                            <tr>
                                <th>Movie Name</th>
                                <th>Playing On</th>
                                <th>Movie Time</th>
                                <th>Showroom</th>
                                <th>Seats Left</th>
                            </tr>
                        </thead>
                        <tbody>
                            {scheduleMovies.map((movie, index) => (
                                <tr key={index}>
                                    <td>{movie.name}</td>
                                    <td>{movie.playingOn}</td>
                                    <td>{movie.movieTime}</td>
                                    <td>{movie.showroom}</td>
                                    <td>{movie.seatsLeft}</td>
                                </tr>
                            ))}
                        </tbody>
                    </table>
                )}
            </div>
        </div>
    );
}

export default DashBoard;
