import React, { useState, useEffect } from "react";
import Navbar from "./Navbar";
import axios from "../api/api";
import PaymentModal from "./PaymentModal"; // Import the PaymentModal component

const StudentPage = () => {
  const [student, setStudent] = useState({});
  const [bills, setBills] = useState([]);
  const [loading, setLoading] = useState(true);
  const [selectedBill, setSelectedBill] = useState(null); // State to manage the selected bill for the modal
  const [creditBalance, setCreditBalance] = useState(0); // State to store credit balance

  useEffect(() => {
    // Fetch student details from localStorage
    const user = JSON.parse(localStorage.getItem("user"));
    setStudent({
      firstName: user.firstName,
      email: user.identifier,
      id: user.id,
    });

    // Fetch bills and credit balance for the student
    const fetchStudentData = async () => {
      try {
        const billsResponse = await axios.get(`/api/v1/students/bills/${user.id}`);
        
        
        setBills(billsResponse.data);
        if(billsResponse.data.length !== 0) setCreditBalance(billsResponse.data[0].creditBalance);

        // const creditResponse = await axios.get(`/api/v1/students/${user.id}/credit`);
        // setCreditBalance(creditResponse.data.creditBalance || 0);
      } catch (error) {
        console.error("Error fetching student data:", error);
        alert("Failed to load student data.",error);
      } finally {
        setLoading(false);
      }
    };

    fetchStudentData();
  }, []);

  const handleOpenPaymentModal = (bill) => {
    setSelectedBill(bill); // Set the selected bill for the modal
  };

  const handleClosePaymentModal = async () => {
    setSelectedBill(null); // Close the modal
    try {
      // Refresh the bills after payment
      const response = await axios.get(`/api/v1/students/bills/${student.id}`);
      setBills(response.data);
    } catch (error) {
      console.error("Error refreshing bills:", error);
    }
  };

  return (
    <div className="min-h-screen bg-gray-100">
      {/* Navbar */}
      <Navbar name={student.firstName} email={student.email} />

      {/* Main Content */}
      <div className="container mx-auto px-4 py-6">
        {/* Bills Section */}
        <div className="mt-8">
          <h3 className="text-xl font-semibold text-gray-700">Pending Bills</h3>
          {loading ? (
            <p className="mt-4 text-gray-600">Loading bills...</p>
          ) : bills.length === 0 ? (
            <p className="mt-4 text-gray-600">You have no pending bills.</p>
          ) : (
            <table className="table-auto w-full mt-4 border-collapse border border-gray-300">
              <thead>
                <tr className="bg-gray-200">
                  <th className="border border-gray-300 px-4 py-2">Description</th>
                  <th className="border border-gray-300 px-4 py-2">Total Amount</th>
                  <th className="border border-gray-300 px-4 py-2">Due Date</th>
                  <th className="border border-gray-300 px-4 py-2">Deadline Date</th>
                  <th className="border border-gray-300 px-4 py-2">Pay</th>
                </tr>
              </thead>
              <tbody>
                {bills.map((bill) => (
                  <tr key={bill.billId} className="hover:bg-gray-100">
                    <td className="border border-gray-300 px-4 py-2">{bill.description}</td>
                    <td className="border border-gray-300 px-4 py-2">{bill.amount}</td>
                    <td className="border border-gray-300 px-4 py-2">{bill.dueDate}</td>
                    <td className="border border-gray-300 px-4 py-2">{bill.deadline}</td>
                    <td className="border border-gray-300 px-4 py-2 text-center">
                      <button
                        onClick={() => handleOpenPaymentModal(bill)} // Open the payment modal
                        className="bg-blue-500 text-white px-3 py-1 rounded-md hover:bg-blue-600"
                      >
                        Pay
                      </button>
                    </td>
                  </tr>
                ))}
              </tbody>
            </table>
          )}
        </div>
      </div>

      {/* Payment Modal */}
      {selectedBill && (
        <PaymentModal
          isOpen={!!selectedBill}
          onClose={handleClosePaymentModal}
          billId={selectedBill.billId}
          totalDue={selectedBill.currentDue}
          creditBalance={creditBalance}
          studentId={student.id}
        />
      )}
    </div>
  );
};

export default StudentPage;
