package org.example.empleadosapp.controller;

import org.example.empleadosapp.model.Empleado;
import org.example.empleadosapp.model.Departamento;
import org.example.empleadosapp.repository.EmpleadoRepository;
import org.example.empleadosapp.repository.DepartamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/api")
public class TestController {

    @Autowired
    private EmpleadoRepository empleadoRepository;

    @Autowired
    private DepartamentoRepository departamentoRepository;

    @GetMapping("/status")
    public String testConexion() {
        try {
            // Intentar obtener el primer empleado
            Empleado primerEmpleado = empleadoRepository.findAll().stream().findFirst().orElse(null);

            // Intentar obtener el primer departamento
            Departamento primerDepartamento = departamentoRepository.findAll().stream().findFirst().orElse(null);

            // Contar total de registros
            long totalEmpleados = empleadoRepository.count();
            long totalDepartamentos = departamentoRepository.count();

            StringBuilder respuesta = new StringBuilder();
            respuesta.append("✅ Conexión exitosa a la base de datos\n");
            respuesta.append("Total de empleados: ").append(totalEmpleados).append("\n");
            respuesta.append("Total de departamentos: ").append(totalDepartamentos).append("\n");

            if (primerEmpleado != null) {
                respuesta.append("\nPrimer empleado encontrado:\n");
                respuesta.append("Número: ").append(primerEmpleado.getEmpNo()).append("\n");
                respuesta.append("Nombre: ").append(primerEmpleado.getFirstName()).append("\n");
                respuesta.append("Apellido: ").append(primerEmpleado.getLastName()).append("\n");
            }

            if (primerDepartamento != null) {
                respuesta.append("\nPrimer departamento encontrado:\n");
                respuesta.append("Código: ").append(primerDepartamento.getDeptNo()).append("\n");
                respuesta.append("Nombre: ").append(primerDepartamento.getDeptName()).append("\n");
            }

            return respuesta.toString();
        } catch (Exception e) {
            return "❌ Error al conectar con la base de datos: " + e.getMessage();
        }
    }
}