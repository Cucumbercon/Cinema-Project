import React, { useState } from 'react';
import './MovieM.css';

function MovieManagement() {
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

    const handleChange = (e) => {
        const { name, value } = e.target;
        setMovieDetails(prev => ({ ...prev, [name]: value }));
    };

    const handleSubmit = (e) => {
        e.preventDefault();

        fetch('http://localhost:8000/api/addMovie', {
            method: 'POST', 
            body: JSON.stringify(movieDetails),
            headers: {
                'Content-Type': 'application/json'
            }
        })
        .then(function(response) {
            // 如果200，direct到homepage，并且更新页面
            if (response.status === 200) {
                return response.json();
            } else {
                // 其他HTTP状态码，如404（未找到）、500（服务器错误）等
                // 出现一个弹框，或其他方式通知用户
                console.error('Request failed with status:', response.status);
                // 返回一个Promise，以便后续链式操作
                return Promise.reject('Request failed');
            }
        })
        .then(function(data) {
            // 处理后端的响应，仅当HTTP状态码为200时才会执行到这里
        })
        .catch(function(error) {
            // 处理任何错误，包括网络错误和HTTP状态码非200的情况
            console.error('Error:', error);
        });   
        
    }


    const handleCheckbox = (e) => {
        const { name, checked } = e.target;
        setMovieDetails(prev => ({ ...prev, [name]: checked }));
    };

    return (
        <div className='movManBackground'>
        <div className="movie-management">
            <h2>Movie Details</h2>
            <form onSubmit={handleSubmit}>
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
