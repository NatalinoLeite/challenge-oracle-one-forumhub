package com.alura.forumhub.infra.security;

import com.alura.forumhub.usuario.UsuarioRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        System.out.println("=============================================");
        System.out.println("FILTRO DE SEGURANÇA EM AÇÃO!");
        var tokenJWT = recuperarToken(request);
        System.out.println("Token recuperado do cabeçalho: " + tokenJWT);

        if (tokenJWT != null) {
            System.out.println("Token não é nulo, a validar...");
            var subject = tokenService.getSubject(tokenJWT);
            System.out.println("Subject (login) extraído do token: " + subject);
            var usuario = usuarioRepository.findByLogin(subject);

            if (usuario != null) {
                var authentication = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);
                System.out.println("Utilizador autenticado com sucesso e definido no contexto de segurança!");
            } else {
                System.out.println("Utilizador não encontrado na base de dados para o subject: " + subject);
            }
        } else {
            System.out.println("Nenhum token JWT encontrado no cabeçalho Authorization.");
        }
        System.out.println("=============================================");

        filterChain.doFilter(request, response);
    }

    private String recuperarToken(HttpServletRequest request) {
        var authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader != null) {
            return authorizationHeader.replace("Bearer ", "");
        }
        return null;
    }
}