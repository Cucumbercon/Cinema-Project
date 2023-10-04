import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
// import Navbar from './components/Navbar';
import MovieBooking from './Homepage/home';
import AdminPanel from './pages/Admin/adminPanel';
import { Login } from './pages/Users/login';
import { Signup }   from './pages/Users/signup';
import { NotFound } from './pages/Users/NotFound';
import { UpdateProfile } from './pages/Users/UpdateProfile';

function App() {
  return (
    
      // <AdminPanel /> 
      <MovieBooking /> 
      // <Login />  
      // <Signup /> 
      // < NotFound /> 
      //<UpdateProfile />
     
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