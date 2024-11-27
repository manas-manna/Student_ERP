import axios from './api';

// Utility function to fetch users data
export const fetchStudentPayment = async (id) => {
    try {
        const response = await axios.get(`/api/v1/payments`, {
            headers: { studentId: id }
          });
        // Return the data received from the API
        return response; 
    } catch (error) {
        // Throw error to handle it in the component
        console.error('Error fetching payments: ' + error.message);
    }
};
