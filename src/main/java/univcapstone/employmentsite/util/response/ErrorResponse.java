package univcapstone.employmentsite.util.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorResponse extends BasicResponse {
    private Integer code;
    private String message;
}
