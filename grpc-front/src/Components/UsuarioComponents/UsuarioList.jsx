import Usuario from "../../Models/Usuario";
import '../../CSS/UsuarioList.css';


export const ListaUsuarios = () => {
    const usuarios = [
    new Usuario(1, "Juan_Pico", "Juan", "Perez", "123456789", "sdsdsds", "email@email.com", "admin", true),
    new Usuario(2, "Ana_G", "Ana", "Gomez", "987654321", "dsdsds", "ana@mail.com", "user", true),
    new Usuario(3, "LUISm", "Luis", "Martinez", "456123789", "DDSDSD", "LUIS@mail.com", "user", false)
    ];
 
    return (
        <div className="tabla-usuarios-container">
            <h2>Listado de Usuarios</h2>
            <div className="table-responsive">
                <table className="tabla-usuarios">
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Username</th>
                            <th>Nombre</th>
                            <th>Apellido</th>
                            <th>Teléfono</th>
                            <th>Dirección</th>
                            <th>Email</th>
                            <th>Rol</th>
                            <th>Estado</th>
                        </tr>
                    </thead>
                    <tbody>
                        {usuarios.map(usuario => (
                            <tr key={usuario.id} className={usuario.activo ? 'activo' : 'inactivo'}>
                                <td>{usuario.id}</td>
                                <td>@{usuario.username}</td>
                                <td>{usuario.nombre}</td>
                                <td>{usuario.apellido}</td>
                                <td>{usuario.telefono}</td>
                                <td>{usuario.direccion}</td>
                                <td>{usuario.email}</td>
                                <td>
                                    <span className={`rol-badge ${usuario.rol}`}>
                                        {usuario.rol.toUpperCase()}
                                    </span>
                                </td>
                                <td>
                                    <span className={`estado-badge ${usuario.activo ? 'activo' : 'inactivo'}`}>
                                        {usuario.activo ? '🟢 Activo' : '🔴 Inactivo'}
                                    </span>
                                </td>
                            </tr>
                        ))}
                    </tbody>
                </table>
            </div>
        </div>
    );
};
