package com.reservafacil.reservafacil.security;

    import com.reservafacil.reservafacil.repositories.UserRepository;
    import jakarta.servlet.FilterChain;
    import jakarta.servlet.ServletException;
    import jakarta.servlet.http.HttpServletRequest;
    import jakarta.servlet.http.HttpServletResponse;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
    import org.springframework.security.core.context.SecurityContextHolder;
    import org.springframework.security.core.userdetails.UserDetails;
    import org.springframework.stereotype.Component;
    import org.springframework.web.filter.OncePerRequestFilter;

    import java.io.IOException;

    @Component
    public class SecurityFilter extends OncePerRequestFilter {
        @Autowired
        TokenService tokenService;
        @Autowired
        UserRepository userRepository;

        /**
         * Processes incoming HTTP requests to perform JWT-based authentication, setting the security context if a valid token is present.
         *
         * Bypasses authentication for login and registration endpoints. If a valid token is found in the "Authorization" header, validates the token, retrieves the associated user, and sets the authentication in the security context. Continues the filter chain regardless of authentication outcome.
         *
         * @param request the HTTP request
         * @param response the HTTP response
         * @param filterChain the filter chain to continue processing
         * @throws ServletException if an error occurs during filtering
         * @throws IOException if an I/O error occurs during filtering
         */
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
                        var userOptional = userRepository.findByEmail(email);
                        if (userOptional.isPresent()) {
                            var user = userOptional.get();
                            var authentication = new UsernamePasswordAuthenticationToken(
                                    user, null, user.getAuthorities());
                            SecurityContextHolder.getContext().setAuthentication(authentication);
                        } else {
                            System.out.println("User not found for email: " + email);
                        }
                    } else {
                        System.out.println("Invalid token: " + token);
                    }
                } catch (Exception e) {
                    System.out.println("Error validating token: " + e.getMessage());
                    // Prevent filter from rejecting request
                }
            }
            filterChain.doFilter(request, response);
        }

        /**
         * Extracts the JWT token from the "Authorization" header of the request.
         *
         * @param request the HTTP request containing the "Authorization" header
         * @return the token string if present, or null if the header is missing
         */
        private String recoverToken(HttpServletRequest request) {
            var authHeader = request.getHeader("Authorization");
            if (authHeader == null) {
                return null;
            }
            return authHeader.replace("Bearer ", "");
        }
    }