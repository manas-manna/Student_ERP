import React from "react";

import usePaymentMethod from "../hooks/usePaymentMethod";

const PaymentModal = ({ isOpen, onClose, billId, totalDue, creditBalance, studentId }) => {

  const { useCredit,
    setUseCredit,
    paymentType,
    setPaymentType,
    customAmount,
    setCustomAmount,
    adjustedDue,
    handlePayment } = usePaymentMethod(isOpen, onClose, billId, totalDue, creditBalance, studentId);


  if (!isOpen) return null;

  return (
    <div className="fixed inset-0 z-50 flex items-center justify-center bg-black bg-opacity-50">
      <div className="bg-white p-6 rounded-lg shadow-lg w-96">
        <h2 className="text-xl font-semibold mb-4">Make a Payment</h2>
        <p className="mb-4">Current Due: ₹{adjustedDue.toFixed(2)}</p>

        {creditBalance > 0 && (
          <div className="mb-4">
            <label className="flex items-center space-x-2">
              <input
                type="checkbox"
                checked={useCredit}
                onChange={(e) => setUseCredit(e.target.checked)}
                className="form-checkbox"
              />
              <span>Use Credit Balance (₹{creditBalance.toFixed(2)})</span>
            </label>
          </div>
        )}

        <div className="mb-4">
          <label className="block mb-2">Select Payment Type:</label>
          <label className="flex items-center space-x-2 mb-2">
            <input
              type="radio"
              name="paymentType"
              value="total"
              checked={paymentType === "total"}
              onChange={(e) => setPaymentType("total")}
              className="form-radio"
            />
            <span>Pay Total Amount</span>
          </label>
          <label className="flex items-center space-x-2">
            <input
              type="radio"
              name="paymentType"
              value="custom"
              checked={paymentType === "custom"}
              onChange={() => setPaymentType("custom")}
              className="form-radio"
            />
            <span>Pay Custom Amount</span>
          </label>
        </div>

        {paymentType === "custom" && (
          <div className="mb-4">
            <label className="block mb-2">Enter Custom Amount:</label>
            <input
              type="number"
              value={customAmount}
              onChange={(e) => setCustomAmount(e.target.value)}
              className="w-full border border-gray-300 px-3 py-2 rounded-md"
              placeholder="Enter amount"
            />
          </div>
        )}

        <div className="flex justify-end space-x-4">
          <button
            onClick={onClose}
            className="bg-gray-400 text-white px-4 py-2 rounded-md hover:bg-gray-500"
          >
            Cancel
          </button>
          <button
            onClick={handlePayment}
            className="bg-blue-500 text-white px-4 py-2 rounded-md hover:bg-blue-600"
          >
            Pay
          </button>
        </div>
      </div>
    </div>
  );
};

export default PaymentModal;
