package hello.bootstrap;


import hello.model.Role;
import hello.model.User;
import hello.repositories.RoleRepository;
import hello.repositories.UserRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * Created by jt on 5/16/17.
 */
@Component
public class DevBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private RoleRepository roleRepository;
    private UserRepository userRepository;

    public DevBootstrap(RoleRepository roleRepository, UserRepository userRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        initData();
    }

    private void initData(){

        //Roles
        Role admin = new Role("admin","read, create, delete, update");
        Role guest = new Role("guest", "read");
        //Users
        User userAdmin = new User("Ivan Urgant","ivan","ivan",admin);
        User userGuest = new User("Masha Big","maria","maria",guest);

        roleRepository.save(admin);
        roleRepository.save(guest);
        userRepository.save(userAdmin);
        userRepository.save(userGuest);


    }
}
