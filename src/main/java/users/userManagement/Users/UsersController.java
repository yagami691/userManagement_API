package users.userManagement.Users;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import users.userManagement.DTO.JwtResponse;
import users.userManagement.DTO.LoginRequest;

import java.util.List;

@RestController
@RequestMapping("/auth")
public class UsersController {

    private final UsersService usersService;

    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }

    @GetMapping(path ="/allUsers")
    public List<Users> getAllUsers() {
        return usersService.getUsers();
    }

    @PostMapping(path = "/addUsers")
    public ResponseEntity<String> addUser(@RequestBody Users user) {
           usersService.addUser(user);
           return ResponseEntity.status(HttpStatus.CREATED).body("User added successfully");
    }


    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest login) {
        String token = usersService.login(login.getEmail(), login.getPassword());
        return ResponseEntity.ok(token);
    }



    @PutMapping(path ="/{user_id}")
    public ResponseEntity<String> updateUser(@PathVariable Long user_id,
                                             @RequestBody Users user) {
        usersService.UpdateUser(user_id, user.getName(), user.getEmail(), user.getPassword());
        return ResponseEntity.ok("User updated successfully");
    }

    @DeleteMapping(path ="/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        usersService.deleteUser(id);
        return ResponseEntity.ok("User deleted successfully");
    }
}

