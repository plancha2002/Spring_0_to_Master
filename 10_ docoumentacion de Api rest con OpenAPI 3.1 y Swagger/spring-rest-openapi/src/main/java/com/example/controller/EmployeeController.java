package com.example.controller;

import com.example.model.Employee;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
@Tag(name = "Employee",
    description = "Api rest de operaciones crud para Employees"
)
public class EmployeeController {
    @ApiResponses( value = {
        @ApiResponse(responseCode = "200",
                    description = "Retrieved employees",
                    content = {
                        @Content(
                        mediaType = "application/json",
                        array = @ArraySchema(
                                schema = @Schema(implementation = Employee.class)
                        )
        )
        })
    }
    )
    @Operation(
            summary = "findAll employee",
            description = "devuelve el listado de todos los empleados"
    )
    @GetMapping("/employee")
    public ResponseEntity<List<Employee>> findAll(){
        return null;
    }
}
