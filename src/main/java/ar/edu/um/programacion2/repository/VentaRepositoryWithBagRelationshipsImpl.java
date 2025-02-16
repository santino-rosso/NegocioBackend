package ar.edu.um.programacion2.repository;

import ar.edu.um.programacion2.domain.Venta;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.hibernate.annotations.QueryHints;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

/**
 * Utility repository to load bag relationships based on https://vladmihalcea.com/hibernate-multiplebagfetchexception/
 */
public class VentaRepositoryWithBagRelationshipsImpl implements VentaRepositoryWithBagRelationships {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<Venta> fetchBagRelationships(Optional<Venta> venta) {
        return venta.map(this::fetchAdicionales).map(this::fetchCaracteristicas);
    }

    @Override
    public Page<Venta> fetchBagRelationships(Page<Venta> ventas) {
        return new PageImpl<>(fetchBagRelationships(ventas.getContent()), ventas.getPageable(), ventas.getTotalElements());
    }

    @Override
    public List<Venta> fetchBagRelationships(List<Venta> ventas) {
        return Optional.of(ventas).map(this::fetchAdicionales).map(this::fetchCaracteristicas).orElse(Collections.emptyList());
    }

    Venta fetchAdicionales(Venta result) {
        return entityManager
            .createQuery("select venta from Venta venta left join fetch venta.adicionales where venta is :venta", Venta.class)
            .setParameter("venta", result)
            .setHint(QueryHints.PASS_DISTINCT_THROUGH, false)
            .getSingleResult();
    }

    List<Venta> fetchAdicionales(List<Venta> ventas) {
        HashMap<Object, Integer> order = new HashMap<>();
        IntStream.range(0, ventas.size()).forEach(index -> order.put(ventas.get(index).getId(), index));
        List<Venta> result = entityManager
            .createQuery("select distinct venta from Venta venta left join fetch venta.adicionales where venta in :ventas", Venta.class)
            .setParameter("ventas", ventas)
            .setHint(QueryHints.PASS_DISTINCT_THROUGH, false)
            .getResultList();
        Collections.sort(result, (o1, o2) -> Integer.compare(order.get(o1.getId()), order.get(o2.getId())));
        return result;
    }

    Venta fetchCaracteristicas(Venta result) {
        return entityManager
            .createQuery("select venta from Venta venta left join fetch venta.caracteristicas where venta is :venta", Venta.class)
            .setParameter("venta", result)
            .setHint(QueryHints.PASS_DISTINCT_THROUGH, false)
            .getSingleResult();
    }

    List<Venta> fetchCaracteristicas(List<Venta> ventas) {
        HashMap<Object, Integer> order = new HashMap<>();
        IntStream.range(0, ventas.size()).forEach(index -> order.put(ventas.get(index).getId(), index));
        List<Venta> result = entityManager
            .createQuery("select distinct venta from Venta venta left join fetch venta.caracteristicas where venta in :ventas", Venta.class)
            .setParameter("ventas", ventas)
            .setHint(QueryHints.PASS_DISTINCT_THROUGH, false)
            .getResultList();
        Collections.sort(result, (o1, o2) -> Integer.compare(order.get(o1.getId()), order.get(o2.getId())));
        return result;
    }
}
