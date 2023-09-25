import React, { useState } from "react";
import { Link } from "react-router-dom";

    function Navbar() {
  return <nav className = "nav">
    <a href = "/" className = "site-title" >C1 Cinemas </a>
    <ul>
        <li>
            <a href = "/pricing">pricing</a>  
            </li>  
        <li>
            <a href = "/about">about</a>

        </li>
    </ul>

    </nav>
   
}

export default Navbar;