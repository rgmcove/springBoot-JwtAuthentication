package co.com.ragomos.auth.controller;

import co.com.ragomos.auth.entities.User;
import co.com.ragomos.auth.service.IUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class DemoController {

    private final IUserService userService;

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Solicitud exitosa",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "401", description = "Debe iniciar sesion para obtener la informacion",
                    content = @Content),
            @ApiResponse(responseCode = "403", description = "Sin privilegios suficientes",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Error al solicitar informacion",
                    content = @Content)})
    @Operation(security = {@SecurityRequirement(name = "bearer-key")})
    @PostMapping("demo")
    @PreAuthorize("hasRole('ROLE_USER')")
    public String welcome() {
        return "Welcome from secure endpoint";
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Solicitud exitosa",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = User.class))}),
            @ApiResponse(responseCode = "401", description = "Debe iniciar sesion para obtener la informacion",
                    content = @Content),
            @ApiResponse(responseCode = "403", description = "Sin privilegios suficientes",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Error al solicitar informacion",
                    content = @Content)})
    @Operation(security = {@SecurityRequirement(name = "bearer-key")})
    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("users")
    public ResponseEntity<List<User>> getUsers() {
        return ResponseEntity.ok().body(userService.getAllUsers());
    }
}
