import '../../CSS/App.css';
import { DonacionList } from '../DonacionComponents/DonacionList';
import { DonacionForm } from '../DonacionComponents/DonacionForm';
import { ListaUsuarios } from '../UsuarioComponents/UsuarioList';
import { UsuarioForm } from '../UsuarioComponents/UsuarioForm';
import { MasterLayout } from './Master';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import { UsuarioDetalle } from '../UsuarioComponents/UsuarioDetalle';
import { UsuarioEditar } from '../UsuarioComponents/UsuarioEditar';
import { Login } from '../UsuarioComponents/Login';
import { Navigate } from 'react-router-dom';

function App() {
  return (
    <Router>
      <div className="App">
        <Routes>
          <Route path="/" element={<Navigate to="/login" />} />
          <Route path="/login" element={<Login />} />

          <Route path="/*" element={
            <MasterLayout>
              <Routes>
                <Route path="/home" element={<div>Bienvenido al sistema</div>} />
                <Route path="/usuarios" element={<ListaUsuarios />} />
                <Route path="/usuarios/nuevo" element={<UsuarioForm />} />
                <Route path="/usuarios/:username" element={<UsuarioDetalle />} />
                <Route path="/usuarios/:username/editar" element={<UsuarioEditar />} />
                <Route path="/donaciones" element={<DonacionList />} />
                <Route path="/donaciones/nueva" element={<DonacionForm/>} />
              </Routes>
            </MasterLayout>
          } />
        </Routes>
      </div>
    </Router>
  );
}

export default App;
