package lt.techin.from.scratch.controllers;

import lt.techin.from.scratch.dao.UserRepository;
import lt.techin.from.scratch.model.Person;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class DefaultRoomController {

    private final UserRepository userRepository;

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

    @PostMapping("/add-person")
    public String addUser(@ModelAttribute Person person) {
        userRepository.save(person);
        return "mainHub";
    }

    @PostMapping("/display-rooms")
    public String displayRooms(RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("attribute name", "Successful switch!");
        return "redirect:rooms";
    }
}
