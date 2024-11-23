import React, { useState, useEffect } from "react";
import axios from "../api/api";

const PendingBills = ({ studentId }) => {
  const [bills, setBills] = useState([]);

  useEffect(() => {
    const fetchBills = async () => {
      try {
        const response = await axios.get(`/api/bills/pending/${studentId}`);
        setBills(response.data);
      } catch (error) {
        alert("Failed to fetch bills: " + error.message);
      }
    };

    fetchBills();
  }, [studentId]);

  return (
    <div>
      <h3>Pending Bills</h3>
      <ul>
        {bills.map((bill) => (
          <li key={bill.id}>
            {bill.description} - {bill.amount} (Due: {bill.dueDate})
          </li>
        ))}
      </ul>
    </div>
  );
};

export default PendingBills;
