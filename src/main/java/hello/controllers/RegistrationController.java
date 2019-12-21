package hello.controllers;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import hello.entities.User;
import hello.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class RegistrationController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/registry")
    public String registry(@RequestBody String registrationJson) {
        System.out.println("registration request, info={}" + registrationJson);
        return checkRegister(registrationJson);
    }
    private String checkRegister(String registrationJson){
        Gson gson = new Gson();
        User user = gson.fromJson(registrationJson,User.class);
        User userInDb = userRepository.findByUsername(user.getUsername());
        JsonObject jsonObject = new JsonObject();
        String message;
        if(userInDb == null) {
            System.out.println("add user to database");
            userRepository.save(user);
            message = "User registered";
        }else{
            message = "User already exist";
        }
        jsonObject.addProperty("message",message);
        System.out.println("return to client={}"+ jsonObject.toString());
        return jsonObject.toString();
    }
}
