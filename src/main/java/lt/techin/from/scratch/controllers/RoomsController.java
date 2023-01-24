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
public class RoomsController {

    private final PersonService personService;

    public RoomsController (UserRepository userRepository) {
        this.personService = new PersonService(userRepository);
    }

    @GetMapping("rooms")
    public String getDefaultRoom(Model model) {
        List<Person> room1 = personService.usersByRoomInt(1);
        model.addAttribute("room1",room1);

        List<Person> room2 = personService.usersByRoomInt(2);
        model.addAttribute("room2", room2);

        List<Person> room3 = personService.usersByRoomInt(3);
        model.addAttribute("room3", room3);

        List<Person> room4 = personService.usersByRoomInt(4);
        model.addAttribute("room4", room4);
        return "rooms";
    }

    @PostMapping("/remove-user-from-room")
    public String removePersonFromRoom(@RequestParam("personId") Long personToRemoveID, RedirectAttributes redirectAttributes) {
        personService.removeUserFromRoom(personToRemoveID);
        return "redirect:rooms";
    }

    @PostMapping("/add-person-to-room1")
    public String addPersonToRoom1(@RequestParam String personId) {
        try {
            Long personID = Long.parseLong(personId);
            personService.addUserToRoom(personID, 1);
        } catch (Exception ignored) {}
        return "redirect:rooms";
    }

    @PostMapping("/add-person-to-room2")
    public String addPersonToRoom2(@RequestParam String personId) {
        try {
            Long personID = Long.parseLong(personId);
            personService.addUserToRoom(personID, 2);
        } catch (Exception ignored) {}
        return "redirect:rooms";
    }

    @PostMapping("/add-person-to-room3")
    public String addPersonToRoom3(@RequestParam String personId) {
        try {
            Long personID = Long.parseLong(personId);
            personService.addUserToRoom(personID, 3);
        } catch (Exception ignored) {}
        return "redirect:rooms";
    }

    @PostMapping("/add-person-to-room4")
    public String addPersonToRoom4(@RequestParam String personId) {
        try {
            Long personID = Long.parseLong(personId);
            personService.addUserToRoom(personID, 4);
        } catch (Exception ignored) {}
        return "redirect:rooms";
    }

    @PostMapping("display-mainHub")
    public String displayRooms(RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("attribute name", "Successful switch!");
        return "redirect:mainHub";
    }
}
