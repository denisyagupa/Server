package hello.repository;

import hello.entities.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface UserRepository extends CrudRepository<User,Integer> {

    User findByUsername(String username);
    ArrayList<User> findAll();
}
