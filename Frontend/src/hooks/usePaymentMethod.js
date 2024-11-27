import { useState, useEffect } from "react";
import axios from "../api/api";

function usePaymentMethod(isOpen, onClose, billId, totalDue, creditBalance, studentId) {

    const [useCredit, setUseCredit] = useState(false);
    const [paymentType, setPaymentType] = useState("total");
    const [customAmount, setCustomAmount] = useState("");
    const [adjustedDue, setAdjustedDue] = useState(totalDue);

    // Adjust the total due dynamically based on `useCredit`
    useEffect(() => {
        if (useCredit) {
            setAdjustedDue(Math.max(0, totalDue - creditBalance)); // Ensure non-negative due
        } else {
            setAdjustedDue(totalDue);
        }
    }, [useCredit, totalDue, creditBalance]);

    const handlePayment = async () => {
        try {
            const paymentAmount =
                paymentType === "total"
                    ? totalDue // Include credit if applicable
                    : parseFloat(customAmount === "" ? 0 : customAmount); // Custom amount + credit

            const paymentData = {
                studentId,
                billId,
                amount: paymentAmount, // Total payment amount
                useCredit, // Inform backend if credit is used
                useTotal: paymentType === "total",
                creditBalance, // Credit balance
                totalDue // Total payment amount
            };

            processPayment();

            alert("Payment successful!");
            onClose();
        } catch (error) {
            console.error("Error processing payment:", error.message);
        }
    };

    return {
        useCredit,
        setUseCredit,
        paymentType,
        setPaymentType,
        customAmount,
        setCustomAmount,
        adjustedDue,
        setAdjustedDue,
        handlePayment
    };
}

export default usePaymentMethod;