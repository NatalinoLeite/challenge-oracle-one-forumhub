package com.alura.forumhub;

import com.alura.forumhub.usuario.Usuario;
import com.alura.forumhub.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DatabaseSeeder implements CommandLineRunner {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        // Verifica se o utilizador admin já existe
        if (usuarioRepository.findByLogin("admin@forum.hub") == null) {
            // Cria o utilizador admin com senha encriptada
            Usuario admin = new Usuario(
                    null,
                    "admin@forum.hub",
                    passwordEncoder.encode("123456") // NUNCA guarde senhas em texto plano
            );
            usuarioRepository.save(admin);
            System.out.println("Utilizador de teste 'admin@forum.hub' criado com sucesso!");
        }
    }
}