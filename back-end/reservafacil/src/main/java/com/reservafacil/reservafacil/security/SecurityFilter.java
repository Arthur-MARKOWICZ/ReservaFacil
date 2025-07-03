    package com.reservafacil.reservafacil.security;

    import com.reservafacil.reservafacil.repositories.UsuarioRepository;
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
        TokenService tokenService;
        @Autowired
        UsuarioRepository usuarioRepository;

        @Override
        protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                        FilterChain filterChain) throws ServletException, IOException {
            String path = request.getRequestURI();
            System.out.println("SecurityFilter: Path=" + path + ", Authorization=" + request.getHeader("Authorization"));
            if (path.equals("/auth/login") || path.equals("/auth/cadastro")) {
                filterChain.doFilter(request, response);
                return;
            }

            var token = this.recoverToken(request);
            if (token != null) {
                try {
                    var email = tokenService.validateToken(token);
                    if (email != null) {
                        var userOptional = usuarioRepository.findByEmail(email);
                        if (userOptional.isPresent()) {
                            var user = userOptional.get();
                            var authentication = new UsernamePasswordAuthenticationToken(
                                    user, null, user.getAuthorities());
                            SecurityContextHolder.getContext().setAuthentication(authentication);
                        } else {
                            System.out.println("Usuario not found for email: " + email);
                        }
                    } else {
                        System.out.println("Invalid token: " + token);
                    }
                } catch (Exception e) {
                    System.out.println("Error validating token: " + e.getMessage());

                }
            }
            filterChain.doFilter(request, response);
        }

        private String recoverToken(HttpServletRequest request) {
            var authHeader = request.getHeader("Authorization");
            if (authHeader == null) {
                return null;
            }
            return authHeader.replace("Bearer ", "");
        }
    }