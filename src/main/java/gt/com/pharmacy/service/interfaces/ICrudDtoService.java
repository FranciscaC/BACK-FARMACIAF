package gt.com.pharmacy.service.interfaces;

import java.util.List;
import java.util.Optional;

public interface ICrudDtoService<D, I> {

    D save(D dto);

    Optional<D> findById(I id);

    List<D> findAll();

    D update(D dto, I id);

    void deleteById(I id);
}
