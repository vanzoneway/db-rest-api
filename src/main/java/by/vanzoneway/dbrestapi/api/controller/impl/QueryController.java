package by.vanzoneway.dbrestapi.api.controller.impl;

import by.vanzoneway.dbrestapi.api.controller.QueryOperations;
import by.vanzoneway.dbrestapi.api.dto.query.request.CustomQueryRequest;
import by.vanzoneway.dbrestapi.api.dto.query.response.QueryResponse;
import by.vanzoneway.dbrestapi.core.service.QueryExecutionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/queries")
@RequiredArgsConstructor
public class QueryController implements QueryOperations {

    private final QueryExecutionService queryExecutionService;

    @PostMapping("/execute")
    public ResponseEntity<QueryResponse> executeQuery(@RequestBody CustomQueryRequest request) {
        try {
            QueryResponse response = queryExecutionService.executeQuery(
                    request.getQuery(),
                    request.isSaveToCsv(),
                    request.getFilename()
            );

            return ResponseEntity.ok(response);

        } catch (Exception e) {
            QueryResponse errorResponse = new QueryResponse();
            errorResponse.setSuccess(false);
            errorResponse.setMessage("Internal server error: " + e.getMessage());
            return ResponseEntity.internalServerError().body(errorResponse);
        }
    }
}
