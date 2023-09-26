import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import './App.css';
// import Navbar from './components/Navbar';
import MovieBooking from './Homepage/home';

function App() {
  return (
    <MovieBooking />
  );
  // return (
  //   <>
  //     <Router>
  //       <Navbar />
  //       <Routes>
  //         <Route path="/" exact />
  //       </Routes>
  //     </Router>
  //   </>
  // );
}

export default App;
