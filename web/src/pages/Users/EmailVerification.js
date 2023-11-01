// EmailVerification.js
import React, { useState } from 'react';
import './EmailVerification.css';
import { useNavigate } from 'react-router-dom';
import { useLocation } from 'react-router-dom';


function EmailVerification() {
  const location = useLocation();
  const emailFromPreviousPage = location.state?.email || ""; // 获取之前页面传递的email
  const [email, setEmail] = useState(emailFromPreviousPage);
  const [code, setCode] = useState('');
  const [isValidCode, setIsValidCode] = useState(true);
  const navigate = useNavigate();

  const handleCodeChange = (e) => {
    const enteredCode = e.target.value;
    setCode(enteredCode);

    // Validates the code format (6 digits)
    const codeRegex = /^\d{6}$/;
    setIsValidCode(codeRegex.test(enteredCode));
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    if (isValidCode) {
        // 发送POST请求到后端
        const response = await fetch('http://localhost:8000/api/verifyCode', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({
                email: email,
                code: code,
            }),
        });

        // 根据响应处理逻辑，例如：
        if (response.ok) {
            // const data = await response.json();
            // 根据返回的数据进行进一步处理，例如导航到下一个页面
            // console.log('Code verified:', data);
            navigate("/registrationsuccess");
        } else {
            // 处理错误
            console.error('Failed to verify code');
        }
    } else {
        console.error('Invalid code format');
    }
};


  return (
    <div className="registration-page">
      <h1>Registration</h1>
      <p>Enter the 6-digit code sent to your email address:</p>
      <form onSubmit={handleSubmit}>
        <input
          type="text"
          placeholder="XXXXXX"
          value={code}
          onChange={handleCodeChange}
          pattern="\d{6}" // 只接受6位数字
          title="Please enter a 6-digit code." // 当用户输入不匹配时显示的提示
        />
        {!isValidCode && <p className="error-message">Invalid code format</p>}
        <button type="submit">Register</button>
      </form>
    </div>
  );
}

export default EmailVerification;
