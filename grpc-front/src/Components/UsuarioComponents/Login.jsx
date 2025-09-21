import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import '../../CSS/Login.css';
import { loginUsuario } from '../../Service/LoginService';

export const Login = () => {
    const navigate = useNavigate();
    const [formData, setFormData] = useState({ usuario: '', password: '' });
    const [showPassword, setShowPassword] = useState(false);
    const [errorMessage, setErrorMessage] = useState('');

    const handleChange = e => {
        const { name, value } = e.target;
        setFormData(prev => ({ ...prev, [name]: value }));
    };

    const handleTogglePasswordVisibility = () => {
        setShowPassword(prev => !prev);
    };

  const handleSubmit = async e => {
    e.preventDefault();
    setErrorMessage('');

    try {
        const userSession = await loginUsuario(formData.usuario, formData.password);

        localStorage.setItem('userSession', JSON.stringify(userSession));

        navigate('/home');
    } catch (error) {
        setErrorMessage(error.message);
    }
};

    return (
        <div className="login-principal-box">
            <div className="login-form-div">
                <form onSubmit={handleSubmit}>
                    <h2 className="login-title">Bienvenido a Sistema gRPC del GRUPO F</h2>
                    <p className="login-subtitle">Ingresa tus datos para continuar</p>

                    <label className="login-label">Usuario</label>
                    <div className="login-input-container">
                        <span className="login-icon">👤</span>
                        <input
                            type="text"
                            name="usuario"
                            placeholder=" usuario"
                            value={formData.usuario}
                            onChange={handleChange}
                            className="login-input"
                            required
                        />
                    </div>

                    <label className="login-label">Contraseña</label>
                    <div className="login-input-container">
                        <span className="login-icon">🔒</span>
                        <input
                            type={showPassword ? 'text' : 'password'}
                            name="password"
                            placeholder=" contraseña"
                            value={formData.password}
                            onChange={handleChange}
                            className="login-input"
                            required
                        />
                        <span
                            className="login-toggle-pass"
                            onClick={handleTogglePasswordVisibility}
                        >
                            {showPassword ? '🙈' : '👁️'}
                        </span>
                    </div>
                     {errorMessage && <p className="login-error">{errorMessage}</p>}
                    <button type="submit"className="login-button">Iniciar Sesión</button>
                </form>
            </div>
        </div>
    );
};
