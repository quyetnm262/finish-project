package vn.com.t3h.finish_project.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.com.t3h.finish_project.model.dto.CategoryDto;
import vn.com.t3h.finish_project.model.response.BaseResponse;
import vn.com.t3h.finish_project.service.ICategoryService;

@RestController
@RequestMapping("/api/category")
public class CategoryController {


    /*----------Inject start-------------------*/
    @Autowired
    private ICategoryService iCategoryService;

    /*----------Inject end-------------------*/

    @GetMapping
    public ResponseEntity<?> getListCategory(){
        return ResponseEntity.ok(BaseResponse.builder()
                .message(HttpStatus.OK.name())
                .messageCode(HttpStatus.OK.value())
                .data(iCategoryService.getCategories())
                .build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCategory(@PathVariable Integer id){
        return ResponseEntity.ok(BaseResponse.builder()
                .message(HttpStatus.OK.name())
                .messageCode(HttpStatus.OK.value())
                .data(iCategoryService.getCategoryById(id))
                .build());
    }

    @PostMapping
    public ResponseEntity<?> postCategory(@RequestBody CategoryDto categoryDto){
        return ResponseEntity.ok(BaseResponse.builder()
                .message(HttpStatus.OK.name())
                .messageCode(HttpStatus.OK.value())
                .data(iCategoryService.createCategory(categoryDto))
                .build());

    }

    @PutMapping
    public ResponseEntity<?> putCategory(@RequestBody CategoryDto categoryDto){
        return ResponseEntity.ok(BaseResponse.builder()
                .message(HttpStatus.OK.name())
                .messageCode(HttpStatus.OK.value())
                .data(iCategoryService.updateCategory(categoryDto, categoryDto.getId()))
                .build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable Integer id){
        iCategoryService.deleteCategoryById(id);
        return ResponseEntity.ok(BaseResponse.builder()
                .message(HttpStatus.OK.name())
                .messageCode(HttpStatus.OK.value())
                .build());
    }

}
