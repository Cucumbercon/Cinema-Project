import React, { useState, useEffect } from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import { Login } from './pages/Users/login';
import DashBoard from './pages/Admin/DashBoard';
import MovieBooking from './Homepage/home';
import MovieDetails from './pages/MovieDetails/MovieDetails';
import ScheduleMovie from './pages/Admin/ScheduleMovie';
import MovieManagement from './pages/Admin/MovieM';
import UserManagement from './pages/Admin/UserM';
import ConfirmationPage from './pages/ConfirmationPage/ConfirmationPage'
import Promotion from './pages/Admin/promo';
import MovieSeatBooking from './pages/TicketProcess/seatsSelecting';
import AdminPanel from './pages/Admin/adminPanel';
import OrderSummary from './pages/OrderSummary/OrderSummary';
import OrderHistory from './pages/OrderHistory/OrderHistory';
import { Signup } from './pages/Users/signup';
import { NotFound } from './pages/Users/NotFound';
import { UpdateProfile } from './pages/Users/UpdateProfile';
import EmailVerification from './pages/Users/EmailVerification';
import RegistrationSuccessPage from './pages/Users/RegistrationSuccessPage';
import Forgotpass from './pages/Users/Forgotpass';
import { AdminSignup } from './pages/Admin/adminSignup';
import  UpdateMovie  from './pages/Admin/UpdateMovie';
import ShoppingCart from './pages/Users/shoppingcart';




function App() {

  const [isAdmin, setIsAdmin] = useState(false);
  // const [name, setName] = useState("");

  useEffect(() => {
    const storedIsLoggedIn = localStorage.getItem('isLoggedIn') === 'true';
    const storedAdminLoggedIn = localStorage.getItem('isAdminLoggedIn') === 'true';
    const storedName = localStorage.getItem('name') === '';
  }, []);

  return (
    <Router>
      <Routes>
        <Route path="/" element={<MovieBooking />} />
        <Route path="/moviebooking" element={<MovieBooking />} />
        <Route path="/moviedetails/:id" element={<MovieDetails />} />
        <Route path="/shoppingcart" element={<ShoppingCart />} /> 
        <Route path="/movieseatbooking" element={<MovieSeatBooking />} />
        <Route path="/adminpanel" element={<AdminPanel />} />
        <Route path="/dashboard" element={<DashBoard isAdmin={isAdmin} />} />
        <Route path="/scheduleMovie" element={<ScheduleMovie isAdmin={isAdmin}/>} />
        <Route path="/moviemanagement" element={<MovieManagement />} />
        <Route path="/usermanagement" element={<UserManagement isAdmin={isAdmin} />} />
        <Route path="/promotion" element={<Promotion />} />
        <Route path="/ordersummary" element={<OrderSummary />} />
        <Route path="/orderhistory" element={<OrderHistory />} />
        <Route path="/confirmationpage" element={<ConfirmationPage />} />
        <Route path="/login" element={<Login />} />
        <Route path="/signup" element={<Signup />} />
        <Route path="/adminSignup" element={<AdminSignup />} />
        <Route path="/notfound" element={<NotFound />} />
        <Route path="/updateprofile" element={<UpdateProfile />} />
        <Route path="/emailverification" element={<EmailVerification />} />
        <Route path="/registrationsuccess" element={<RegistrationSuccessPage />} />
        <Route path="/forgotpass" element={<Forgotpass />} />
        <Route path="/updatemovie" element={<UpdateMovie />} />
        <Route path="*" element={<NotFound />} />
      </Routes>
    </Router>

  );
}

export default App;