import React from 'react';
import { Link } from 'react-router-dom';
import '../../CSS/Master.css';

export const MasterLayout = ({ children }) => {
    return (
        <div className="master-layout">
            <aside className="sidebar">
                <h2 className="sidebar-title">Sistema gRPC</h2>
                <nav className="sidebar-nav">
                    <ul>
                        <li><Link to="/usuarios">Gestión de usuarios</Link></li>
                        <li><Link to="/donaciones">Inventario de donaciones</Link></li>
                        <li><Link to="/eventos">Eventos solidarios</Link></li>
                        <li><Link to="/soapclient/input/">Soap Client</Link></li>
                        <li><Link to="/solicitud-donaciones/">Solicitudes de Donaciones </Link></li>
                        <li><Link to="/otros-eventos">Eventos Externos</Link></li>
                    </ul>
                </nav>
            </aside>

            <main className="master-content">
                {children}
            </main>
        </div>
    );
};
