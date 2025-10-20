package br.com.fiap.users;

import java.util.List;

public interface IUserService {
    User create(User user);
    User findById(Long id);
    List<User> findAll();
    void delete(Long id);
}
