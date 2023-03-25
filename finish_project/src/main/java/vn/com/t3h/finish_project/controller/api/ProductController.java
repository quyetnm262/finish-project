package vn.com.t3h.finish_project.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.com.t3h.finish_project.model.dto.ProductDto;
import vn.com.t3h.finish_project.model.response.BaseResponse;
import vn.com.t3h.finish_project.service.IProductService;

import java.util.List;


@RestController("API")
@RequestMapping("/api/product")
public class ProductController {


    /*----------Inject start-------------------*/
    @Autowired
    private IProductService iProductService;

    /*----------Inject end-------------------*/




    @GetMapping
    public ResponseEntity<?> getProducts(){
        return ResponseEntity.ok(BaseResponse.builder()
                .message(HttpStatus.OK.name())
                .messageCode(HttpStatus.OK.value())
                .data(iProductService.getProducts())
                .build());
    }
    @GetMapping("/{categoryId}")
    public ResponseEntity<?> getProductsByCategoryId(@PathVariable Integer categoryId){
        return ResponseEntity.ok(BaseResponse.builder()
                .message(HttpStatus.OK.name())
                .messageCode(HttpStatus.OK.value())
                .data(iProductService.getProductsByCategoryId(categoryId))
                .build());
    }


    @PostMapping
    public ResponseEntity<?> postProduct(@RequestBody ProductDto dto){
        return ResponseEntity.ok(BaseResponse.builder()
                .message(HttpStatus.OK.name())
                .messageCode(HttpStatus.OK.value())
                .data(iProductService.createProduct(dto))
                .build());

    }

    @PutMapping("")
    public ResponseEntity<?> putProduct(@RequestBody ProductDto dto){
        return ResponseEntity.ok(BaseResponse.builder()
                .message(HttpStatus.OK.name())
                .messageCode(HttpStatus.OK.value())
                .data(iProductService.updateProduct(dto, dto.getId()))
                .build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Integer id){

        iProductService.deleteProduct(id);
        return ResponseEntity.ok(BaseResponse.builder()
                .message(HttpStatus.OK.name())
                .messageCode(HttpStatus.OK.value())
                .build());

    }

}
