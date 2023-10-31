import React, { useState } from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import {toast,ToastContainer} from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
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
import { LoginFail } from './pages/Users/LoginFail';
import { UpdateProfile } from './pages/Users/UpdateProfile';
import EmailVerification from './pages/Users/EmailVerification';
import RegistrationSuccessPage from './pages/Users/RegistrationSuccessPage';
import Forgotpass from './pages/Users/Forgotpass';
import { adminSignup } from './pages/Admin/adminSignup';


function App() {

  const [isLoggedIn, setIsLoggedIn] = useState(false);
  const [isAdmin, setIsAdmin] = useState(false);
  const [name, setName] = useState("");

  return (
    <Router>
      <Routes>
        <Route path="/" element={<MovieBooking isLoggedIn={isLoggedIn} isAdmin={isAdmin} setIsLoggedIn={setIsLoggedIn} setIsAdmin={setIsAdmin} name={name}/>} />
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
        <Route path="/login" element={<Login setIsLoggedIn={setIsLoggedIn} setIsAdmin={setIsAdmin} setName={setName}/>} />
        <Route path="/signup" element={<Signup setIsLoggedIn={setIsLoggedIn}/>} />
        <Route path="/adminSignup" element={<adminSignup setIsLoggedIn={setIsLoggedIn}/>} />
        <Route path="/notfound" element={<NotFound />} />
        <Route path="/loginFail" element={<LoginFail />} />
        <Route path="/updateprofile" element={<UpdateProfile/>} />
        <Route path="/emailverification" element={<EmailVerification/>} />
        <Route path="/registrationsuccess" element={<RegistrationSuccessPage/>} />
        <Route path="/forgotpass" element={<Forgotpass/>} />

      </Routes>
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
    </Router>

  );
}

export default App;
