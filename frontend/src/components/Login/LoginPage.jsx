import React, { useState, useContext } from 'react';
import { useNavigate } from 'react-router-dom';
import './LoginPage.css';
import hero from "../../../public/hero.png";
import { login } from "../../api/loginApi.js";
import { toast, ToastContainer } from "react-toastify";
import 'react-toastify/dist/ReactToastify.css';
import {jwtDecode} from 'jwt-decode';
import {AuthContext} from "../contexe/AuthContext.jsx";
import Cookies from 'js-cookie';


const LoginPage = () => {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const { setIsAuthenticated, setUserProfile } = useContext(AuthContext);
    const navigate = useNavigate();

    const handleLogin = async (e) => {
        e.preventDefault();
        try {
            const loginRequestDTO = { username, password };
            const response = await login(loginRequestDTO);

            if (response && response.token) {
                localStorage.setItem('token', response.token);
                const decodedToken = jwtDecode(response.token);
                const username = decodedToken.sub;
                const role = decodedToken.scope;
                setUserProfile({ username, role });
                setIsAuthenticated(true);

                toast.success('Login successful!');
                if(role === "ADMIN USER"){
                    navigate('/admin/landing');
                }else if(role === "USER"){

                    Cookies.set('token', localStorage.getItem('token'), {
                        domain: 'localhost',
                        path: '/',
                        sameSite: 'None',
                        secure: false
                    });

                        window.location.href = 'http://localhost:5174/';
                }

            } else {
                throw new Error('Login failed. No token received.');
            }
        } catch (error) {
            console.error('Login failed', error);
            toast.error('Login failed. Please check your credentials and try again.');
        }
    };

    return (
        <div className="loginContainer">
            <ToastContainer />
            <section className="right">
                <h1>
                    <span>❝</span> Votre Solution pour une <br />
                    Gestion Académique Simplifiée
                </h1>
                <img src={hero} alt="Hero" />
            </section>

            <section className="left">
                <div className="wrap">
                    <form onSubmit={handleLogin}>
                        <h1>Login</h1>

                        <div className="box">
                            <input
                                type="text"
                                name="username"
                                placeholder="Username"
                                value={username}
                                onChange={(e) => setUsername(e.target.value)}
                                required
                            />
                            <i className="bx bxs-user"></i>
                        </div>

                        <div className="box">
                            <input
                                type="password"
                                name="pwd"
                                placeholder="Password"
                                value={password}
                                onChange={(e) => setPassword(e.target.value)}
                                required
                            />
                            <i className="bx bxs-lock-alt"></i>
                        </div>

                        <button type="submit" className="btn">Login</button>
                    </form>
                </div>
            </section>
        </div>
    );
};

export default LoginPage;
