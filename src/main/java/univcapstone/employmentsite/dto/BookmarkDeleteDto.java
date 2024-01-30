package univcapstone.employmentsite.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.RequestParam;

@Data
@Getter
@Setter
public class BookmarkDeleteDto {
    @NotEmpty
    private Long postId;
    @NotEmpty
    private Long bookmarkId;
}