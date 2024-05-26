document.addEventListener('DOMContentLoaded', () => {
    const registerForm = document.getElementById('registerForm');
    const healthForm = document.getElementById('healthForm');
    const paymentForm = document.getElementById('paymentForm');
    const responseDiv = document.getElementById('response');

    registerForm.addEventListener('submit', (e) => {
        e.preventDefault();
        const formData = new FormData(registerForm);
        fetch('/PatientServlet', {
            method: 'POST',
            body: new URLSearchParams(formData)
        })
        .then(response => response.text())
        .then(data => {
            responseDiv.innerText = data;
        })
        .catch(error => console.error('Error:', error));
    });

    healthForm.addEventListener('submit', (e) => {
        e.preventDefault();
        const formData = new FormData(healthForm);
        fetch('/PatientServlet', {
            method: 'POST',
            body: new URLSearchParams(formData)
        })
        .then(response => response.text())
        .then(data => {
            responseDiv.innerText = data;
        })
        .catch(error => console.error('Error:', error));
    });

    paymentForm.addEventListener('submit', (e) => {
        e.preventDefault();
        const formData = new FormData(paymentForm);
        fetch('/PatientServlet', {
            method: 'POST',
            body: new URLSearchParams(formData)
        })
        .then(response => response.text())
        .then(data => {
            responseDiv.innerText = data;
        })
        .catch(error => console.error('Error:', error));
    });
});