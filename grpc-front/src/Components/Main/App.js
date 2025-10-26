import '../../CSS/App.css';
import { DonacionList } from '../DonacionComponents/DonacionList';
import { DonacionForm } from '../DonacionComponents/DonacionForm';
import { EventoSolidarioList } from '../EventoSolidarioComponents/EventoSolidarioList';
import { EventoSolidarioForm } from '../EventoSolidarioComponents/EventoSolidarioForm';
import { ListaUsuarios } from '../UsuarioComponents/UsuarioList';
import { UsuarioForm } from '../UsuarioComponents/UsuarioForm';
import { MasterLayout } from './Master';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import { UsuarioDetalle } from '../UsuarioComponents/UsuarioDetalle';
import { UsuarioEditar } from '../UsuarioComponents/UsuarioEditar';
import { Login } from '../UsuarioComponents/Login';
import { Navigate } from 'react-router-dom';
import IdInput from '../SoapClient/IdInput';
import AssociationDetail from '../SoapClient/AssociationDetail';
import PresidentList from '../SoapClient/PresidentList';
import SolicitudDonacionForm from '../DonacionComponents/SolicitudDonacionForm';
import EventosExternosList from '../EventoSolidarioComponents/EventosExternosList';
import { SolicitudDonacionList } from '../DonacionComponents/SolicitudDonacionList';
import DonacionTransferir from '../DonacionComponents/DonacionTransferir';

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
                <Route path="/donaciones/editar/:id" element={<DonacionForm/>} />
                <Route path="/eventos" element={<EventoSolidarioList />} />
                <Route path="/eventos/nuevo" element={<EventoSolidarioForm />} />
                <Route path="/eventos/editar/:id" element={<EventoSolidarioForm />} />
                <Route path="/soapclient/input/" element={<IdInput />} />
                <Route path="/soapclient/presidents/" element={<PresidentList />} />
                <Route path="/soapclient/association/" element={<AssociationDetail />} />
                <Route path="/solicitud-donaciones" element={<SolicitudDonacionList/>} />
                <Route path="/solicitud-donaciones/nueva" element={<SolicitudDonacionForm/>} />
                <Route path="/solicitud-donaciones/transferir" element={<DonacionTransferir />} />
                <Route path="/otros-eventos" element={<EventosExternosList />} />
              </Routes>
            </MasterLayout>
          } />
        </Routes>
      </div>
    </Router>
  );
}

export default App;
