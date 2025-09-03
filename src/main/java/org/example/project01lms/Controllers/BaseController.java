package org.example.project01lms.Controllers;

import org.example.project01lms.Response.ApiResponse;

public class BaseController {
    public ApiResponse successResponse(String message , Boolean status , Object data){
        return new ApiResponse(message , status , data);

    }public ApiResponse failureResponse(String message , Boolean status , Object data){
        return new ApiResponse(message , status , data);
    }
}


//made all of it some of them are like using Dto entity relation and others
//now the things that are needed to learn are something like mapstruct and others to make the code
//        more enthusist
