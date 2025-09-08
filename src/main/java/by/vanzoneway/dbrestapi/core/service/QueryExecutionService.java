package by.vanzoneway.dbrestapi.core.service;

import by.vanzoneway.dbrestapi.api.dto.query.response.QueryResponse;

public interface QueryExecutionService {

    QueryResponse executeQuery(String sqlQuery, boolean saveToCsv, String customFilename);
}
