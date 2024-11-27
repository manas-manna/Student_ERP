import axios from './api';

// Utility function to fetch users data
export const fetchStudentBills = async (id) => {
    try {
        // Make a GET request to fetch the bills for the given student ID
        
        const response = await axios.get(`/api/v1/students/bills`, {
            headers: { studentId: id }
          });
        
        // Return the data received from the API
    return response; 
    } catch (error) {
        // Throw error to handle it in the component
        console.error('Error fetching bills: ' + error.message); 
    }
    
};
