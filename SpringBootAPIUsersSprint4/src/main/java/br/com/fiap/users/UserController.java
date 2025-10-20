package br.com.fiap.users;

import br.com.fiap.security.JwtTokenProvider;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final IUserService service;
    private final JwtTokenProvider tokenProvider;
    private final UserRepository repository;

    public UserController(IUserService service, JwtTokenProvider tokenProvider, UserRepository repository) {
        this.service = service;
        this.tokenProvider = tokenProvider;
        this.repository = repository;
    }

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody User user) {
        User created = service.create(user);
        return ResponseEntity.ok(created);
    }

    @PostMapping("/auth")
    public ResponseEntity<?> authenticate(@RequestBody AuthRequest req) {
        // simple auth by matching email and raw password
        var opt = repository.findByEmail(req.getEmail());
        if (opt.isEmpty()) return ResponseEntity.status(401).body("Invalid credentials");
        User u = opt.get();
        boolean matches = new org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder().matches(req.getPassword(), u.getPassword());
        if (!matches) return ResponseEntity.status(401).body("Invalid credentials");

        String token = tokenProvider.generateToken(u.getEmail());
        return ResponseEntity.ok(new AuthResponse(token));
    }

    @GetMapping
    public ResponseEntity<List<User>> list() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> get(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}

class AuthRequest {
    private String email;
    private String password;
    public String getEmail(){return email;}
    public String getPassword(){return password;}
    public void setEmail(String e){this.email=e;}
    public void setPassword(String p){this.password=p;}
}

class AuthResponse {
    private String token;
    public AuthResponse(String token){this.token=token;}
    public String getToken(){return token;}
}
