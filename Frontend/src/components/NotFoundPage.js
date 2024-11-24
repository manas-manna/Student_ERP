// src/components/NotFoundPage.js
import React from 'react';
import { Link } from 'react-router-dom';

const NotFoundPage = () => {
  return (
    <div className="text-center mt-10">
      <h1 className="text-3xl font-bold">404 - Page Not Found</h1>
      <p className="mt-4">Oops! The page you're looking for doesn't exist.</p>
      <div className="mt-6">
        <Link to="/" className="mr-4 text-blue-500">Home</Link>
        <Link to="/student" className="mr-4 text-blue-500">Student Page</Link>
        <Link to="/admin" className="text-blue-500">Admin Page</Link>
      </div>
    </div>
  );
};

export default NotFoundPage;
