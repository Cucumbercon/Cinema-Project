import React, { useState, useEffect } from 'react'
import { useNavigate } from 'react-router-dom';
import { toast, ToastContainer } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import Slider from "react-slick";
import Modal from "react-modal";
import ReactPlayer from "react-player";
import "slick-carousel/slick/slick.css";
import "slick-carousel/slick/slick-theme.css";
import './home.css';


const MovieBooking = (props) => {
    const [searchTerm, setSearchTerm] = useState('');
    const [searchResults, setSearchResults] = useState([]);
    const [availableMovie, setAvailableMovie] = useState([]);
    const [upComingMovie, setUpComingMovie] = useState([]);
    const [modalIsOpen, setModalIsOpen] = useState(false);
    const [selectedMovie, setSelectedMovie] = useState(false);
    const [isPopupOpen, setIsPopupOpen] = useState(false);
    const [hasDisplayedToast, setHasDisplayedToast] = useState(localStorage.getItem('hasDisplayedToast') === 'true');
    // const [hasDisplayedToast, setHasDisplayedToast] = useState(false);
    // const [isLoggedIn, setIsLoggedIn] = useState(false); 

    const navigate = useNavigate();

    useEffect(() => {
        // console.log("isloggedin: " + localStorage.getItem('isLoggedIn'));
        // console.log("Adminisloggedin: " + localStorage.getItem('AdminisLoggedIn'));
        // console.log("hasDisplayedToast: " + hasDisplayedToast);
        if (localStorage.getItem('isLoggedIn') === 'true' && !hasDisplayedToast) {
            toast.success("Login successful!", {
                position: "bottom-center",
                autoClose: 1000,
                hideProgressBar: false,
                closeOnClick: true,
                pauseOnHover: true,
                draggable: true,
                progress: undefined,
                theme: "colored",
            });
            setHasDisplayedToast(true);
            localStorage.setItem('hasDisplayedToast', 'true');
        }
    }, []);
    // }, [hasDisplayedToast]);

    const toggleUserPopup = () => {
        setIsPopupOpen(!isPopupOpen);
    };

    const handleCartClick = () => {
        // Navigate to the shopping cart page or open a cart dropdown
        console.log("Cart clicked");
        navigate('/shoppingcart');
    };
    

    const handleLogin = () => {
    };

    const handleViewDetail = (movie) => {
        fetch(`http://localhost:8000/api/ViewMovieDetail?id=${movie.id}`, {
            method: 'GET',
        }).then((response) => response.text())
            .then((data) => {
                const parsedData = JSON.parse(data);
                navigate(`/moviedetails/${parsedData.id}`, { state: { data: parsedData} });

            })

            .catch((error) => {
                console.error('Error searching movies:', error);
            });
    }

    const handleSignup = () => {
    };

    const handleProfileUpdate = () => {
    };

    // const goToUserManagement = () => {
    //     navigate('/usermanagement');
    // }

    // const goToMoiveManagement = () => {
    //     navigate('/moviemanagement');
    // }

    const goToAdminPanel = () => {
        navigate('/adminpanel');
    }


    const goToLoginPage = () => {
        navigate('/login');
    };

    const goToSignupPage = () => {
        navigate('/signup');
    };

    const goToProfilePage = () => {
        navigate('/updateprofile');
    };

    const handleLogout = () => {
        localStorage.removeItem('authToken');
        localStorage.setItem('isLoggedIn', 'false');
        localStorage.setItem('isAdmin', 'false');
        // localStorage.setItem('isAdminLoggedIn', 'false');
        localStorage.setItem('name', '')
        setHasDisplayedToast(false);
        toast.success('Logout successful!', {
            position: "bottom-center",
            autoClose: 1000,
            hideProgressBar: false,
            closeOnClick: true,
            pauseOnHover: true,
            draggable: true,
            progress: undefined,
            theme: "colored",
        })
        navigate('/');
        localStorage.setItem('hasDisplayedToast', 'false');
    };


    const viewTrailer = (movie) => {
        setSelectedMovie(movie);
        setModalIsOpen(true);
    };

    const closeModal = () => {
        setModalIsOpen(false);
        setSelectedMovie(null);
    };

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
    const handleSearch = () => {
        // request search
        fetch(`http://localhost:8000/api/searchMovieByName?movieName=${searchTerm}`, {
            method: 'GET',
        })

            .then((response) => response.text())
            .then((data) => {
                console.log(searchTerm);
                const parsedData = JSON.parse(data);
                parsedData.forEach(movie => {
                    const state = movie.state;
                    availableMovie.length = 0;
                    upComingMovie.length = 0;
                    if (state === 1) {
                        setAvailableMovie(prevMovies => [...prevMovies, movie]);
                    } else if (state === 0) {
                        setUpComingMovie(prevUpcoming => [...prevUpcoming, movie]);
                    }
                });
                // console.log(availableMovie.length);

                // setSearchResults(parsedData); // 存储搜索结果
                // console.log('Search results:', data);
            })
            .catch((error) => {
                console.error('Error searching movies:', error);
            });
    };

    useEffect(() => {
        // get the onshow movie from db
        fetch('http://localhost:8000/api/getAvailableMovie', {
            method: 'GET',
        })
            .then((response) => response.text()) // JSON
            .then((data) => {
                const parsedData = JSON.parse(data);
                setAvailableMovie(parsedData);
                // console.log('availableMoviedata:', parsedData);
            })
            .catch((error) => {
                console.error('Error sending message to Spring:', error);
            });
        // get the upcoming movie from db
        fetch('http://localhost:8000/api/getUpComingMovie', {
            method: 'GET',
        })
            .then((response) => response.text()) // Goto JSON structure
            .then((data) => {
                // transfer Json from string to Obj
                const parsedData = JSON.parse(data);
                setUpComingMovie(parsedData); // storage the data in upComingMovie
                // console.log('upComingMovie:', parsedData);
            })
            .catch((error) => {
                console.error('Error sending message to Spring:', error);
            });
    }, []);

    return (
        // this part is builing top bar
        <div className="homepagebackground">
            <div>

                <section className="home-top-bar">
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
                            onKeyDown={(event) => {
                                if (event.key === 'Enter') {
                                    handleSearch();
                                }
                            }}
                        />
                    </div>
                    {/* this part is building right content of top bar */}
                    <div className="right-content">
                        {/* <img src={require('./assests/images/movieCart.jpg')} alt="" className="cart"/> */}
                        {/* <img src={require('./moviefilter.jpg')} alt="" className="filter"/> */}
                        {/* <img src={require('./movieCart.jpg')} alt="" className="cart" /> */}
                        {localStorage.getItem('isLoggedIn') === 'true' && (
                            <img src={require('./movieCart.jpg')} alt="Shopping Cart" className="cart" onClick={handleCartClick} />
                                )}
                        <div className="profile-img-box" onClick={toggleUserPopup} className={isPopupOpen ? 'profile-img-box open' : 'profile-img-box'}>
                            {localStorage.getItem('isLoggedIn') === 'true' ? (
                                <img src={require('./baseUserIcon.png')} alt="User's Profile" />
                            ) : (
                                <img src={require('./movieUserIcon.jpg')} alt="Signin/Join" />
                            )}
                        </div>
                        {isPopupOpen && (
                            <div className="user-popup">
                                <div className="popup-content">
                                    {localStorage.getItem('isLoggedIn') === 'true' ? (
                                        <div>
                                            <h3>Welcome back, {localStorage.getItem('name')}!</h3>
                                            {localStorage.getItem('isAdmin') === 'true' ? (
                                                <div>
                                                    <button onClick={goToAdminPanel}>Admin Panel</button>
                                                    <button onClick={handleLogout}>Logout</button>
                                                </div>
                                            ) : (
                                                <div>
                                                    <button onClick={goToProfilePage}>Update Profile</button>
                                                    <button onClick={handleLogout}>Logout</button>
                                                </div>
                                            )}

                                        </div>
                                    ) : (
                                        <div>
                                            <h3>Hello, There!</h3>
                                            <button onClick={goToLoginPage}>Login</button>
                                            {/* <button onClick={handleLogin}>Login</button> */}
                                            <button onClick={goToSignupPage}>Signup</button>
                                            <div className="admin-login" onClick={goToLoginPage}> Login As Admin
                                            </div>

                                        </div>
                                    )}
                                </div>
                            </div>
                        )}
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
                                <div className="checkbox-container">
                                    <input type="checkbox" id="adventure" name="adventure" value="adventure" />
                                    <label htmlFor="adventure">Adventure</label>
                                </div>
                                <div className="checkbox-container">
                                    <input type="checkbox" id="action" name="action" value="action" />
                                    <label htmlFor="action">Action</label>
                                </div>
                                <div className="checkbox-container">
                                    <input type="checkbox" id="animation" name="animation" value="animation" />
                                    <label htmlFor="animation">Animation</label>
                                </div>
                                <div className="checkbox-container">
                                    <input type="checkbox" id="comedy" name="comedy" value="comedy" />
                                    <label htmlFor="comedy">Comedy</label>
                                </div>
                                <div className="checkbox-container">
                                    <input type="checkbox" id="thriller" name="thriller" value="thriller" />
                                    <label htmlFor="thriller">Thriller</label>
                                </div>
                                <div className="checkbox-container">
                                    <input type="checkbox" id="fantasy" name="fantasy" value="fantasy" />
                                    <label htmlFor="fantasy">Fantasy</label>
                                </div>

                            </div>

                            {/* this part is for Language */}
                            <div className="sidebar-groups">
                                <h3 className="sg-title">Language</h3>
                                <div className="checkbox-container">
                                    <input type="checkbox" id="english" name="english" value="english" />
                                    <label htmlFor="english">English</label>
                                </div>
                                <div className="checkbox-container">
                                    <input type="checkbox" id="spanish" name="spanish" value="spanish" />
                                    <label htmlFor="spanish">Spanish</label>
                                </div>
                                <div className="checkbox-container">
                                    <input type="checkbox" id="chinese" name="chinese" value="chinese" />
                                    <label htmlFor="chinese">Chinese</label>
                                </div>
                            </div>
                            
                            {/* this part is for Details */}
                            <div className="sidebar-groups">
                                <h3 className="sg-title">Details</h3>
                                <div className="checkbox-container">
                                    <input type="checkbox" id="ratescore" name="ratescore" value="ratescore" />
                                    <label htmlFor="ratescore">RateScore</label>
                                </div>
                                <div className="checkbox-container">
                                    <input type="checkbox" id="numofviewers" name="numofviewers" value="numofviewers" />
                                    <label htmlFor="numofviewers">Numofviewers</label>
                                </div>
                                <div className="checkbox-container">
                                    <input type="checkbox" id="mostleased" name="mostleased" value="mostleased" />
                                    <label htmlFor="mostleased">Mostleased</label>
                                </div>
                            </div>
                            {/* this part is for apply filters */}
                            <div className="sidebar-groups">
                                <a href="#" className="btn-l btn">Apply Filters</a>
                            </div>
                        </form>
                    </div>
                    {/* this part include both current movie and future movie*/}
                    <div className="movies-container">
                        <p className='separate'>Upcoming Movies</p>
                        <div className="future-movies">

                            {/*另起一行 */}
                            {searchResults.length <= 0 ? (
                                upComingMovie.map((movie) => (
                                    <div className="current-movie" key={movie.id}>
                                        <div className="current-img-box">
                                            <img src={movie.posterPath} alt={movie.title} />
                                        </div>
                                        <h3 className="movie-title">{movie.title}</h3>
                                        <div className="booking">
                                            <a href="#" className="btn" onClick={() => handleViewDetail(movie)}>View Details</a>
                                        </div>
                                        <div className="booking">
                                            <a
                                                href="#"
                                                className="btn"
                                                onClick={() => {
                                                    viewTrailer(movie)
                                                    // openTrailerModal(movie.trailerPath);
                                                }}
                                            >Play Trailer</a>
                                        </div>
                                    </div>
                                ))
                            ) : (
                                ""
                            )}
                        </div>

                        <p className='separate'>Current Movies</p>
                        <div className="current-movies">
                            {/* <Slider {...settings}> */}
                            {searchResults.length <= 0 ? (
                                availableMovie.map((movie) => (
                                    <div className="current-movie" key={movie.id}>
                                        <div className="current-img-box">
                                            <img src={movie.posterPath} alt={movie.title} />
                                        </div>
                                        <h3 className="movie-title">{movie.title}</h3>
                                        <div className="booking">
                                            <a href="#" className="btn" onClick={() => handleViewDetail(movie)}>Veiw Details</a>
                                        </div>
                                        <div className="booking">
                                            <a
                                                href="#"
                                                className="btn"
                                                onClick={() => {
                                                    viewTrailer(movie)
                                                    // openTrailerModal(movie.trailerPath);
                                                }}
                                            >Play Trailer</a>
                                        </div>
                                    </div>
                                ))
                            ) : (
                                ""
                            )}
                            {/* 另起一行 */}
                            {/* {searchResults.length <= 0 ? (
                                upComingMovie.map((movie) => (
                                    <div className="current-movie" key={movie.id}>
                                        <div className="current-img-box">
                                            <img src={movie.posterPath} alt={movie.title} />
                                        </div>
                                        <h3 className="movie-title">{movie.title}</h3>
                                        <div className="booking">
                                            <a href="#" className="btn">Veiw Details</a>
                                        </div>
                                        <div className="booking">
                                            <a href="#" className="btn">Play Trailer</a>
                                        </div>
                                    </div>
                                ))
                            ) : (
                                ""
                            )} */}
                            {/*另起一行 */}

                            {searchResults.length > 0 ? (
                                searchResults.map((movie) => (
                                    <div className="current-movie" key={movie.id}>
                                        <div className="current-img-box">
                                            <img src={movie.posterPath} alt={movie.title} />
                                        </div>
                                        <h3 className="movie-title">{movie.title}</h3>
                                        <div className="booking">
                                            <a href="#" className="btn" onClick={() => handleViewDetail(movie)}>View Details</a>
                                        </div>
                                        <div className="booking">
                                            <a href="#" className="btn">Play Trailer</a>
                                        </div>
                                    </div>
                                ))
                            ) : (
                                ""
                            )}




                            {/* </div> */}
                            {/* </Slider> */}
                        </div>

                    </div>


                </section>

            </div>
            <Modal
                isOpen={modalIsOpen}
                onRequestClose={closeModal}
                contentLabel='Video Modal'
                style={{
                    overlay: {
                        backgroundColor: ""
                    },
                    content: {
                        width: "1050px",
                        height: "85vh",
                        margin: "auto",
                        padding: "0px",
                        border: "none",
                        overflow: "hidden"
                    },
                }}
            >
                {selectedMovie && (
                    <div>
                        <ReactPlayer
                            url={selectedMovie.trailerPath}
                            height='85vh'
                            width='1050px'
                            controls={true}
                            className='modal-popup'
                        />
                    </div>
                )}
            </Modal>

            <ToastContainer
                position="bottom-center"
                autoClose={5000}
                hideProgressBar={false}
                newestOnTop={false}
                closeOnClick
                rtl={false}
                pauseOnFocusLoss
                draggable
                pauseOnHover
                theme="colored"
            />
        </div>

    );
}

export default MovieBooking;