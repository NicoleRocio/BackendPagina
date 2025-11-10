package com.plataforma.plataforma.config;

import com.plataforma.plataforma.model.Empleado;
import com.plataforma.plataforma.model.Rol;
import com.plataforma.plataforma.model.Usuario;
import com.plataforma.plataforma.model.Producto;
import com.plataforma.plataforma.model.Asistencia;
import com.plataforma.plataforma.repository.EmpleadoRepository;
import com.plataforma.plataforma.repository.RolRepository;
import com.plataforma.plataforma.repository.UsuarioRepository;
import com.plataforma.plataforma.repository.ProductoRepository;
import com.plataforma.plataforma.repository.AsistenciaRepository;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

@Component
public class DataLoader {

    private final ProductoRepository productoRepository;
    private final RolRepository rolRepository;
    private final EmpleadoRepository empleadoRepository;
    private final UsuarioRepository usuarioRepository;
    private final AsistenciaRepository asistenciaRepository;

    public DataLoader(ProductoRepository productoRepository,
            RolRepository rolRepository,
            EmpleadoRepository empleadoRepository,
            UsuarioRepository usuarioRepository,
            AsistenciaRepository asistenciaRepository) {
        this.productoRepository = productoRepository;
        this.rolRepository = rolRepository;
        this.empleadoRepository = empleadoRepository;
        this.usuarioRepository = usuarioRepository;
        this.asistenciaRepository = asistenciaRepository;
    }

    @PostConstruct
    public void init() {

        // ----- Productos -----
        if (productoRepository.count() == 0) {

            productoRepository.save(new Producto(
                    "Laptop Lenovo ThinkPad",
                    "Core i5, 8GB RAM, SSD 256GB",
                    "COLEGIO_ZARATE",
                    4,
                    "computadora.png"));

            productoRepository.save(new Producto(
                    "Monitor Samsung 24''",
                    "Full HD, HDMI/VGA",
                    "COLEGIO_ZARATE",
                    10,
                    "computadora.png"));

            productoRepository.save(new Producto(
                    "Proyector Epson X05",
                    "Resolución XGA, 3300 lúmenes",
                    "COLEGIO_ZARATE",
                    0,
                    "computadora.png"));

            productoRepository.save(new Producto(
                    "Impresora HP LaserJet",
                    "Tóner negro, conexión Wi-Fi",
                    "ACADEMIA_ZARATE",
                    5,
                    "computadora.png"));
        }

        // ----- Roles -----
        Rol docenteRol = rolRepository.findByNombre("DOCENTE").orElseGet(() -> {
            Rol r = new Rol();
            r.setNombre("DOCENTE");
            return rolRepository.save(r);
        });

        Rol adminRol = rolRepository.findByNombre("ADMINISTRATIVO").orElseGet(() -> {
            Rol r = new Rol();
            r.setNombre("ADMINISTRATIVO");
            return rolRepository.save(r);
        });

        // ----- Empleados -----
        if (empleadoRepository.count() == 0) {
            Empleado emp1 = new Empleado();
            emp1.setNombre("Juan Pérez");
            Set<Rol> roles1 = new HashSet<>();
            roles1.add(docenteRol);
            emp1.setRoles(roles1);
            empleadoRepository.save(emp1);

            Empleado emp2 = new Empleado();
            emp2.setNombre("María López");
            Set<Rol> roles2 = new HashSet<>();
            roles2.add(adminRol);
            emp2.setRoles(roles2);
            empleadoRepository.save(emp2);
        }

        // ----- Usuarios -----
        if (usuarioRepository.count() == 0) {
            Empleado emp1 = empleadoRepository.findAll().get(0);
            Empleado emp2 = empleadoRepository.findAll().get(1);

            Usuario user1 = new Usuario();
            user1.setUsername("juanp");
            user1.setPassword("123456"); // ⚠ En producción, usar BCrypt
            user1.setNombre(emp1.getNombre());
            user1.setEmpleado(emp1);
            usuarioRepository.save(user1);

            Usuario user2 = new Usuario();
            user2.setUsername("marial");
            user2.setPassword("123456");
            user2.setNombre(emp2.getNombre());
            user2.setEmpleado(emp2);
            usuarioRepository.save(user2);
        }

        // ----- Asistencias -----
        if (asistenciaRepository.count() == 0) {
            Empleado emp1 = empleadoRepository.findAll().get(0);
            Empleado emp2 = empleadoRepository.findAll().get(1);

            Asistencia asis1 = new Asistencia();
            asis1.setEmpleado(emp1);
            asis1.setFecha(LocalDate.of(2025, 10, 28));
            asis1.setHoraIngreso(LocalTime.of(8, 0));
            asis1.setHoraSalida(LocalTime.of(17, 0));
            asis1.setEstado("Presente");
            asistenciaRepository.save(asis1);

            Asistencia asis2 = new Asistencia();
            asis2.setEmpleado(emp2);
            asis2.setFecha(LocalDate.of(2025, 10, 28));
            asis2.setHoraIngreso(LocalTime.of(9, 0));
            asis2.setHoraSalida(LocalTime.of(17, 30));
            asis2.setEstado("Tardanza");
            asistenciaRepository.save(asis2);
        }
    }
}
