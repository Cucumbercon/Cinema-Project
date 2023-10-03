import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import './App.css';
// import Navbar from './components/Navbar';
import { Login } from './pages/Users/login';
import  Error   from './pages/Users/error';
import MovieBooking from './Homepage/home';
import MovieDetails from './pages/MovieDetails/MovieDetails';
import AdminPanel from './pages/Admin/adminPanel';
import DashBoard from './pages/Admin/DashBoard';
import MovieManagement from './pages/Admin/MovieM';
import Promotion from './pages/Admin/promo';
import MovieSeatBooking from './pages/TicketProcess/seatsSelecting';
import ConfirmationPage from './pages/ConfirmationPage/ConfirmationPage'
import OrderSummary from './pages/OrderSummary/OrderSummary'
import Checkout from './pages/Checkout/Checkout';



function App() {
  return (

    // <MovieBooking />
    // <MovieDetails />
    //  <MovieSeatBooking />
    // <AdminPanel />
     //<DashBoard />
    <MovieManagement />
    // <Promotion />
    // <OrderSummary/>
     //<Checkout/>
     //<ConfirmationPage />
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


//********************** RECEIVING TESTING **************************************//
// import React, { useEffect, useState } from 'react';

// function App() {
//   const [data, setData] = useState('');

//   useEffect(() => {
//     const apiUrl = '/api/data';

//     // Fetch data from the Spring API
//     fetch(apiUrl)
//         .then((response) => response.text())
//         .then((data) => {
//             console.log('Data from Spring:', data); // Log the data received
//             setData(data);
//         })
//         .catch((error) => {
//             console.error('Error fetching data from Spring:', error);
//         });
// }, []);


//   return (
//     <div>
//       <h1>React Frontend</h1>
//       <p>Data from Spring Backend: {data}</p>
//     </div>
//   );
// }

// export default App;



//*******************SENDING TESTING******************//
// function App() {

// const apiUrl = 'http://localhost:8000/api/send-message';
//     const message = 'Hello from React!';

//     fetch(apiUrl, {
//         method: 'POST',
//         headers: {
//             'Content-Type': 'application/json',
//         },
//         body: JSON.stringify({ message }),
//     })
//     .then((response) => response.text())
//     .then((data) => {
//         console.log('Response from Spring:', data);
//     })
//     .catch((error) => {
//         console.error('Error sending message to Spring:', error);
//     });
//   }