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


//********************** TESTING **************************************//
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
