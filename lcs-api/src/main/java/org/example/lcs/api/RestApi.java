package org.example.lcs.api;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.example.lcs.common.requests.CreateUserAccountRequest;
import org.example.lcs.common.requests.PurchaseRequest;
import org.example.lcs.common.requests.RedeemRequest;
import org.example.lcs.common.responses.PurchaseResponse;
import org.example.lcs.common.responses.RedeemResponse;
import org.example.lcs.common.responses.UnClaimedUsersAccounts;
import org.example.lcs.common.responses.UseResponse;
import org.example.lcs.service.operation.impl.CreateUserOperation;
import org.example.lcs.service.operation.impl.PurchaseOperation;
import org.example.lcs.service.operation.impl.RedeemOperation;
import org.example.lcs.service.operation.impl.UnClaimedUserAccountOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import sun.net.www.protocol.http.HttpURLConnection;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("lcs/v1")
public class RestApi {

    @Autowired
    private CreateUserOperation createUserOperation;

    @Autowired
    private PurchaseOperation purchaseOperation;

    @Autowired
    private UnClaimedUserAccountOperation unClaimedUserAccountOperation;

    @Autowired
    private RedeemOperation redeemOperation;

    @PostMapping(value = "/earn", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Method user to earn points", notes = "This method can be used to earn point when user purchase something")
    @ApiParam(value = "this param represent the request used to purchase an item.", name = "PurchaseRequest")
    @ApiResponses(
            value = {
                    @ApiResponse(code = HttpURLConnection.HTTP_OK, message = "Success", response = PurchaseResponse.class),
                    @ApiResponse(code = HttpURLConnection.HTTP_NOT_FOUND, message = "cashier with the id number not found in the system or user Account does not exist"),
                    @ApiResponse(code = HttpURLConnection.HTTP_BAD_REQUEST, message = "user didn't provider neither a idCardNumber or mobileNumber"),
                    @ApiResponse(code = HttpURLConnection.HTTP_INTERNAL_ERROR, message = "failed to create transaction")
            })
    PurchaseResponse earnPoints(@RequestBody @Valid PurchaseRequest purchaseRequest) {
        return purchaseOperation.execute(purchaseRequest);
    }

    @PostMapping(value = "/user-account", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Method to create User.", notes = "This method can be used to create a user through the api. returns success if user created successfully other wise returns failed to save user account in message field")
    @ApiParam(value = "this param represent the request to createUser.", name = "CreateUserAccountRequest")
    @ApiResponses(
            value = {
                    @ApiResponse(code = HttpURLConnection.HTTP_OK, message = "Success", response = UseResponse.class),
                    @ApiResponse(code = HttpURLConnection.HTTP_INTERNAL_ERROR, message = "failed to save user account")

            })
    UseResponse createUser(@RequestBody @Valid CreateUserAccountRequest createUserAccountRequest) {
        return createUserOperation.execute(createUserAccountRequest);
    }

    @PostMapping(value = "/redeem", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Method to burn/redeem points earned by users.", notes = "This method can be used to redeem the point earned by users.")
    @ApiParam(value = "this param represent the request used to redeemPoints.", name = "RedeemRequest")
    @ApiResponses(
            value = {
                    @ApiResponse(code = HttpURLConnection.HTTP_OK, message = "Success", response = RedeemResponse.class),
                    @ApiResponse(code = HttpURLConnection.HTTP_BAD_REQUEST, message = "user provide invalid redeemValue"),
                    @ApiResponse(code = HttpURLConnection.HTTP_NOT_FOUND, message = "cashier with the id number not found in the system or user Account does not exist"),
                    @ApiResponse(code = HttpURLConnection.HTTP_INTERNAL_ERROR, message = "failed to create transaction")
            })
    RedeemResponse redeemPoints(@RequestBody @Valid RedeemRequest redeemRequest) {
        return redeemOperation.execute(redeemRequest);
    }

    @GetMapping(value = "/users/unclaimed-accounts", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "this method going to be used to get a list of unclaimed balances for existing users")
    UnClaimedUsersAccounts getUnclaimedUserAccounts() {
        return unClaimedUserAccountOperation.execute();
    }
}
