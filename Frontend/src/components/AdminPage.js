import React, { useEffect, useState } from "react";
import axios from "../api/api";
import { useNavigate } from "react-router-dom";

import Navbar from "./Navbar";

const AdminPage = () => {
  const navigate = useNavigate();
  const [admin, setAdmin] = useState({ firstName: "", email: "" });
  const [students, setStudents] = useState([]);
  const [selectedStudentId, setSelectedStudentId] = useState("");
  const [billDescription, setBillDescription] = useState("");
  const [amount, setAmount] = useState("");
  const [dueDate, setDueDate] = useState("");
  const [deadline, setDeadline] = useState("");


  // Fetch admin and students from the backend
  useEffect(() => {
    const user = JSON.parse(localStorage.getItem("user"));
    
    if (!user) {
      navigate("/"); // Redirect to admin page if not a student
      return;
    }else if(user.identifier?.startsWith("MT")){
      navigate("/student"); // Redirect to admin page if not a student
      return;
    }


      setAdmin({
        firstName: user.firstName,
        email: user.identifier,
      });

    //Fetch all students for the dropdown
    const fetchStudents = async () => {
      try {
        const response = await axios.get("/api/v1/students"); // Adjust the endpoint as per your backend
        
        
        setStudents(response.data); // Assume response contains an array of students with id and rollNumber
      } catch (error) {
        console.error("Error fetching students:", error);
      }
    };

    fetchStudents();
  }, []);

  // Handle bill creation
  const handleCreateBill = async (event) => {
    event.preventDefault();

    if (!selectedStudentId || !billDescription || !amount || !dueDate || !deadline) {
      alert("Please fill all fields.");
      return;
    }

    const billData = {
      studentId: selectedStudentId, // Use the selected student ID
      description: billDescription,
      amount: parseFloat(amount),
      dueDate: dueDate,
      deadline: deadline,
    };

    try {
      await axios.post("/api/v1/bills", billData); // Adjust endpoint for bill creation
      alert("Bill created successfully!");
      setBillDescription(""); // Clear form after submission
      setAmount("");
      setDueDate("");
      setDeadline("");
      setSelectedStudentId("");
    } catch (error) {
      alert("Error creating bill: " + (error.response?.data?.message || "Unknown error"));
    }
  };

  return (
    <div className="min-h-screen flex flex-col">
      {/* Navbar */}
      <Navbar name={admin.firstName} email={admin.email} />

      {/* Main Content */}
      <div className="container mx-auto p-8">
        <h2 className="text-2xl font-bold mb-4">Create a Bill</h2>

        <form onSubmit={handleCreateBill} className="space-y-4">
          {/* Dropdown to select student */}
          <div>
            <label htmlFor="student" className="block text-lg font-medium">
              Select Student
            </label>
            <select
              id="student"
              value={selectedStudentId}
              onChange={(e) => setSelectedStudentId(e.target.value)}
              className="w-full mt-2 p-2 border rounded-md focus:outline-none focus:ring-2 focus:ring-indigo-500"
            >
              <option value="">-- Select a student --</option>
              {students.map((student) => (
                <option key={student.studentId} value={student.studentId}>
                  {student.rollNumber}( {student.name} )
                </option>
              ))}
            </select>
          </div>

          {/* Bill Description */}
          <div>
            <label htmlFor="billDescription" className="block text-lg font-medium">
              Bill Description
            </label>
            <input
              id="billDescription"
              type="text"
              value={billDescription}
              onChange={(e) => setBillDescription(e.target.value)}
              className="w-full mt-2 p-2 border rounded-md focus:outline-none focus:ring-2 focus:ring-indigo-500"
              placeholder="Enter bill description"
            />
          </div>

          {/* Amount */}
          <div>
            <label htmlFor="amount" className="block text-lg font-medium">
              Amount
            </label>
            <input
              id="amount"
              type="number"
              value={amount}
              onChange={(e) => setAmount(e.target.value)}
              className="w-full mt-2 p-2 border rounded-md focus:outline-none focus:ring-2 focus:ring-indigo-500"
              placeholder="Enter bill amount"
            />
          </div>

          {/* Due Date */}
          <div>
            <label htmlFor="dueDate" className="block text-lg font-medium">
              Due Date
            </label>
            <input
              id="dueDate"
              type="date"
              value={dueDate}
              onChange={(e) => setDueDate(e.target.value)}
              className="w-full mt-2 p-2 border rounded-md focus:outline-none focus:ring-2 focus:ring-indigo-500"
            />
          </div>

          {/* Deadline */}
          <div>
            <label htmlFor="deadline" className="block text-lg font-medium">
              Deadline
            </label>
            <input
              id="deadline"
              type="date"
              value={deadline}
              onChange={(e) => setDeadline(e.target.value)}
              className="w-full mt-2 p-2 border rounded-md focus:outline-none focus:ring-2 focus:ring-indigo-500"
            />
          </div>

          {/* Submit Button */}
          <button
            type="submit"
            className="w-full mt-4 bg-blue-600 text-white p-2 rounded-md hover:bg-blue-700"
          >
            Create Bill
          </button>
        </form>
      </div>
    </div>
  );
};

export default AdminPage;
