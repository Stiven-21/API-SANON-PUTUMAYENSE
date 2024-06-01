package backend.sasonptumayense.repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import backend.sasonptumayense.model.Pedidos;

@Repository
public interface PedidosRepository extends JpaRepository<Pedidos, Integer> {
    Optional<List<Pedidos>> findByMenusId(Integer menusId);

    Optional<List<Pedidos>> findByUserId(Integer userId);

    Optional<Pedidos> findByMenusIdAndUserId(Integer menusId, Integer userId);

    Optional<List<Pedidos>> findByEstadoPedidoId(Integer estadoPedidoId);

    Optional<List<Pedidos>> findByCreatedAt(Date createdAt);

    void deleteByMenusId(Integer menusId);

    void deleteByUserId(Integer userId);

    void deleteByMenusIdAndUserId(Integer menusId, Integer userId);
}
