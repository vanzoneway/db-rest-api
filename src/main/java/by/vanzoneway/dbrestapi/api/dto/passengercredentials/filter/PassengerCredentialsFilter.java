package by.vanzoneway.dbrestapi.api.dto.passengercredentials.filter;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PassengerCredentialsFilter {
    private String id;
    private String cardNumber;
    private String holderName;
    private String iban;
    private String passengerId;
}