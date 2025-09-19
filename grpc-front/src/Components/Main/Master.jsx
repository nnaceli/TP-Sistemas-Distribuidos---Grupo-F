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
                        <li><Link to="/gestion2">Gestión 2</Link></li>
                        <li><Link to="/gestion3">Gestión 3</Link></li>
                    </ul>
                </nav>
            </aside>

            <main className="master-content">
                {children}
            </main>
        </div>
    );
};
