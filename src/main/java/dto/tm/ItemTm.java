package dto.tm;

import javafx.scene.control.Button;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ItemTm {
    private String code;
    private String name;
    private String category;
    private String status;
    private Button btn;
    private String contact;
}
