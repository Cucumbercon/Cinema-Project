import { link } from "react-router-dom";
import React from "react";
import './NotFound.css'

export const LoginFail = () => {
    return (
        <div className="not-found">
            <h2>Sorry!</h2>
            <p>Email and/or password do not match</p>



        </div>
    );
}

export default LoginFail;