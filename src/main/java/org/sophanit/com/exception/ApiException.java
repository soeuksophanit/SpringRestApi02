package org.sophanit.com.exception;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ApiException extends RuntimeException {

    private String detail;
    private String path;
    public ApiException(String title,String detail,String path){
        super(title);
        this.detail = detail;
        this.path = path;
    }
}
