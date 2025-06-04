package users.userManagement.Users;




import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import users.userManagement.security.JwtUtil;

import java.util.List;
import java.util.Optional;

@Service
public class  UsersService{

    private final AuthenticationManager authManager;
    private final JwtUtil jwtUtil;
    private final UsersRepository usersRepository;
    private final PasswordEncoder passwordEncoder;

    public UsersService(AuthenticationManager authManager, JwtUtil jwtUtil,
                      UsersRepository usersRepository, PasswordEncoder passwordEncoder) {
        this.authManager = authManager;
        this.jwtUtil = jwtUtil;
        this.usersRepository = usersRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<Users> getUsers(){
        return usersRepository.findAll();
    }

//    public void addUser(Users user){
//        List<Users> addUser = usersRepository.findUserById(user.getId());
//        boolean userExists = addUser.stream().anyMatch(user1 -> user1.getId().equals(user.getId())
//                && user1.getName().equals(user.getName())
//                && user1.getEmail().equals(user.getEmail()) && user1.getPassword().equals(user.getPassword()));
//
//        if(!userExists) {
//           String encodedPassword =  passwordEncoder.encode(user.getPassword());
//           user.setPassword(encodedPassword);
//           usersRepository.save(user);
//        }
//        else throw new ResponseStatusException(HttpStatus.CONFLICT, "User already exists");
//    }


    public void addUser(Users user) {
        Optional<Users> existingUser = usersRepository.findByEmail(user.getEmail());

        if (existingUser.isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Email already exists");
        }

        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        usersRepository.save(user);
    }


    public void UpdateUser(Long id, String name, String email, String password){
        Users userFromDB = usersRepository.findById(id)
                .orElseThrow( () ->   new IllegalStateException("user with " + id + " not found")
        );

        String encodedPassword =  passwordEncoder.encode(password);


        if(!name.equals(userFromDB.getName())) userFromDB.setName(name);
        if(!email.equals(userFromDB.getEmail())) userFromDB.setEmail(email);
        if(!encodedPassword.equals(userFromDB.getPassword())) userFromDB.setPassword(encodedPassword);

        usersRepository.save(userFromDB);
    }

    public void deleteUser(Long id){
        boolean userExists = usersRepository.existsById(id);
        if(userExists) usersRepository.deleteById(id);
        else throw new ResponseStatusException(HttpStatus.NOT_FOUND, "user with " + id + " not found");

    }

    public String login(String email, String password) {
        try {
            authManager.authenticate(
                    new UsernamePasswordAuthenticationToken(email, password)
            );

            System.out.println("authManager class: " + authManager.getClass());
            return jwtUtil.generateToken(email);
        } catch (BadCredentialsException e) {
            System.out.println("invalid credentials !");
            throw new RuntimeException("Login failed: Bad credentials");
        } catch (UsernameNotFoundException e) {
            System.out.println("Email not found !");
            throw new RuntimeException("Login failed: User not found");
        } catch (Exception e) {
            System.out.println("Another error: " + e.getMessage());
            throw new RuntimeException("Login failed: " + e.getMessage());
        }

    }



}

