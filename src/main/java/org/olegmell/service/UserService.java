package org.olegmell.service;


import org.olegmell.domain.Request;
import org.olegmell.domain.Role;
import org.olegmell.domain.User;
import org.olegmell.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MailSender mailSender;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username);
    }

    public boolean addUser(User user){
        User userFromDb = userRepository.findByUsername(user.getUsername());

        if(userFromDb != null){
            return false;
        }
        user.setActive(true);
        user.setUser_role(Role.USER);
        user.setActivationCode(UUID.randomUUID().toString());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);

        sendMessage(user);

        return true;
    }

    private void sendMessage(User user) {
        if (!StringUtils.isEmpty(user.getEmail())) {
            String message = String.format(
                    "Здравствуйте, %s! \n" +
                            "Добро пожаловать. Пожалуйста, перейдите по следующей ссылке: link: http://localhost:8080/activate/%s",
                    user.getUsername(),
                    user.getActivationCode()
            );

            mailSender.send(user.getEmail(), "Activation code", message);
        }
    }

    public boolean activateUser(String code) {
        User user = userRepository.findByActivationCode(code);

        if(user == null){
            return false;
        }
        user.setActivationCode(null);
        userRepository.save(user);

        return true;
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public void saveUser(User user, String username, Map<String, String> form) {
        user.setUsername(username);



        userRepository.save(user);
    }

    public void updateProfile(User user, String password, String email) {
        String userEmail = user.getEmail();

        boolean isEmailChanged = (email != null && !email.equals(userEmail))
                || (userEmail != null && !userEmail.equals(email));
        if(isEmailChanged){
            user.setEmail(email);
            if(!StringUtils.isEmpty(email)){
                user.setActivationCode(UUID.randomUUID().toString());
            }
        }
        if(!StringUtils.isEmpty(password)){ //!
            user.setPassword(passwordEncoder.encode(password));
        }
        userRepository.save(user);

        if(isEmailChanged){
            sendMessage(user);
        }
    }

    public Iterable<Request> getUserRequests (User user){
       return userRepository.getOne(user.getId()).getRequests();
    }

    public Iterable<Request> getActiveUserRequests (User user){
        List<Request> userRequests = new ArrayList<>(userRepository.getOne(user.getId()).getRequests());
        userRequests.removeIf(request -> request.getStatus().getId()==3);
        Collections.sort(userRequests, Collections.reverseOrder());
        return userRequests;
    }

    public void deleteUserById (Integer userId){
        userRepository.deleteById(userId);
    }
}
