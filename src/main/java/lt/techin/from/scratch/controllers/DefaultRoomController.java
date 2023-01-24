package lt.techin.from.scratch.controllers;

import lt.techin.from.scratch.dao.UserRepository;
import lt.techin.from.scratch.model.Person;
import lt.techin.from.scratch.services.PersonService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.*;
import java.util.stream.Collectors;

@Controller
public class DefaultRoomController {

    private final UserRepository userRepository;
    private final PersonService personService;

    private final List<Person> usersToDisplay = new ArrayList<>();
    private final List<Person> usersToDelete = new ArrayList<>();

    public DefaultRoomController(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.personService = new PersonService(userRepository);
    }

    @GetMapping("/")
    public String getDefaultRoom(Model model) {
        List<Person> allPersons = personService.getAllUsers();
        if (!allPersons.isEmpty())
            model.addAttribute("allPersons", allPersons);
        if (!usersToDisplay.isEmpty())
            model.addAttribute("persons", usersToDisplay);
        if (!usersToDelete.isEmpty())
            model.addAttribute("usersToDelete", usersToDelete);
        return "mainHub";
    }

    @GetMapping("/mainHub")
    public String getDefaultRoomMainHub(Model model) {
        return getDefaultRoom(model);
    }

    @GetMapping("/get-all-persons")
    public String getAllPersons(Model model) {
        return getDefaultRoom(model);
    }

    @PostMapping("/add-person")
    public String addUser(@ModelAttribute Person person, Model model) {
        personService.addPerson(person);
        return getDefaultRoom(model);
    }

    @PostMapping("/get-person-id-by-name")
    public String getPersonByName(@RequestParam String personName, Model model, RedirectAttributes redirectAttributes) {
        List<Person> usersByName = personService.getUsersByName(personName);
        if (!usersByName.isEmpty()) {
            usersToDisplay.clear();
            usersToDisplay.addAll(usersByName);
        }
        return getDefaultRoom(model);
    }

    @PostMapping("/delete-user-by-id")
    public String deleteUserByID(@RequestParam String userID, Model model) {
        try {
            Long personID = Long.parseLong(userID);
            List<Person> personsToDelete = personService.getDeletedUsersAndDelete(personID);
            if (!personsToDelete.isEmpty()) {
                usersToDelete.clear();
                usersToDelete.addAll(personsToDelete);
            }
        } catch (Exception ignored) {}
       return getDefaultRoom(model);
    }

    @PostMapping("/display-rooms")
    public String displayRooms(RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("attribute name", "Successful switch!");
        return "redirect:rooms";
    }
}
