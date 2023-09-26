import React from 'react';
import './home.css';

function MovieBooking() {
  return (
    <div>
        <section className="top-bar">
            <div className="left-content">
                <h2 className="title">WELCOME!</h2>
                <ul className="navigation">
                    <li><a href="#">Home</a></li>
                    <li><a className="active" href="#">Movies</a></li>
                    <li><a href="#">Theaters</a></li>
                    <li><a href="#">Promotion</a></li>
                </ul>
            </div>
            <div className="right-content">
                {/* <img src={require('./assests/images/movieCart.jpg')} alt="" className="cart"/> */}
                {/* <img src={require('./moviefilter.jpg')} alt="" className="filter"/> */}
                <img src={require('./movieCart.jpg')} alt="" className="cart"/>
                <img src={require('./movieHelp.jpg')} alt="" className="help"/>
                <div className="profile-img-box">
                    <img src={require('./movieUserIcon.jpg')} alt="Signin/Join"/>
                </div>
                <img src={require('./movieMenu.png')} alt="" className="menu"/>
            </div>
        </section>
        <section className="main-container">
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
                    

                    <div className="sidebar-groups">
                        <h3 className="sg-title">Language</h3>
                        <input type="checkbox" id="english" name="english" value="english" />
                        <label htmlFor="english">English</label>
                        <input type="checkbox" id="spanish" name="spanish" value="spanish" />
                        <label htmlFor="spanish">Spanish</label>
                        <input type="checkbox" id="chinese" name="chinese" value="chinese" />
                        <label htmlFor="chinese">Chinese</label>
                    </div>    

                    <div className="sidebar-groups">
                        <h3 className="sg-title">Details</h3>
                        <input type="checkbox" id="ratescore" name="ratescore" value="ratescore" />
                        <label htmlFor="ratescore">RateScore</label>
                        <input type="checkbox" id="numofviewers" name="numofviewers" value="numofviewers" />
                        <label htmlFor="numofviewers">Numofviewers</label>
                        <input type="checkbox" id="mostleased" name="mostleased" value="mostleased" />
                        <label htmlFor="mostleased">Mostleased</label>
                    </div>    

                    <div className="sidebar-groups">
                        <a href="#" className="btn-l btn">Apply Filters</a>
                    </div>
                </form>
            </div>
            
            <div className="movies-container">
                <div className="upcoming-img-box">
                    {/* <img src={require('./upcomingMovie.jpg')} alt=""/> */}
                    <img src={require('./Venom3.jpg')} alt=""/>
                    <p className="upcoming-title">Upcoming Movie</p>
                    <div className="buttons">
                        <a href="#" className="btn">Book Now</a>
                        <a href="#" className="btn-alt btn">Play Trailer</a>
                    </div>
                </div>

                <div className="current-movies">

                    <div className="current-movie">
                        <div className="current-img-box">
                            <img src={require('./crtMov1.png')} alt=""/>
                        </div>
                        <h3 className="movie-title">XXXX</h3>
                        <div className="booking">
                            <a href="#" className="btn">Veiw Details</a>
                        </div>
                    </div>

                    <div className="current-movie">
                        <div className="current-img-box">
                            <img src={require('./crtMov2.png')} alt=""/>
                        </div>
                        <h3 className="movie-title">A haunting in Venice</h3>
                        <div className="booking">
                            <a href="#" className="btn">Veiw Details</a>
                        </div>
                    </div>

                    <div className="current-movie">
                        <div className="current-img-box">
                            <img src={require('./crtMov3.png')} alt=""/>
                        </div>
                        <h3 className="movie-title">The Equalizer 3</h3>
                        <div className="booking">
                            <a href="#" className="btn">Veiw Details</a>
                        </div>
                    </div>


                </div>
                
            </div>
        </section>
    </div>
  );
}

export default MovieBooking;
