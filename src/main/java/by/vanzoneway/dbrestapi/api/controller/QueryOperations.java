package by.vanzoneway.dbrestapi.api.controller;

import by.vanzoneway.dbrestapi.api.dto.query.request.CustomQueryRequest;
import by.vanzoneway.dbrestapi.api.dto.query.response.QueryResponse;
import org.springframework.http.ResponseEntity;

public interface QueryOperations {

    ResponseEntity<QueryResponse> executeQuery(CustomQueryRequest request);
}
