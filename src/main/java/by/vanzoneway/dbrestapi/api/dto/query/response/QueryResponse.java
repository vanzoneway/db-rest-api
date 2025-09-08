package by.vanzoneway.dbrestapi.api.dto.query.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(setterPrefix = "with")
public class QueryResponse {

    private boolean success;

    private String message;

    private List<Map<String, Object>> data;

    private String csvContent;

    private String filename;

    public QueryResponse(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public QueryResponse(boolean success, String message, List<Map<String, Object>> data) {
        this.success = success;
        this.message = message;
        this.data = data;
    }


    public static QueryResponse success(String message, List<Map<String, Object>> data) {
        return new QueryResponse(true, message, data);
    }

    public static QueryResponse error(String message) {
        return new QueryResponse(false, message);
    }

}
