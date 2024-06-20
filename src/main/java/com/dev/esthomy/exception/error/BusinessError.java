package com.dev.esthomy.exception.error;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BusinessError {
    public static final BusinessError INVALID_CREDENTIAL = new BusinessError("INVALID_CREDENTIAL", "Invalid Credential", "Invalid Credential");
    public static final BusinessError BROKER_NOT_FOUND = new BusinessError("BROKER_NOT_FOUND", "Broker Not Found", "Broker Not Found");
    public static final BusinessError CLIENT_NOT_FOUND = new BusinessError("CLIENT_NOT_FOUND", "Client Not Found", "Client Not Found");
    public static final BusinessError EMAIL_ALREADY_EXIST = new BusinessError("EMAIL_ALREADY_EXIST", "Email already exist", "Email already exist");
    public static final BusinessError CREDENTIAL_NOT_FOUND = new BusinessError("CREDENTIAL_NOT_FOUND", "Credential Not Found", "Credential Not Found");
    public static final BusinessError INVALID_ROLE = new BusinessError("INVALID_ROLE", "Invalid Role", "Invalid Role");
    public static final BusinessError ACCOUNT_NOT_FOUND = new BusinessError("ACCOUNT_NOT_FOUND", "Account Not Found", "Account Not Found");
    public static final BusinessError OPERATION_NOT_FOUND = new BusinessError("OPERATION_NOT_FOUND", "Operation Not Found", "Operation Not Found");
    private String code;
    private String message;
    private String description;

}
