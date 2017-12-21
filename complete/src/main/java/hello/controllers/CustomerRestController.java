package hello.controllers;


import hello.model.Role;
import hello.model.User;
import hello.repositories.RoleRepository;
import hello.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerRestController {
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private UserRepository userRepository;

    @RequestMapping("/save")
    public String process() {

        //Roles
        Role admin = new Role("admin", "read, create, delete, update");
        Role guest = new Role("guest", "read");
        //Users
        User userAdmin = new User("Ivan Urgant", "ivan", "ivan", admin);
        User userGuest = new User("Masha Big", "maria", "maria", guest);

        roleRepository.save(admin);
        roleRepository.save(guest);
        userRepository.save(userAdmin);
        userRepository.save(userGuest);

        return "Done";
    }

    @RequestMapping("/hello")
    public Model hello(Model model) {

        model.addAttribute("users", userRepository.findAll());

        return model;

    }



}