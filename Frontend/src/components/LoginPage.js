import React, { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import axios from "../api/api";

const LoginPage = () => {
  const [userType, setUserType] = useState("student");
  const [identifier, setIdentifier] = useState(""); // Can be roll number or email
  const [password, setPassword] = useState("");
  const navigate = useNavigate();



  useEffect(() => {
    const user = JSON.parse(localStorage.getItem("user")); // Parse the stored user data
    if (user) {
      if(user.identifier?.startsWith("MT")){
        navigate("/student"); // Redirect to admin page if not a student
        return;
      }else{
        navigate("/admin"); // Redirect to student page if a student
        return;
      }
    }
  }, [navigate]);


    const handleLogin = async () => {
        const endpoint = userType === "admin" ? "/api/v1/auth/admin" : "/api/v1/auth/student";
        const payload =
        userType === "admin"
            ? { identifier: identifier, password }
            : { identifier: identifier, password };

        try {
            
        const response = await axios.post(endpoint, payload);
        
        localStorage.setItem("user", JSON.stringify(response.data));
        localStorage.setItem("token", response.data.token);

        navigate(`/${userType}`);
        } catch (error) {
        alert("Login failed: " + (error.response?.data?.message || "Unknown error"));
        }
    };

  return (
    <div className="flex items-center justify-center min-h-screen bg-gray-100">
      <div className="p-8 bg-white rounded shadow-md w-96">
        <h2 className="text-2xl font-bold mb-6 text-center text-gray-700">Login</h2>

        <div className="mb-4">
          <label htmlFor="userType" className="block text-sm font-medium text-gray-700">
            Select User Type
          </label>
          <select
            id="userType"
            value={userType}
            onChange={(e) => {
              setUserType(e.target.value);
              setIdentifier(""); // Reset the identifier field when user type changes
            }}
            className="mt-1 block w-full border-gray-300 rounded-md shadow-sm focus:ring-indigo-500 focus:border-indigo-500"
          >
            <option value="student">Student</option>
            <option value="admin">Admin</option>
          </select>
        </div>

        <div className="mb-4">
          <label htmlFor="identifier" className="block text-sm font-medium text-gray-700">
            {userType === "admin" ? "Email ID" : "Roll Number"}
          </label>
          <input
            type={userType === "admin" ? "email" : "text"}
            id="identifier"
            placeholder={userType === "admin" ? "Enter your email" : "Enter your roll number"}
            value={identifier}
            onChange={(e) => setIdentifier(e.target.value)}
            className="mt-1 block w-full border-gray-300 rounded-md shadow-sm focus:ring-indigo-500 focus:border-indigo-500"
          />
        </div>

        <div className="mb-4">
          <label htmlFor="password" className="block text-sm font-medium text-gray-700">
            Password
          </label>
          <input
            type="password"
            id="password"
            placeholder="Enter your password"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
            className="mt-1 block w-full border-gray-300 rounded-md shadow-sm focus:ring-indigo-500 focus:border-indigo-500"
          />
        </div>

        <button
          onClick={handleLogin}
          className="w-full px-4 py-2 text-white bg-indigo-600 rounded-md shadow hover:bg-indigo-700 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-indigo-500"
        >
          Login
        </button>
      </div>
    </div>
  );
};

export default LoginPage;
