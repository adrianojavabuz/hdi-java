package br.com.hdi.apijava.controller.exception;

import br.com.hdi.apijava.controller.response.ApiResponse;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
public class DefaultErrorController implements ErrorController {

    @GetMapping("/error")
    @ResponseBody
    public ResponseEntity<?> handleError(HttpServletRequest request) {
        HttpStatus status = getStatus(request);
        Exception ex = (Exception) request.getAttribute("javax.servlet.error.exception");
        String message = ex != null ? ex.getMessage() : "Erro interno inesperado";

        ApiResponse<?> response = ApiResponse.builder().success(false).message(message).build();

        return ResponseEntity.status(status).body(response);
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }

    private HttpStatus getStatus(HttpServletRequest request) {
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        if (statusCode == null) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return HttpStatus.valueOf(statusCode);
    }
}
