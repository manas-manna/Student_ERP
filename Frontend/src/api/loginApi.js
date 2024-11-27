import axios from "axios";

export const loginApi = async (endpoint, payload) => {
    try {
        // Make a GET request to fetch the bills for the given student ID
        
        const response = await axios.post(endpoint, payload);
        
        // Return the data received from the API
    return response; 
    } catch (error) {
        // Throw error to handle it in the component
        console.error('Error fetching bills: ' + error.message); 
    }
    
};