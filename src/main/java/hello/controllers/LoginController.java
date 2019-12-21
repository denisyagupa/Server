package hello.controllers;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import hello.entities.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import hello.repository.UserRepository;


@RestController
public class LoginController {

    //@Autowired
    private UserRepository userRepository;

    @Autowired
    public LoginController(UserRepository userRepository){
        this.userRepository = userRepository;
    }


    @PostMapping("/login")
    public String login(@RequestBody String loginJson){
        System.out.println("Login info: " + loginJson);
        return loginCheck(loginJson);
    }

    private String loginCheck(String loginJson){

        Gson gson = new Gson();
        User userActual = gson.fromJson(loginJson,User.class);


        User userInDB = userRepository.findByUsername(userActual.getUsername());//getting user in database with this login

        String message;
        JsonObject jsonObject = new JsonObject();
        if(userInDB!=null&&userActual.getPassword().equals(userInDB.getPassword())){ //checking user from db
            jsonObject.addProperty("message","login successful");
            message = gson.toJson(jsonObject);
            System.out.println("return to client={}" + message);
            return message;
        }
        if(userInDB==null) {
            jsonObject.addProperty("message", "user does not exist, create account first");
        }else{
            jsonObject.addProperty("message", "wrong login/password");
        }
        message = jsonObject.toString();
        System.out.println("return to client={} " + message);
        return message;
    }
}
