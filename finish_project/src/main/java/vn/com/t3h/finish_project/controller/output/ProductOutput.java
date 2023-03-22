package vn.com.t3h.finish_project.controller.output;

import lombok.Data;
import vn.com.t3h.finish_project.model.dto.ProductDto;

import java.util.ArrayList;
import java.util.List;

@Data
public class ProductOutput {

    private Integer page;
    private Integer totalPage;
    private List<ProductDto> listResult = new ArrayList<>();
}
