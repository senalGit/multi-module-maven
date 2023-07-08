package fr.maetic.dto;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/newuser")
public class CreerUser {
   // private final UserService userService;

 //   @PostMapping
  //  public ResponseEntity<HttpResponse> createUser(@RequestBody User user) {
      //  User newUser = userService.saveUser(user);

        // return ResponseEntity.created(URI.create("")).body(
        //         HttpResponse.builder()
        //                 .timestamp(LocalDateTime.now().toString())
        //                 .data(Map.of("user", newUser))
        //                 .message("User created")
        //                 .status(HttpStatus.CREATED)
        //                 .statusCode(HttpStatus.CREATED.value())
        //                 .build()
        // );
   // }
}
