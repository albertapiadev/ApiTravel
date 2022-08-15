package com.travelgrouperu.security.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Clase Encargada de generar la seguridad
 * Clase que implementa los privilegios de cada usuario
 * UserDetails es una clase propia de Spring Security
 */
public class UsuarioMain implements UserDetails {

    private String nombre;

    private String apellido;


    private String email;

    private String celular;
    private String usuario;

    private String password;
    // Variable que nos da la autorización (no confundir con autenticación)
    // Coleccion de tipo generico que extendiende
    // de GranthedAuthority de Spring security
    private Collection<? extends GrantedAuthority> authorities;

    //Constructor
public UsuarioMain(String nombre, String apellido, String email, String celular, String usuario, String password, Collection<? extends GrantedAuthority> authorities) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.celular = celular;
        this.usuario = usuario;
        this.password = password;
        this.authorities = authorities;
    }

    //Metodo que asigna los privilegios (autorización)
    public static UsuarioMain build(Usuario usuario){
        //Convertimos la clase Rol a la clase GrantedAuthority
        List<GrantedAuthority> authorities =
                usuario.getRoles()
                        .stream()
                        .map(rol -> new SimpleGrantedAuthority(rol.getRolNombre().name()))
                .collect(Collectors.toList());
        return new UsuarioMain(usuario.getNombre(), usuario.getApellido(), usuario.getEmail(), usuario.getCelular(), usuario.getNombreUsuario(), usuario.getPassword(), authorities);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return usuario;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {return apellido;
    }

    public String getEmail() {
        return email;
    }

    public String getCelular() {return celular;
    }

}
