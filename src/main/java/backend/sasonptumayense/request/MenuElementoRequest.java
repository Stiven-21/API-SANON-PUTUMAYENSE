package backend.sasonptumayense.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MenuElementoRequest {
    String menuId;
    String elementoMenuId;
}
