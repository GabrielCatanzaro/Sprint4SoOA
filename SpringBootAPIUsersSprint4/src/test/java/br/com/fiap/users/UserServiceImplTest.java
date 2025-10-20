package br.com.fiap.users;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

public class UserServiceImplTest {

    @Test
    public void create_should_encode_password_and_save() {
        UserRepository repo = Mockito.mock(UserRepository.class);
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        UserServiceImpl service = new UserServiceImpl(repo, encoder);

        User u = User.builder().name("Test").email("a@b.com").password("secret").build();
        Mockito.when(repo.save(any(User.class))).thenAnswer(i -> i.getArgument(0));
        User saved = service.create(u);

        assertNotNull(saved.getPassword());
        assertNotEquals("secret", saved.getPassword());
    }
}
