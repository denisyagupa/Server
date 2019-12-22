package hello.controllers;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import hello.entities.User;
import hello.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ClockController {

    private UserRepository userRepository;

    @Autowired
    public ClockController(UserRepository userRepository){
        this.userRepository = userRepository;
    }
    @PostMapping("/setclock")
    public void sClock(@RequestBody String clockJson){
        System.out.println("Login info: " + clockJson);
        setTimeClock(clockJson);
    }

    private void setTimeClock(String clockJSon)
    {
        Gson gson = new Gson();
        User userActual = gson.fromJson(clockJSon,User.class);


        User userInDB = userRepository.findByUsername(userActual.getUsername());//getting user in database with this login
        userInDB.setClock(userActual.getClock());
        userRepository.save(userInDB);
    }

    @PostMapping("/getclock")
    public String gClock(@RequestBody String clockJson){
        System.out.println("Login info: " + clockJson);
        return getTimeClock(clockJson);
    }

    private String getTimeClock(String clockJSon)
    {
        Gson gson = new Gson();
        User userActual = gson.fromJson(clockJSon,User.class);


        User userInDB = userRepository.findByUsername(userActual.getUsername());//getting user in database with this login
        String message = userInDB.getClock();

        return gson.toJson(message);
    }

    @PostMapping("/removeclock")
    public void rClock(@RequestBody String removeClockJson){
        removeTimeClock(removeClockJson);
    }

    private void removeTimeClock(String removeClockJSon)
    {
        Gson gson = new Gson();
        User userActual = gson.fromJson(removeClockJSon,User.class);
        User userInDB = userRepository.findByUsername(userActual.getUsername());
        userInDB.setClock("NULL");
        userRepository.save(userInDB);
    }

    @PostMapping("/getallusers")
    public String gAllUsers(@RequestBody String allusersJson){
        return getAllUsers(allusersJson);
    }

    private String getAllUsers(String allusersJson)
    {
        Gson gson = new Gson();
        User userActual = gson.fromJson(allusersJson,User.class);

        ArrayList<User> alluserslist = new ArrayList<User>();
        alluserslist = userRepository.findAll();
        for (int i = 0; i < alluserslist.size(); i++)
        {
            if (userActual.getUsername().toString().equals(alluserslist.get(i).getUsername().toString()))
            {
                alluserslist.remove(i);
                break;
            }
        }
        return gson.toJson(alluserslist);
    }

}
