package pl.coderslab.service;

import pl.coderslab.model.User;

public interface UserService {
    public User findByUserName(String name);
    public User findByEmail(String name);

    public void saveUser(User user);
}
