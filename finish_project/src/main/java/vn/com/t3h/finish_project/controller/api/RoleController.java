package vn.com.t3h.finish_project.controller.api;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.com.t3h.finish_project.model.dto.RoleDto;
import vn.com.t3h.finish_project.model.response.BaseResponse;
import vn.com.t3h.finish_project.service.IRoleService;

@RestController
@RequestMapping("/api/role")
public class RoleController {

    /*----------- Inject start --------------*/
    @Autowired
    private IRoleService iRoleService;
    /*----------- Inject start --------------*/


    @GetMapping
    public ResponseEntity<?> getRole(){
        return ResponseEntity.ok(BaseResponse.builder()
                .message(HttpStatus.OK.name())
                .messageCode(HttpStatus.OK.value())
                .data(iRoleService.getRoles())
                .build());
    }

    @PostMapping
    public ResponseEntity<?> postRole(@RequestBody RoleDto dto){
        return ResponseEntity.ok(BaseResponse.builder()
                .message(HttpStatus.OK.name())
                .messageCode(HttpStatus.OK.value())
                .data(iRoleService.createRole(dto))
                .build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> putRole(@RequestBody RoleDto dto, @PathVariable Integer id){
        return ResponseEntity.ok(BaseResponse.builder()
                .message(HttpStatus.OK.name())
                .messageCode(HttpStatus.OK.value())
                .data(iRoleService.updateRole(dto,id))
                .build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteRole(@PathVariable Integer id){
        iRoleService.deleteRole(id);
        return ResponseEntity.ok(BaseResponse.builder()
                .message(HttpStatus.OK.name())
                .messageCode(HttpStatus.OK.value())
                .build());
    }


}
