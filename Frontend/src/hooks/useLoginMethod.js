import { useState, useEffect } from "react";
import axios from "../api/api";
import { useNavigate } from "react-router-dom";
import { loginApi } from "../api/loginApi";
function useLoginMethod() {
    const [userType, setUserType] = useState("student");
    const [identifier, setIdentifier] = useState(""); // Can be roll number or email
    const [password, setPassword] = useState("");
    const navigate = useNavigate();
    const handleLogin = async () => {
        const endpoint = userType === "admin" ? "/api/v1/auth/admin" : "/api/v1/auth/student";
        const payload =
            userType === "admin"
                ? { identifier: identifier, password }
                : { identifier: identifier, password };

        try {

            const response = loginApi(endpoint, payload);

            localStorage.setItem("user", JSON.stringify(response.data));
            localStorage.setItem("token", response.data.token);

            navigate(`/${userType}`);
        } catch (error) {
            alert("Login failed: " + (error.response?.data?.message || "Unknown error"));
        }
    };
    return { userType, setUserType, identifier, setIdentifier, password, setPassword, handleLogin }
}

export default useLoginMethod;