import React from "react";
import { useNavigate } from "react-router-dom";

const Navbar = ({ name, email }) => {
  const navigate = useNavigate();
  return (
    <nav className="bg-gray-800 text-white p-4">
        <div className="container mx-auto flex justify-between items-center">
          <div className="text-lg">
            Welcome, {name} ({email})
          </div>
          <button
            onClick={() => {
              localStorage.removeItem("user");
              localStorage.removeItem("token");
              navigate("/");
            }}
            className="bg-red-600 text-white p-2 rounded hover:bg-red-700"
          >
            Logout
          </button>
        </div>
      </nav>
  );
};

export default Navbar;
