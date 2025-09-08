package by.vanzoneway.dbrestapi.core.service.impl;

import by.vanzoneway.dbrestapi.api.dto.query.response.QueryResponse;
import by.vanzoneway.dbrestapi.core.exception.NotAllowedDatabaseOperationException;
import by.vanzoneway.dbrestapi.core.service.QueryExecutionService;
import com.opencsv.CSVWriter;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.io.StringWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class QueryExecutionServiceImpl implements QueryExecutionService {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public QueryResponse executeQuery(String sqlQuery, boolean saveToCsv, String customFilename) {
        try {
            validateQuery(sqlQuery);

            String upperQuery = sqlQuery.toUpperCase().trim();
            boolean isSelectQuery = upperQuery.startsWith("SELECT");
            boolean isWithQuery = upperQuery.startsWith("WITH");
            boolean isShowQuery = upperQuery.startsWith("SHOW");
            boolean isDescribeQuery = upperQuery.startsWith("DESCRIBE") || upperQuery.startsWith("DESC");
            boolean isDdlQuery = upperQuery.startsWith("CREATE") ||
                    upperQuery.startsWith("DROP") ||
                    upperQuery.startsWith("ALTER") ||
                    upperQuery.startsWith("TRUNCATE") ||
                    upperQuery.startsWith("RENAME");

            if (isSelectQuery || isWithQuery || isShowQuery || isDescribeQuery) {
                return executeSelectQuery(sqlQuery, saveToCsv, customFilename);
            } else if (isDdlQuery) {
                return executeDdlQuery(sqlQuery);
            } else {
                return executeUpdateQuery(sqlQuery);
            }

        } catch (Exception e) {
            return createErrorResponse(e.getMessage());
        }
    }

    private QueryResponse executeSelectQuery(String sqlQuery, boolean saveToCsv, String customFilename) {
        try {
            List<Map<String, Object>> results = jdbcTemplate.queryForList(sqlQuery);

            List<String> columnNames = getColumnNames(sqlQuery);

            QueryResponse response = QueryResponse.success(
                    "Query executed successfully",
                    results
            );

            if (saveToCsv && !results.isEmpty()) {
                String csvContent = convertToCsv(results, columnNames);
                response.setCsvContent(csvContent);

                String filename = generateFilename(customFilename);
                saveToFile(csvContent, filename);
                response.setFilename(filename);
                response.setMessage("Query executed successfully and saved to " + filename);
            }

            return response;

        } catch (Exception e) {
            return createErrorResponse("Error executing query: " + e.getMessage());
        }
    }

    private QueryResponse executeUpdateQuery(String sqlQuery) {
        try {
            int affectedRows = jdbcTemplate.update(sqlQuery);

            Map<String, Object> result = new HashMap<>();
            result.put("affectedRows", affectedRows);
            result.put("queryType", getQueryType(sqlQuery));

            List<Map<String, Object>> data = new ArrayList<>();
            data.add(result);

            return QueryResponse.success(
                    "Query executed successfully. Affected rows: " + affectedRows,
                    data
            );

        } catch (Exception e) {
            return createErrorResponse("Error executing update query: " + e.getMessage());
        }
    }

    private QueryResponse executeDdlQuery(String sqlQuery) {
        try {
            jdbcTemplate.execute(sqlQuery);

            Map<String, Object> result = new HashMap<>();
            result.put("affectedRows", 0);
            result.put("queryType", getQueryType(sqlQuery));
            result.put("status", "success");

            List<Map<String, Object>> data = new ArrayList<>();
            data.add(result);

            return QueryResponse.success(
                    "DDL query executed successfully: " + getQueryType(sqlQuery),
                    data
            );

        } catch (Exception e) {
            return createErrorResponse("Error executing DDL query: " + e.getMessage());
        }
    }

    private List<String> getColumnNames(String sqlQuery) {
        try {
            return jdbcTemplate.query(sqlQuery, (ResultSet rs) -> {
                ResultSetMetaData metaData = rs.getMetaData();
                List<String> columns = new ArrayList<>();
                for (int i = 1; i <= metaData.getColumnCount(); i++) {
                    columns.add(metaData.getColumnLabel(i));
                }
                return columns;
            });
        } catch (Exception e) {
            return generateGenericColumnNames(sqlQuery);
        }
    }

    private List<String> generateGenericColumnNames(String sqlQuery) {
        List<String> columnNames = new ArrayList<>();
        String upperQuery = sqlQuery.toUpperCase();

        if (upperQuery.contains("COUNT")) columnNames.add("count");
        if (upperQuery.contains("SUM")) columnNames.add("sum");
        if (upperQuery.contains("AVG")) columnNames.add("average");
        if (upperQuery.contains("MAX")) columnNames.add("max");
        if (upperQuery.contains("MIN")) columnNames.add("min");
        
        int estimatedColumns = estimateColumnCount(upperQuery);
        for (int i = columnNames.size(); i < estimatedColumns; i++) {
            columnNames.add("column_" + (i + 1));
        }

        return columnNames;
    }

    private int estimateColumnCount(String query) {
        if (query.contains("*")) return 10;

        int commaCount = countOccurrences(query, ",");
        int fromIndex = query.indexOf("FROM");

        if (fromIndex > 0) {
            String selectPart = query.substring(0, fromIndex);
            return countOccurrences(selectPart, ",") + 1;
        }

        return Math.max(commaCount + 1, 5);
    }

    private int countOccurrences(String str, String sub) {
        return str.split(sub, -1).length - 1;
    }

    private String convertToCsv(List<Map<String, Object>> results, List<String> columnNames) {
        try (StringWriter writer = new StringWriter();
             CSVWriter csvWriter = new CSVWriter(writer)) {

            // Заголовки
            csvWriter.writeNext(columnNames.toArray(new String[0]));

            // Данные
            for (Map<String, Object> row : results) {
                String[] stringRow = new String[columnNames.size()];
                for (int i = 0; i < columnNames.size(); i++) {
                    String columnName = columnNames.get(i);
                    Object value = row.get(columnName);
                    stringRow[i] = value != null ? value.toString() : "";
                }
                csvWriter.writeNext(stringRow);
            }

            return writer.toString();
        } catch (Exception e) {
            throw new RuntimeException("Error converting to CSV", e);
        }
    }

    private String getQueryType(String sqlQuery) {
        String upperQuery = sqlQuery.toUpperCase().trim();
        if (upperQuery.startsWith("INSERT")) return "INSERT";
        if (upperQuery.startsWith("UPDATE")) return "UPDATE";
        if (upperQuery.startsWith("DELETE")) return "DELETE";
        if (upperQuery.startsWith("CREATE")) return "CREATE";
        if (upperQuery.startsWith("DROP")) return "DROP";
        if (upperQuery.startsWith("ALTER")) return "ALTER";
        if (upperQuery.startsWith("TRUNCATE")) return "TRUNCATE";
        if (upperQuery.startsWith("SELECT")) return "SELECT";
        if (upperQuery.startsWith("WITH")) return "WITH";
        if (upperQuery.startsWith("SHOW")) return "SHOW";
        if (upperQuery.startsWith("DESCRIBE") || upperQuery.startsWith("DESC")) return "DESCRIBE";
        return "UNKNOWN";
    }

    private void validateQuery(String sqlQuery) {
        if (sqlQuery == null || sqlQuery.trim().isEmpty()) {
            throw new NotAllowedDatabaseOperationException("Query cannot be empty");
        }

        String upperQuery = sqlQuery.toUpperCase();

        if (upperQuery.contains("DROP DATABASE") || upperQuery.contains("DROP SCHEMA")) {
            throw new NotAllowedDatabaseOperationException("Dropping databases or schemas is not allowed");
        }

        if (upperQuery.contains("SHUTDOWN") || upperQuery.contains("RECONFIGURE")) {
            throw new NotAllowedDatabaseOperationException("Dangerous system operations are not allowed");
        }
    }

    private String generateFilename(String customFilename) {
        if (customFilename != null && !customFilename.trim().isEmpty()) {
            return customFilename.endsWith(".csv") ? customFilename : customFilename + ".csv";
        }

        return "query_result_" +
                LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss")) +
                ".csv";
    }

    private void saveToFile(String content, String filename) {
        try {
            Files.write(Paths.get(filename), content.getBytes());
        } catch (Exception e) {
            throw new RuntimeException("Error saving to file: " + filename, e);
        }
    }

    private QueryResponse createErrorResponse(String errorMessage) {
        return QueryResponse.error(errorMessage);
    }
}