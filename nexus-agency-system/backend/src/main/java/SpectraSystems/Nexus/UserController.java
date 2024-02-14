package SpectraSystems.Nexus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/nexus/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // Endpoint to retrieve all users
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    // Endpoint to retrieve a user by ID
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") Long id) {
        return userService.getUserById(id)
                .map(user -> new ResponseEntity<>(user, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Endpoint to create a new user
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User createdUser = userService.createUser(user);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    // Endpoint to update an existing user
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable("id") Long id, @RequestBody User userDetails) {
        User updatedUser = userService.updateUser(id, userDetails);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    // Endpoint to delete a user by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // USER - LOGIN
    // @PostMapping("/login")
    // public Object signIn(@RequestBody User user) {
    //     Connection conn = new OracleConnector().getConnection();

    //     try {
    //         PreparedStatement query = conn
    //                 .prepareStatement(String.format("SELECT * FROM users WHERE email = '%s'", user.email));
    //         ResultSet result = query.executeQuery();

    //         if (!result.next()) {
    //             return new WebError("User not found");
    //         }

    //         if (BCrypt.checkpw(user.password, result.getString("password"))) {
    //             return new LoggedUser(
    //                     result.getString("user_id"),
    //                     result.getString("email"),
    //                     result.getString("first_name"),
    //                     result.getString("last_name"),
    //                     result.getString("origin_country"),
    //                     result.getString("passport_number"),
    //                     result.getString("role"),
    //                     result.getString("age"));
    //         }
    //         return new WebError("Invalid password");
    //     } catch (Throwable e) {
    //         e.printStackTrace();
    //         return new WebError("Failed to login");
    //     } finally {
    //         try {
    //             if (conn != null) {
    //                 conn.close();
    //             }
    //         } catch (SQLException e) {
    //             e.printStackTrace();
    //         }
    //     }
    // }

    // USER - SIGN UP
    // @PostMapping("/create-user")
    // public Object createUser(@RequestBody User user) {

    //     Connection conn = new OracleConnector().getConnection();

    //     String passwordHash = BCrypt.hashpw(user.password, BCrypt.gensalt());

    //     try {
    //         PreparedStatement query = conn
    //                 .prepareStatement(String.format(
    //                         "INSERT INTO users (email, password, first_name, last_name, origin_country, passport_number, role, age) VALUES ('%s', '%s', '%s', '%s', '%s', '%s', 'user', %s)",
    //                         user.email, passwordHash, user.firstName, user.lastName, user.originCountry,
    //                         user.passportNumber, user.age));
    //         query.executeQuery();

    //         PreparedStatement retrievalQueery = conn
    //                 .prepareStatement(String.format("SELECT * FROM users WHERE email = '%s'", user.email));
    //         ResultSet result = retrievalQueery.executeQuery();

    //         if (!result.next()) {
    //             return new WebError("Failed to create user");
    //         }

    //         return new LoggedUser(
    //                 result.getString("user_id"),
    //                 result.getString("email"),
    //                 result.getString("first_name"),
    //                 result.getString("last_name"),
    //                 result.getString("origin_country"),
    //                 result.getString("passport_number"),
    //                 result.getString("role"),
    //                 result.getString("age"));
    //     } catch (Throwable e) {
    //         e.printStackTrace();
    //         if (e.getMessage().contains(
    //                 "ORA-00001: unique constraint (SYSTEM.EMAIL_UK) violated on table SYSTEM.USERS columns (EMAIL)")) {
    //             return new WebError("Email already in use");
    //         }
    //         return new WebError("Failed to create user");
    //     } finally {
    //         try {
    //             if (conn != null) {
    //                 conn.close();
    //             }
    //         } catch (SQLException e) {
    //             e.printStackTrace();
    //         }
    //     }
    // }
}
