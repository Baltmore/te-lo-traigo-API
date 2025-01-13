package com.balmore.apirest.apirest.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import com.balmore.apirest.apirest.Entities.UsuarioAdmin;
import com.balmore.apirest.apirest.Repositories.UsuarioAdminRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UsuarioAdminRepository usuarioAdminRepository;

    public UserDetailsServiceImpl(UsuarioAdminRepository usuarioAdminRepository) {
        this.usuarioAdminRepository = usuarioAdminRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UsuarioAdmin usuario = usuarioAdminRepository.findByNombreUsuario(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + username));

        return User.builder()
                .username(usuario.getNombreUsuario())
                .password(usuario.getContrasena()) // Contraseña encriptada
                .roles(usuario.getRol())
                .build();
    }
}
