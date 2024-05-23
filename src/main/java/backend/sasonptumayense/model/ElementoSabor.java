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
@Table(name = "elementoSabor")
public class ElementoSabor {
    @Id
    @ManyToOne
    @JoinColumn(name = "elementoId")
    private ElementosMenu elemento;

    @Id
    @ManyToOne
    @JoinColumn(name = "saborId")
    private SaboresPostres sabor;
}
