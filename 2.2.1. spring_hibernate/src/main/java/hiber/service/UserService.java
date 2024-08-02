package hiber.service;

import hiber.model.User;
import org.springframework.stereotype.Service;

import java.util.List;
public interface UserService {
    void add(User user);
    List<User> listUsers();
    void cleanUsersTable();
    List<User> getUserWhithCar(String model, int series);
}
