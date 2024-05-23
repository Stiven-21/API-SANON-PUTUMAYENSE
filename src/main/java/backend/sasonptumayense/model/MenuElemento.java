package backend.sasonptumayense.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "menuElemento")
public class MenuElemento {

    @Id
    @ManyToOne
    @JoinColumn(name = "menusId")
    private Menus menus;

    @Id
    @ManyToOne
    @JoinColumn(name = "elementosMenuId")
    private ElementosMenu elementosMenu;
}
