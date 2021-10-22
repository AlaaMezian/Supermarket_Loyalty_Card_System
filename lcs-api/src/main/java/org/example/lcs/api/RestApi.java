package org.example.lcs.api;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.example.lcs.common.requests.CreateUserAccountRequest;
import org.example.lcs.common.responses.UserCreatedResponse;
import org.example.lcs.service.operation.impl.CreateUserOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("lcs/v1")
public class RestApi {

    @Autowired
    private CreateUserOperation createUserOperation;

    @PostMapping(value = "/user-account" , produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Method to create User.", notes = "This method can be used to create a user through the api.")
    @ApiParam(value = "this param represent the request to createUser.", name = "CreateUserAccountRequest")
    UserCreatedResponse createUser(@RequestBody @Valid CreateUserAccountRequest createUserAccountRequest){
        return createUserOperation.execute(createUserAccountRequest);
    }
}
