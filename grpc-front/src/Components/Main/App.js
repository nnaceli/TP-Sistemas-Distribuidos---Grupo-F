import '../../CSS/App.css';
import { ListaUsuarios } from '../UsuarioComponents/UsuarioList';
import { UsuarioForm } from '../UsuarioComponents/UsuarioForm';
import { MasterLayout } from './Master';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import { UsuarioDetalle } from '../UsuarioComponents/UsuarioDetalle';
import { UsuarioEditar } from '../UsuarioComponents/UsuarioEditar';

function App() {
  return (
    <Router>
      <div className="App">
        <MasterLayout>
          <Routes>
            <Route path="/usuarios" element={<ListaUsuarios />} />
            <Route path="/usuarios/nuevo" element={<UsuarioForm/>} />
            <Route path="/usuarios/:username" element={<UsuarioDetalle />} />
            <Route path="/usuarios/:username/editar" element={<UsuarioEditar />} />
          </Routes>
        </MasterLayout>
      </div>
    </Router>
  );
}

export default App
