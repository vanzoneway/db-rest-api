package by.vanzoneway.dbrestapi.api.dto.query.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder(setterPrefix = "with")
public class CustomQueryRequest {

    private String query;

    private boolean saveToCsv;

    private String filename;
}
