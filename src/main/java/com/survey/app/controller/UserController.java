package com.survey.app.controller;

import java.net.URI;
import java.util.Collections;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.suervey.app.util.AppConstants;
import com.survey.app.dto.UserData;
import com.survey.app.exception.AppException;
import com.survey.app.exception.ResourceNotFoundException;
import com.survey.app.model.Role;
import com.survey.app.model.RoleName;
import com.survey.app.model.User;
import com.survey.app.payload.ApiResponse;
import com.survey.app.payload.PagedResponse;
import com.survey.app.payload.PollResponse;
import com.survey.app.payload.SignUpRequest;
import com.survey.app.payload.UserIdentityAvailability;
import com.survey.app.payload.UserProfile;
import com.survey.app.payload.UserSummary;
import com.survey.app.repository.PollRepository;
import com.survey.app.repository.UserRepository;
import com.survey.app.repository.VoteRepository;
import com.survey.app.security.CurrentUser;
import com.survey.app.security.UserPrincipal;
import com.survey.app.service.PollService;
import com.survey.app.service.UserService;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PollRepository pollRepository;

    @Autowired
    private VoteRepository voteRepository;

    @Autowired
    private PollService pollService;
    
    @Autowired
    PasswordEncoder passwordEncoder;
    
    @Autowired
    private UserService userService;

    @GetMapping("/user/me")
    @PreAuthorize("hasRole('USER')")
    public UserSummary getCurrentUser(@CurrentUser UserPrincipal currentUser) {
        UserSummary userSummary = new UserSummary(currentUser.getId(), currentUser.getUsername(), currentUser.getName());
        return userSummary;
    }

    @GetMapping("/user/checkUsernameAvailability")
    public UserIdentityAvailability checkUsernameAvailability(@RequestParam(value = "username") String username) {
        Boolean isAvailable = !userRepository.existsByUsername(username);
        return new UserIdentityAvailability(isAvailable);
    }

    @GetMapping("/user/checkEmailAvailability")
    public UserIdentityAvailability checkEmailAvailability(@RequestParam(value = "email") String email) {
        Boolean isAvailable = !userRepository.existsByEmail(email);
        return new UserIdentityAvailability(isAvailable);
    }

    @GetMapping("/users/{username}")
    public UserProfile getUserProfile(@PathVariable(value = "username") String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User", "username", username));

        long pollCount = pollRepository.countByCreatedBy(user.getId());
        long voteCount = voteRepository.countByUserId(user.getId());

        UserProfile userProfile = new UserProfile(user.getId(), user.getUsername(), user.getName(), user.getCreatedAt(), pollCount, voteCount);

        return userProfile;
    }

    @GetMapping("/users/{username}/polls")
    public PagedResponse<PollResponse> getPollsCreatedBy(@PathVariable(value = "username") String username,
                                                         @CurrentUser UserPrincipal currentUser,
                                                         @RequestParam(value = "page", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) int page,
                                                         @RequestParam(value = "size", defaultValue = AppConstants.DEFAULT_PAGE_SIZE) int size) {
        return pollService.getPollsCreatedBy(username, currentUser, page, size);
    }


    @GetMapping("/users/{username}/votes")
    public PagedResponse<PollResponse> getPollsVotedBy(@PathVariable(value = "username") String username,
                                                       @CurrentUser UserPrincipal currentUser,
                                                       @RequestParam(value = "page", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) int page,
                                                       @RequestParam(value = "size", defaultValue = AppConstants.DEFAULT_PAGE_SIZE) int size) {
        return pollService.getPollsVotedBy(username, currentUser, page, size);
    }
    
    @GetMapping("/users")
    public List<UserData> getAllUsers() {
        return userService.getAllUsers();
    }
    
    @PostMapping("/update")
    public ResponseEntity<?> registerUser(@RequestBody UserData data) {
    	
    	User user = userRepository.findById(data.getId()).get();
        if(userRepository.existsByUsername(data.getUsername()) && !data.getUsername().equalsIgnoreCase(user.getUsername()) ) {
            return new ResponseEntity(new ApiResponse(false, "Username is already taken!"),
                    HttpStatus.BAD_REQUEST);
        }

        if(userRepository.existsByEmail(data.getEmail()) && !data.getEmail().equalsIgnoreCase(user.getEmail())) {
            return new ResponseEntity(new ApiResponse(false, "Email Address already in use!"),
                    HttpStatus.BAD_REQUEST);
        }

        // Creating user's account
        user = new User(data.getId(),data.getName(), data.getUsername(),
        		data.getEmail());


        User result = userRepository.save(user);
        return ResponseEntity.ok(new ApiResponse(true, "User registered successfully"));
    }
}
