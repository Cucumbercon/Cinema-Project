import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import './App.css';
// import Navbar from './components/Navbar';
import MovieBooking from './Homepage/home';
import AdminPanel from './pages/Admin/adminPanel';

function App() {
  return (
    // <AdminPanel />
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
