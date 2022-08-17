package com.travelgrouperu.service.controller;

import com.travelgrouperu.dto.Mensaje;
import com.travelgrouperu.dto.UsrDto;
import com.travelgrouperu.security.dto.JwtDto;
import com.travelgrouperu.security.dto.LoginUsuario;
import com.travelgrouperu.security.dto.NuevoUsuario;
import com.travelgrouperu.security.entity.Rol;
import com.travelgrouperu.security.entity.Usuario;
import com.travelgrouperu.security.enums.RolNombre;
import com.travelgrouperu.security.jwt.JwtProvider;
import com.travelgrouperu.security.service.RolService;
import com.travelgrouperu.security.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/auth")
@CrossOrigin
public class AuthController {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UsuarioService usuarioService;

    @Autowired
    RolService rolService;

    @Autowired
    JwtProvider jwtProvider;

    //Espera un json y lo convierte a tipo clase NuevoUsuario
    @PostMapping("/nuevoUsuario")
    public ResponseEntity<Object> nuevoUsuario(@Valid @RequestBody NuevoUsuario nuevoUsuario,
                                          BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return new ResponseEntity<>(new Mensaje("Campos mal o email invalido"), HttpStatus.BAD_REQUEST);
        }
        if(usuarioService.existsByUsuario(nuevoUsuario.getNombreUsuario())){
            return new ResponseEntity<>(new Mensaje("Ese nombre ya existe"), HttpStatus.BAD_REQUEST);
        }
        if(usuarioService.existsByEmail(nuevoUsuario.getEmail())){
            return new ResponseEntity<>(new Mensaje("Ese email ya existe"), HttpStatus.BAD_REQUEST);
        }

        Usuario usuario = new Usuario(nuevoUsuario.getNombre(), nuevoUsuario.getApellido(), nuevoUsuario.getEmail(), nuevoUsuario.getCelular(),
                nuevoUsuario.getNombreUsuario(), passwordEncoder.encode(nuevoUsuario.getPassword()));

        Set<Rol> roles = new HashSet<>();
        roles.add(rolService.getByRolNombre(RolNombre.ROLE_USER).get());
        if(nuevoUsuario.getRoles().contains("admin"))
            roles.add(rolService.getByRolNombre(RolNombre.ROLE_ADMIN).get());
        usuario.setRoles(roles);

        usuarioService.save(usuario);

        return new ResponseEntity<>(new Mensaje("Usuario creado"), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<JwtDto> login(@Valid @RequestBody LoginUsuario loginUsuario, BindingResult bindingResult){
        if (bindingResult.hasErrors())
            return new ResponseEntity(new Mensaje("Campos mal"), HttpStatus.BAD_REQUEST);
        Authentication authentication =
                authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(loginUsuario.getNombreUsuario(),
                                loginUsuario.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtProvider.generateToken(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        Usuario usr = usuarioService.getByUsuario(userDetails.getUsername()).get();

       // JwtDto jwtDto = new JwtDto(jwt, userDetails.getUsername(), ,true, userDetails.getAuthorities());
        JwtDto jwtDto = new JwtDto();
       // jwtDto.setToken(jwt);
       // jwtDto.setBearer("Bearer");
        jwtDto.setNombreUsuario(usr.getNombreUsuario());
        jwtDto.setIdPersona(usr.getIdUsuario());
        jwtDto.setNombres(usr.getNombre());
        jwtDto.setApellidos(usr.getApellido());
        jwtDto.setEmail(usr.getEmail());
        jwtDto.setCelular(usr.getCelular());
        jwtDto.setUsuario(usr.getNombreUsuario());
        jwtDto.setMensaje("Usuario autenticado.");
        jwtDto.setRpta(true);
       // jwtDto.setAuthorities(userDetails.getAuthorities());

        return new ResponseEntity<>(jwtDto, HttpStatus.OK);
    }


    @PostMapping("/buscarUsuario")
    public ResponseEntity<Object> buscarUsuario(@RequestBody UsrDto usr){

        Usuario usuario= usuarioService.getByUsuario(usr.getUsr()).get();


        return new ResponseEntity<>(usuario, HttpStatus.OK);
    }

}
