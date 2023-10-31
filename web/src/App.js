import React, { useState } from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
// import Navbar from './components/Navbar';
import { Login } from './pages/Users/login';
import DashBoard from './pages/Admin/DashBoard';
import MovieBooking from './Homepage/home';
import MovieDetails from './pages/MovieDetails/MovieDetails';
import MovieManagement from './pages/Admin/MovieM';
//import UserManagement from './pages/Admin/UserM';
import ConfirmationPage from './pages/ConfirmationPage/ConfirmationPage'
import Promotion from './pages/Admin/promo';
import MovieSeatBooking from './pages/TicketProcess/seatsSelecting';
import AdminPanel from './pages/Admin/adminPanel';
import OrderSummary from './pages/OrderSummary/OrderSummary'
import Checkout from './pages/Checkout/Checkout';
import { Signup } from './pages/Users/signup';
import { NotFound } from './pages/Users/NotFound';
import { UpdateProfile } from './pages/Users/UpdateProfile';
import EmailVerification from './pages/Users/EmailVerification';
import RegistrationSuccessPage from './pages/Users/RegistrationSuccessPage';
import Forgotpass from './pages/Users/Forgotpass';
import { AdminSignup } from './pages/Admin/adminSignup';


function App() {

  const [isLoggedIn, setIsLoggedIn] = useState(false);
  const [isAdmin, setIsAdmin] = useState(false);

  return (
    <Router>
      <Routes>
        <Route path="/" element={<MovieBooking isLoggedIn={isLoggedIn} isAdmin={isAdmin} setIsLoggedIn={setIsLoggedIn}/>} />
        <Route path="/dashboard" element={<DashBoard isAdmin={isAdmin}/>} />
        <Route path="/moviebooking" element={<MovieBooking />} />
        <Route path="/moviedetails" element={<MovieDetails />} />
        <Route path="/movieseatbooking" element={<MovieSeatBooking />} />
        <Route path="/adminpanel" element={<AdminPanel isAdmin={isAdmin}/>} />
        <Route path="/moviemanagement" element={<MovieManagement isAdmin={isAdmin}/>} />
        <Route path="/promotion" element={<Promotion isAdmin={isAdmin}/>} />
        <Route path="/ordersummary" element={<OrderSummary />} />
        <Route path="/checkout" element={<Checkout />} />
        <Route path="/confirmationpage" element={<ConfirmationPage />} />
        <Route path="/login" element={<Login setIsLoggedIn={setIsLoggedIn} setIsAdmin={setIsAdmin}/>} />
        <Route path="/signup" element={<Signup setIsLoggedIn={setIsLoggedIn}/>} />
        <Route path="/adminSignup" element={<AdminSignup setIsLoggedIn={setIsLoggedIn}/>} />
        <Route path="/notfound" element={<NotFound />} />
        <Route path="/updateprofile" element={<UpdateProfile/>} />
        <Route path="/emailverification" element={<EmailVerification/>} />
        <Route path="/registrationsuccess" element={<RegistrationSuccessPage/>} />
        <Route path="/forgotpass" element={<Forgotpass/>} />

      </Routes>
    </Router>

  );
}

export default App;