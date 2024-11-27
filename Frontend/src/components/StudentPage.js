import React  from "react";
import Navbar from "./Navbar";

import PaymentModal from "./PaymentModal"; // Import the PaymentModal component
import PaymentHistory from "./PaymentHistory";
import useStudentDetails from "../hooks/useStudentDetails";



const StudentPage = () => {
  const { loading, handleClosePaymentModal, handleOpenPaymentModal,payments,bills,student, creditBalance,selectedBill }  = useStudentDetails();  



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

        {/* Payment History Section */}
        <PaymentHistory payments={payments} />

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
