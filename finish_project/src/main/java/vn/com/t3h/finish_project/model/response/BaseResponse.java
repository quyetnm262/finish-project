package vn.com.t3h.finish_project.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class BaseResponse {

    private int messageCode;
    private Object message;
    private Object data;


}