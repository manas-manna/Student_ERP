async function processPayment(paymentData){
    await axios.post(`/api/v1/payments`, paymentData);
}

export default processPayment;