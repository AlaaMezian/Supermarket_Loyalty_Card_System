package org.example.lcs.api;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.example.lcs.common.requests.CreateUserAccountRequest;
import org.example.lcs.common.requests.PurchaseRequest;
import org.example.lcs.common.responses.PurchaseResponse;
import org.example.lcs.common.responses.UserCreatedResponse;
import org.example.lcs.service.operation.impl.CreateUserOperation;
import org.example.lcs.service.operation.impl.PurchaseOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.LinkedHashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("lcs/v1")
public class RestApi {

    @Autowired
    private CreateUserOperation createUserOperation;

    @Autowired
    private PurchaseOperation purchaseOperation;

    @PostMapping(value = "/user-account" , produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Method to create User.", notes = "This method can be used to create a user through the api.")
    @ApiParam(value = "this param represent the request to createUser.", name = "CreateUserAccountRequest")
    UserCreatedResponse createUser(@RequestBody @Valid CreateUserAccountRequest createUserAccountRequest){
        return createUserOperation.execute(createUserAccountRequest);
    }

    @PostMapping(value = "/earn" , produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Method user to earn points", notes = "This method can be used to earn point when user purchase something")
    @ApiParam(value = "this param represent the request used to purchase an item.", name = "PurchaseRequest")
    PurchaseResponse earnPoints(@RequestBody @Valid PurchaseRequest purchaseRequest){
        return purchaseOperation.execute(purchaseRequest);
    }

    @PostMapping(value = "/redeem" , produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Method to burn/redeem points earned by users.", notes = "This method can be used to redeem the point earned by users.")
    @ApiParam(value = "this param represent the request to createUser.", name = "CreateUserAccountRequest")
    Map<String,Object> redeemPoints(@RequestBody @Valid CreateUserAccountRequest createUserAccountRequest){
        return new LinkedHashMap<>();
    }
}
