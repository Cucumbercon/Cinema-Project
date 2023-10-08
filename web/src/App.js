import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
// import Navbar from './components/Navbar';
import { Login } from './pages/Users/login';
import DashBoard from './pages/Admin/DashBoard';
import MovieBooking from './Homepage/home';
import MovieDetails from './pages/MovieDetails/MovieDetails';
import MovieManagement from './pages/Admin/MovieM';
import UserManagement from './pages/Admin/UserM';
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


function App() {
  return (

    // <MovieBooking />
    // <MovieDetails />
    // <MovieSeatBooking />
    // <OrderSummary/>
    // <Checkout/>
    // <ConfirmationPage />
    // <AdminPanel />
    //  <DashBoard />
    // <Promotion />
    // <OrderSummary/>
     //<Checkout/>
     //<ConfirmationPage />
      // <AdminPanel /> 
     //  <Login />  
      <Signup /> 
      // < NotFound /> 
    //<UpdateProfile />
    
  );

  // Alternatively, you can use the following code to set up routing with a Navbar:
  // return (
  //   <>
  //     <Router>
  //       <Navbar />
  //       <Routes>
  //         <Route path="/" element={<Home />} />
  //         <Route path="/dashboard" element={<DashBoard />} />
  //         <Route path="/moviebooking" element={<MovieBooking />} />
  //         <Route path="/moviedetails" element={<MovieDetails />} />
  //         <Route path="/movieseatbooking" element={<MovieSeatBooking />} />
  //         <Route path="/adminpanel" element={<AdminPanel />} />
  //         <Route path="/moviemanagement" element={<MovieManagement />} />
  //         <Route path="/promotion" element={<Promotion />} />
  //         <Route path="/ordersummary" element={<OrderSummary />} />
  //         <Route path="/checkout" element={<Checkout />} />
  //         <Route path="/confirmationpage" element={<ConfirmationPage />} />
  //         <Route path="/login" element={<Login />} />
  //         <Route path="/signup" element={<Signup />} />
  //         <Route path="/notfound" element={<NotFound />} />
  //       </Routes>
  //     </Router>
  //   </>
  // );
}

export default App;
