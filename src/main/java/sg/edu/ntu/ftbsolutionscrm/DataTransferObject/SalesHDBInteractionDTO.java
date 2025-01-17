package sg.edu.ntu.ftbsolutionscrm.DataTransferObject;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sg.edu.ntu.ftbsolutionscrm.entity.Salesperson;





@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class SalesHDBInteractionDTO 
{
    private Long salesperson;
    private Long hdbUserId;
    private String review;
}
