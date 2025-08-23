package org.example.project01lms.Controllers;

import org.example.project01lms.Response.ApiResponse;

public class BaseController {
    public ApiResponse successResponse(String message , Boolean status , Object data){
        return new ApiResponse(message , status , data);

    }public ApiResponse failureResponse(String message , Boolean status , Object data){
        return new ApiResponse(message , status , data);
    }
}
