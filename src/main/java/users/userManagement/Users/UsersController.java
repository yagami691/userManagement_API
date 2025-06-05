package users.userManagement.Users;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import users.userManagement.DTO.LoginRequest;

import java.util.List;

@RestController
@RequestMapping("/auth")
@Tag(name = "User Authentication", description = "Endpoints for user registration, login, update and deletion")
public class UsersController {

    private final UsersService usersService;

    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }

    @GetMapping("/allUsers")
    @Operation(summary = "Get all users", description = "Returns a list of all registered users")
    public List<Users> getAllUsers() {
        return usersService.getUsers();
    }

    @PostMapping("/addUsers")
    @Operation(summary = "Add a new user", description = "Registers a new user into the system")
    public ResponseEntity<String> addUser(@RequestBody Users user) {
        usersService.addUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body("User added successfully");
    }

    @PostMapping("/login")
    @Operation(summary = "User login", description = "Authenticates user and returns a JWT token if credentials are valid")
    public ResponseEntity<String> login(@RequestBody LoginRequest login) {
        String token = usersService.login(login.getEmail(), login.getPassword());
        return ResponseEntity.ok(token);
    }

    @PutMapping("/{user_id}")
    @Operation(summary = "Update user", description = "Updates a user's information by their ID")
    public ResponseEntity<String> updateUser(
            @Parameter(description = "ID of the user to update") @PathVariable Long user_id,
            @RequestBody Users user) {
        usersService.UpdateUser(user_id, user.getName(), user.getEmail(), user.getPassword());
        return ResponseEntity.ok("User updated successfully");
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete user", description = "Deletes a user by their ID")
    public ResponseEntity<String> deleteUser(
            @Parameter(description = "ID of the user to delete") @PathVariable Long id) {
        usersService.deleteUser(id);
        return ResponseEntity.ok("User deleted successfully");
    }
}
