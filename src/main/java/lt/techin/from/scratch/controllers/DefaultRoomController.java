package lt.techin.from.scratch.controllers;

import lt.techin.from.scratch.dao.UserRepository;
import lt.techin.from.scratch.model.Person;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class DefaultRoomController {

    private UserRepository userRepository;

    public DefaultRoomController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/")
    public String getDefaultRoom() {
        return "mainHub";
    }

    @GetMapping("/mainHub")
    public String getDefaultRoom(Model model) {
        //model.addAttribute("logged_user", new AbstractUser("Coolguy", "password"));
        System.out.println(userRepository.findAll().size());
        return "mainHub";
    }

    @PostMapping("adduser")
    public String getLogout(@ModelAttribute Person person) {
        System.out.println("asd" + person.getName() + person.getPassword());
        userRepository.save(person);
        return "mainHub";
    }
}
