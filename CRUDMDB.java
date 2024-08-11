import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

// Clase para la conexión a la base de datos
public class DatabaseConnection {
    private static final String URL = "jdbc:mariadb://localhost:3306/Colegio";
    private static final String USER = "tu_usuario";
    private static final String PASSWORD = "tu_contraseña";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}

// Clase Estudiante
public class Estudiante {
    private int id;
    private String nombre;
    private String apellido;
    private String fechaNacimiento;
    private String genero;
    private String direccion;
    private String telefono;
    private String correoElectronico;
    private String grado;
    private String fechaInscripcion;

    // Getters y setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getApellido() { return apellido; }
    public void setApellido(String apellido) { this.apellido = apellido; }
    public String getFechaNacimiento() { return fechaNacimiento; }
    public void setFechaNacimiento(String fechaNacimiento) { this.fechaNacimiento = fechaNacimiento; }
    public String getGenero() { return genero; }
    public void setGenero(String genero) { this.genero = genero; }
    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }
    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }
    public String getCorreoElectronico() { return correoElectronico; }
    public void setCorreoElectronico(String correoElectronico) { this.correoElectronico = correoElectronico; }
    public String getGrado() { return grado; }
    public void setGrado(String grado) { this.grado = grado; }
    public String getFechaInscripcion() { return fechaInscripcion; }
    public void setFechaInscripcion(String fechaInscripcion) { this.fechaInscripcion = fechaInscripcion; }
}

// Clase para las operaciones CRUD
public class EstudianteDAO {
    // Crear (Create)
    public void agregarEstudiante(Estudiante estudiante) {
        String sql = "INSERT INTO estudiantes (nombre, apellido, fecha_nacimiento, genero, direccion, telefono, correo_electronico, grado, fecha_inscripcion) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, estudiante.getNombre());
            pstmt.setString(2, estudiante.getApellido());
            pstmt.setString(3, estudiante.getFechaNacimiento());
            pstmt.setString(4, estudiante.getGenero());
            pstmt.setString(5, estudiante.getDireccion());
            pstmt.setString(6, estudiante.getTelefono());
            pstmt.setString(7, estudiante.getCorreoElectronico());
            pstmt.setString(8, estudiante.getGrado());
            pstmt.setString(9, estudiante.getFechaInscripcion());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Leer (Read)
    public List<Estudiante> obtenerEstudiantes() {
        List<Estudiante> estudiantes = new ArrayList<>();
        String sql = "SELECT * FROM estudiantes";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Estudiante estudiante = new Estudiante();
                estudiante.setId(rs.getInt("id"));
                estudiante.setNombre(rs.getString("nombre"));
                estudiante.setApellido(rs.getString("apellido"));
                estudiante.setFechaNacimiento(rs.getString("fecha_nacimiento"));
                estudiante.setGenero(rs.getString("genero"));
                estudiante.setDireccion(rs.getString("direccion"));
                estudiante.setTelefono(rs.getString("telefono"));
                estudiante.setCorreoElectronico(rs.getString("correo_electronico"));
                estudiante.setGrado(rs.getString("grado"));
                estudiante.setFechaInscripcion(rs.getString("fecha_inscripcion"));

                estudiantes.add(estudiante);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return estudiantes;
    }

    // Actualizar (Update)
    public void actualizarEstudiante(Estudiante estudiante) {
        String sql = "UPDATE estudiantes SET nombre = ?, apellido = ?, fecha_nacimiento = ?, genero = ?, direccion = ?, telefono = ?, correo_electronico = ?, grado = ?, fecha_inscripcion = ? WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, estudiante.getNombre());
            pstmt.setString(2, estudiante.getApellido());
            pstmt.setString(3, estudiante.getFechaNacimiento());
            pstmt.setString(4, estudiante.getGenero());
            pstmt.setString(5, estudiante.getDireccion());
            pstmt.setString(6, estudiante.getTelefono());
            pstmt.setString(7, estudiante.getCorreoElectronico());
            pstmt.setString(8, estudiante.getGrado());
            pstmt.setString(9, estudiante.getFechaInscripcion());
            pstmt.setInt(10, estudiante.getId());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Eliminar (Delete)
    public void eliminarEstudiante(int id) {
        String sql = "DELETE FROM estudiantes WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

// Clase principal para probar el CRUD
public class Main {
    public static void main(String[] args) {
        EstudianteDAO estudianteDAO = new EstudianteDAO();

        // Crear un nuevo estudiante
        Estudiante nuevoEstudiante = new Estudiante();
        nuevoEstudiante.setNombre("Juan");
        nuevoEstudiante.setApellido("Pérez");
        nuevoEstudiante.setFechaNacimiento("2000-01-01");
        nuevoEstudiante.setGenero("M");
        nuevoEstudiante.setDireccion("Calle Falsa 123");
        nuevoEstudiante.setTelefono("123456789");
        nuevoEstudiante.setCorreoElectronico("juan.perez@example.com");
        nuevoEstudiante.setGrado("10");
        nuevoEstudiante.setFechaInscripcion("2024-08-03");

        estudianteDAO.agregarEstudiante(nuevoEstudiante);

        // Leer estudiantes
        List<Estudiante> estudiantes = estudianteDAO.obtenerEstudiantes();
        for (Estudiante estudiante : estudiantes) {
            System.out.println(estudiante.getNombre() + " " + estudiante.getApellido());
        }

        // Actualizar un estudiante
        nuevoEstudiante.setNombre("Juanito");
        estudianteDAO.actualizarEstudiante(nuevoEstudiante);

        // Eliminar un estudiante
        estudianteDAO.eliminarEstudiante(nuevoEstudiante.getId());
    }
}
