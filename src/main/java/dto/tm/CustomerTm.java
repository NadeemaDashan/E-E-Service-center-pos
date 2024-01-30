package dto.tm;

import com.jfoenix.controls.JFXButton;
import javafx.scene.control.Button;
import lombok.*;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class CustomerTm {
    private String id;
    private String name;
    private String contactNumber;
    private String email ;
    private Button btn;

}
