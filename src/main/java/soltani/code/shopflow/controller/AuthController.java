package soltani.code.shopflow.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import soltani.code.shopflow.security.JwtUtil;
import soltani.code.shopflow.entity.User;
import soltani.code.shopflow.repository.UserRepository;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public AuthController(UserRepository userRepository,
                          BCryptPasswordEncoder passwordEncoder,
                          JwtUtil jwtUtil)
    {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/register")
    public User register(@RequestBody User user)
    {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);

    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody User user)
    {
        Optional<User> selectedUser = userRepository.findByUsername(user.getUsername());
        if (selectedUser.isEmpty())
        {
            return ResponseEntity.status(401).body(Map.of("error", "the username doesn't exist"));

        }
        boolean matchedPassword = passwordEncoder.matches(user.getPassword(),
                selectedUser.get().getPassword());

        if (!matchedPassword)
        {
            return ResponseEntity.status(401).body(Map.of("error", "wrong password"));

        }

        String token = jwtUtil.generateToken(
                 selectedUser.get().getUsername()
                ,selectedUser.get().getRole());
        return ResponseEntity.ok(Map.of("token", token));


    }


}
