import axios from 'axios';

const API_URL = 'http://localhost:8080/api'; // Asegúrate de que tu Spring Boot esté corriendo aquí

export const getAllContent = async () => {
    try {
        const response = await axios.get(`${API_URL}/content`);
        return response.data;
    } catch (error) {
        console.error("Error buscando contenido:", error);
        return [];
    }
};