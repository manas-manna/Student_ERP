import { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import { fetchStudentBills } from "../api/fetchStudentBills";
import { fetchStudentPayment } from "../api/fetchStudentPayment";
function useStudentDetails() {

  const [student, setStudent] = useState({});
  const [bills, setBills] = useState([]);
  const [payments, setPayments] = useState([]);
  const [loading, setLoading] = useState(true);
  const [selectedBill, setSelectedBill] = useState(null); // State to manage the selected bill for the modal
  const [creditBalance, setCreditBalance] = useState(0); // State to store credit balance
  
  const navigate = useNavigate();


  useEffect(() => {
    // Fetch student details from localStorage
    const user = JSON.parse(localStorage.getItem("user"));
  
    if (!user) {
      navigate("/"); // Redirect to admin page if not a student
      return;
    } else if (!user.identifier?.startsWith("MT")) {
      navigate("/admin"); // Redirect to admin page if not a student
      return;
    }
  
  
    // Set the student state
    setStudent({
      firstName: user.firstName,
      email: user.identifier,
      id: user.id,
    });
  }, [navigate]);
  
  useEffect(() => {
    // Fetch student data only after 'student' is updated
    if (!student.id) return; // Ensure student is set before fetching data
  
    const fetchStudentData = async () => {
      try {
  
        const billsResponse = await fetchStudentBills(student.id);
        const paymentsResponse = await fetchStudentPayment(student.id);
  
        setBills(billsResponse.data);
        setPayments(paymentsResponse.data);
        if (billsResponse.data.length !== 0) {
          
          
          setCreditBalance(billsResponse.data[0].creditBalance);
        }
      } catch (error) {
        console.error("Error in fetch student data function:", error.message);
      } finally {
        setLoading(false);
      }
    };
  
    fetchStudentData();
  }, [student]);

  const handleOpenPaymentModal = (bill) => {
    setSelectedBill(bill); // Set the selected bill for the modal
  };

  const handleClosePaymentModal = async () => {
    setSelectedBill(null); // Close the modal
    try {
      // Refresh the bills and payment history after payment
      const billsResponse = await fetchStudentBills(student.id);
      const paymentsResponse = await fetchStudentPayment(student.id);

        setBills(billsResponse.data);
        setPayments(paymentsResponse.data);
        setCreditBalance(billsResponse.data[0].creditBalance);
    } catch (error) {
      console.error("Error refreshing bills in handleclosepaymentmodal:", error);
    }
  };

  return { student, bills, payments, selectedBill, creditBalance, loading, handleClosePaymentModal, handleOpenPaymentModal };
}

export default useStudentDetails;