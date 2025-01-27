import axios from 'axios';

export const login = async (loginRequestDTO) => {
    const response = await axios.post('http://localhost:8888/ETUDIANT-SERVICE/auth/login', null, {
        params: loginRequestDTO,
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
        }
    });

    return response.data;
};
