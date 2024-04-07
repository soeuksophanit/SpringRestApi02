package org.sophanit.com.exception;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ApiException extends RuntimeException {

    private String detail;
    private String path;
    private HttpStatus httpStatus;
    public ApiException(String title,String detail,String path,HttpStatus httpStatus){
        super(title);
        this.detail = detail;
        this.path = path;
        this.httpStatus = httpStatus;
    }
}
