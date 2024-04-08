package SpectraSystems.Nexus.services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import SpectraSystems.Nexus.exceptions.ResourceNotFoundException;
import SpectraSystems.Nexus.models.User;
import SpectraSystems.Nexus.repositories.UserRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public User createUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword())); // Hash password before saving
        return userRepository.save(user);
    }

    public User updateUser(Long id, User userDetails) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));

        user.setFirstName(userDetails.getFirstName());
        user.setLastName(userDetails.getLastName());
        user.setAge(userDetails.getAge());
        user.setCountry(userDetails.getCountry());
        user.setPassport(userDetails.getPassport());
        user.setPercentage(userDetails.getPercentage());
        user.setRole(userDetails.getRole());

        return userRepository.save(user);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public UserDetailsService userDetailsService(){
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username){
                return userRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("username not found"));
            }
        };
    }

    public User save(User newUser) {
        if (newUser.getId() == null) {
          newUser.setCreatedAt(LocalDateTime.now());
        }
    
        newUser.setUpdatedAt(LocalDateTime.now());
        return userRepository.save(newUser);
      }
}
