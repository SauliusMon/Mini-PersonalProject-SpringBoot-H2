package lt.techin.from.scratch.controllers;

import lt.techin.from.scratch.dao.UserRepository;
import lt.techin.from.scratch.model.Person;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
public class DefaultRoomController {

    private final UserRepository userRepository;

    public DefaultRoomController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/")
    public String getDefaultRoom(Model model) {
        List<Person> allPersons = userRepository.findAll().stream().sorted(customComparator).collect(Collectors.toList());
        if (!allPersons.isEmpty())
            model.addAttribute("allPersons", allPersons);
        return "mainHub";
    }

    @GetMapping("/mainHub")
    public String getDefaultRoomMainHub(Model model) {
        List<Person> allPersons = userRepository.findAll().stream().sorted(customComparator).collect(Collectors.toList());
        if (!allPersons.isEmpty())
            model.addAttribute("allPersons", allPersons);
        return "mainHub";
    }

    @GetMapping("/get-all-persons")
    public String getAllPersons(Model model) {
        return getDefaultRoom(model);
    }

    @PostMapping("/add-person")
    public String addUser(@ModelAttribute Person person) {
        userRepository.save(person);
        return "mainHub";
    }

    private final Comparator<Person> customComparator = new Comparator<Person>() {
        @Override
        public int compare(Person o1, Person o2) {
            //Swapped places for teachers to be on top
            return o1.getId().compareTo(o2.getId());
        }
    };
    @PostMapping("/get-person-id-by-name")
    public String getPersonByName(@RequestParam String personName, Model model, RedirectAttributes redirectAttributes) {
        List<Person> personsByName = userRepository.findAll().stream().filter(p -> p.getName().equals(personName)).sorted(customComparator).collect(Collectors.toList());
        if (!personsByName.isEmpty()) {
            model.addAttribute("persons", personsByName);
        }
        return "mainHub";
    }

    @PostMapping("/delete-user-by-id")
    public String deleteUserByID(@RequestParam String userID, Model model) {
        try {
            Long personID = Long.parseLong(userID);
            List<Person> personToDelete = userRepository.findAll().stream().filter(p -> Objects.equals(p.getId(), personID)).collect(Collectors.toList());
            model.addAttribute("usersToDelete", personToDelete);
            userRepository.deleteAllInBatch(personToDelete);
        } catch (Exception ignored) {}
       return "mainhub";
    }

    @PostMapping("/display-rooms")
    public String displayRooms(RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("attribute name", "Successful switch!");
        return "redirect:rooms";
    }
}
