import React, { useState, useEffect } from 'react'
import Slider from "react-slick";
import "slick-carousel/slick/slick.css";
import "slick-carousel/slick/slick-theme.css";
import './home.css';

function MovieBooking() {
    const [searchTerm, setSearchTerm] = useState('');
    const [availableMovie, setAvailableMovie] = useState([]);
    const [upComingMovie, setUpComingMovie] = useState([]);
    const [isModalOpen, setIsModalOpen] = useState(false);
    const [currentTrailerUrl, setCurrentTrailerUrl] = useState('');


    // Carousels for movies 
    const settings = {
        dots: true,
        infinite: true,
        speed: 500,
        slidesToShow: 3,
        slidesToScroll: 1,
        autoplay: true,
        autoplaySpeed: 3000,
    };

    useEffect(() => {
    // get the onshow movie from db
    fetch('http://localhost:8000/api/getAvailableMovie', {
  method: 'GET',
})
  .then((response) => response.text()) // 解析响应为JSON
  .then((data) => {
    const parsedData = JSON.parse(data);
    setAvailableMovie(parsedData);
    console.log('availableMoviedata:', parsedData);
  })
  .catch((error) => {
    console.error('Error sending message to Spring:', error);
  });
    // get the upcoming movie from db
    fetch('http://localhost:8000/api/getUpComingMovie', {
        method: 'GET',
      })
        .then((response) => response.text()) // 解析响应为 JSON 格式
        .then((data) => {
            // transfer Json from string to Obj
          const parsedData = JSON.parse(data);
          setUpComingMovie(parsedData); // 存储数据在 upComingMovie 状态中
          console.log('upComingMovie:',parsedData);
        })
        .catch((error) => {
          console.error('Error sending message to Spring:', error);
        });
    }, []);

    return (
        // this part is builing top bar
        <div className="homepagebackground">
            <div>
                <section className="top-bar">
                    {/* this part is building left content of top bar */}
                    <div className="left-content">
                        <h2 className="title">WELCOME!</h2>
                        <ul className="navigation">
                            <li><a href="#">Home</a></li>
                            <li><a className="active" href="#">Movies</a></li>
                            <li><a href="#">Theaters</a></li>
                            <li><a href="#">Promotion</a></li>
                        </ul>
                    </div>
                    {/* this part is building search bar of top bar */}
                    <div className="search-bar-container">
                        <input
                            type="text"
                            placeholder="Search for movies..."
                            value={searchTerm}
                            onChange={(e) => setSearchTerm(e.target.value)}
                        />
                    </div>
                    {/* this part is building right content of top bar */}
                    <div className="right-content">
                        {/* <img src={require('./assests/images/movieCart.jpg')} alt="" className="cart"/> */}
                        {/* <img src={require('./moviefilter.jpg')} alt="" className="filter"/> */}
                        <img src={require('./movieCart.jpg')} alt="" className="cart" />
                        <div className="profile-img-box">
                            <img src={require('./movieUserIcon.jpg')} alt="Signin/Join" />
                        </div>
                        <img src={require('./movieMenu.png')} alt="" className="menu" />
                    </div>
                </section>
                {/* this part is building the matin container inculde sidebar and movie container*/}
                <section className="main-container">
                    {/* this part is building sidebar for movie filter. user are able find the type of movie of what they like*/}
                    {/* there are three groups of sidebar which incude Categories, Language, Details. and one "Apply Filters" button */}
                    <div className="sidebar">
                        <form action="#">
                            <div className="sidebar-groups">
                                <h3 className="sg-title">Categories</h3>
                                <input type="checkbox" id="adventure" name="adventure" value="adventure" />
                                <label htmlFor="adventure">Adventure</label>
                                <input type="checkbox" id="action" name="action" value="action" />
                                <label htmlFor="action">Action</label>
                                <input type="checkbox" id="animation" name="animation" value="animation" />
                                <label htmlFor="animation">Animation</label>
                                <input type="checkbox" id="comedy" name="comedy" value="comedy" />
                                <label htmlFor="comedy">Comedy</label>
                                <input type="checkbox" id="thriller" name="thriller" value="thriller" />
                                <label htmlFor="thriller">Thriller</label>
                                <input type="checkbox" id="fantasy" name="fantasy" value="fantasy" />
                                <label htmlFor="fantasy">Fantasy</label>

                            </div>

                            {/* this part is for Language */}
                            <div className="sidebar-groups">
                                <h3 className="sg-title">Language</h3>
                                <input type="checkbox" id="english" name="english" value="english" />
                                <label htmlFor="english">English</label>
                                <input type="checkbox" id="spanish" name="spanish" value="spanish" />
                                <label htmlFor="spanish">Spanish</label>
                                <input type="checkbox" id="chinese" name="chinese" value="chinese" />
                                <label htmlFor="chinese">Chinese</label>
                            </div>
                            {/* this part is for Details */}
                            <div className="sidebar-groups">
                                <h3 className="sg-title">Details</h3>
                                <input type="checkbox" id="ratescore" name="ratescore" value="ratescore" />
                                <label htmlFor="ratescore">RateScore</label>
                                <input type="checkbox" id="numofviewers" name="numofviewers" value="numofviewers" />
                                <label htmlFor="numofviewers">Numofviewers</label>
                                <input type="checkbox" id="mostleased" name="mostleased" value="mostleased" />
                                <label htmlFor="mostleased">Mostleased</label>
                            </div>
                            {/* this part is for apply filters */}
                            <div className="sidebar-groups">
                                <a href="#" className="btn-l btn">Apply Filters</a>
                            </div>
                        </form>
                    </div>
                    {/* this part include both current movie and future movie*/}
                    <div className="movies-container">
                        <div className="upcoming-img-box">
                            <img src={require('./Venom3.jpg')} alt="" />
                            <p className="upcoming-title">Upcoming Movie</p>
                            <div className="buttons">
                                <a href="#" className="btn">Book Now</a>
                                <a href="https://www.youtube.com/watch?v=TMqfLxE15zI&t=1s&ab_channel=ScreenCulture" className="btn-alt btn">Play Trailer</a>
                            </div>
                        </div>

                        <div className="current-movies">
                            {/* <Slider {...settings}> */}
                            


                            {availableMovie.map((movie) => (
                                <div className="current-movie" key={movie.id}>
                                    <div className="current-img-box">
                                        <img src={movie.posterPath} alt={movie.title} />
                                    </div>
                                    <h3 className="movie-title">{movie.title}</h3>
                                    <div className="booking">
                                        <a href="#" className="btn">Veiw Details</a>
                                    </div>
                                    <div className="booking">
                                    <a href="#" className="btn" onClick={() => openModal(movie.trailerPath)}>Play Trailer</a>
                                    </div>
                                </div>
                            ))}
                        

                            <div className="current-movie">
                                <div className="current-img-box">
                                    <img src={require('./crtMov1.png')} alt="" />
                                </div>
                                <h3 className="movie-title">XXXX</h3>
                                <div className="booking">
                                    <a href="#" className="btn">Veiw Details</a>
                                </div>
                            </div>

                            <div className="current-movie">
                                <div className="current-img-box">
                                    <img src={require('./crtMov2.png')} alt="" />
                                </div>
                                <h3 className="movie-title">A haunting in Venice</h3>
                                <div className="booking">
                                    <a href="#" className="btn">Veiw Details</a>
                                </div>
                            </div>

                            <div className="current-movie">
                                <div className="current-img-box">
                                    <img src={require('./crtMov3.png')} alt="" />
                                </div>
                                <h3 className="movie-title">The Equalizer 3</h3>
                                <div className="booking">
                                    <a href="#" className="btn">Veiw Details</a>
                                </div>
                            </div>

                            {/* <div className="future-movies"> */}

                            {/* <div className="future-movie">
                        <div className="future-img-box"> */}
                            <div className="current-movie">
                                <div className="current-img-box">
                                    <img src={require('./crtMov3.png')} alt="" />
                                </div>
                                <h3 className="movie-title">The Equalizer 3</h3>
                                <div className="booking">
                                    <a href="#" className="btn">Veiw Details</a>
                                </div>
                            </div>

                            {/* <div className="future-movie">
                        <div className="future-img-box"> */}
                            <div className="current-movie">
                                <div className="current-img-box">
                                    <img src={require('./crtMov3.png')} alt="" />
                                </div>
                                <h3 className="movie-title">The Equalizer 3</h3>
                                <div className="booking">
                                    <a href="#" className="btn">Veiw Details</a>
                                </div>
                            </div>

                            {/* <div className="future-movie">
                        <div className="future-img-box"> */}
                            <div className="current-movie">
                                <div className="current-img-box">
                                    <img src={require('./crtMov3.png')} alt="" />
                                </div>
                                <h3 className="movie-title">The Equalizer 3</h3>
                                <div className="booking">
                                    <a href="#" className="btn">Veiw Details</a>
                                </div>
                            </div>

                            {/* </div> */}
                            {/* </Slider> */}
                        </div>

                    </div>
                </section>
            </div>

        </div>
    );
}

export default MovieBooking;
