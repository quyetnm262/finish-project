package vn.com.t3h.finish_project.controller.api;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.com.t3h.finish_project.model.dto.UserDto;
import vn.com.t3h.finish_project.model.response.BaseResponse;
import vn.com.t3h.finish_project.service.IUserService;

@RestController
@RequestMapping("/api/user")
public class UserController {

    /*----------Inject start-------------------*/

    @Autowired
    private IUserService iUserService;

    /*----------Inject end-------------------*/

    @GetMapping
    public ResponseEntity<?> getUsers(){
        return ResponseEntity.ok(BaseResponse.builder()
                .message(HttpStatus.OK.name())
                .messageCode(HttpStatus.OK.value())
                .data(iUserService.getUsers())
                .build());
    }

    @PostMapping
    public ResponseEntity<?> postUser(@RequestBody UserDto userDto){
        return ResponseEntity.ok(BaseResponse.builder()
                .message(HttpStatus.OK.name())
                .messageCode(HttpStatus.OK.value())
                .data(iUserService.createUser(userDto))
                .build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> putUser(@RequestBody UserDto userDto, @PathVariable Integer id){
        return ResponseEntity.ok(BaseResponse.builder()
                .message(HttpStatus.OK.name())
                .messageCode(HttpStatus.OK.value())
                .data(iUserService.updateUser(userDto, id))
                .build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Integer id){
        iUserService.deleteUser(id);
        return ResponseEntity.ok(BaseResponse.builder()
                .message(HttpStatus.OK.name())
                .messageCode(HttpStatus.OK.value())
                .build());
    }

}
