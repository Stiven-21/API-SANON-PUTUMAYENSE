package backend.sasonptumayense.repository;

//import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import backend.sasonptumayense.model.MenuElemento;

@Repository
public interface MenuElementoRepository extends JpaRepository<MenuElemento, Integer> {
    /*void deleteMenuElementoByIdElementosMenuId(Integer elementosMenuId);
    void deleteMenuElementoByMenusId(Integer menusId);

    Optional<MenuElemento> findByMenusIdAndElementosMenuId(Integer menusId, Integer elementosMenuId);

    Optional<MenuElemento> findByMenusId(Integer menusId);

    Optional<MenuElemento> findByElementosMenuId(Integer elementosMenuId);*/

}
