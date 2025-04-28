package seguranca.springboot_bootcamp;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncoderUtil {
    public static void main(String[] args) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String rawPassword = "1234";
        String encodedPassword = passwordEncoder.encode(rawPassword);
        System.out.println("Contraseña codificada: " + encodedPassword);
        // Utiliza este 'encodedPassword' en tu comando INSERT SQL
    }
}
