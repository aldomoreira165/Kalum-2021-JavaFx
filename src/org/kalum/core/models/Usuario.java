package org.kalum.core.models;

import javax.persistence.*;
import java.io.Serializable;

// select * from usuarios_app;
//select * from Usuario, from Usuario

@Entity
@Table(name = "usuario_app")
@NamedQueries({@NamedQuery(name = "Usuario.finAll", query = "from Usuario")})
public class Usuario implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "username")
    private String username;
    @Column(name = "apellidos")
    private String apellidos;
    @Column(name = "nombres")
    private String nombres;
    @Column(name = "email")
    private String email;
    @Column(name = "password")
    private String password;
    @Column(name = "enabled")
    private boolean activo;
    @Column(name = "direccion")
    private String direccion;
    @Column(name = "telefono")
    private String telefono;

    public Usuario() {
    }

    public Usuario(Long id, String username, String apellidos, String nombres, String email, String password, boolean activo, String direccion, String telefono) {
        this.id = id;
        this.username = username;
        this.apellidos = apellidos;
        this.nombres = nombres;
        this.email = email;
        this.password = password;
        this.activo = activo;
        this.direccion = direccion;
        this.telefono = telefono;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
}
