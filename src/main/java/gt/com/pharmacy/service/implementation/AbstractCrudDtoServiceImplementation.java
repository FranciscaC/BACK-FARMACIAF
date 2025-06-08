package gt.com.pharmacy.service.implementation;

import gt.com.pharmacy.service.interfaces.ICrudDtoService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public abstract class AbstractCrudDtoServiceImplementation<D, E, I> implements ICrudDtoService<D, I> {

    protected final JpaRepository<E, I> jpaRepository;

    protected AbstractCrudDtoServiceImplementation(JpaRepository<E, I> jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    protected abstract D toDTO(E entity);

    protected abstract E toEntity(D dto);

    @Override
    public D save(D dto) {
        E entity = toEntity(dto);
        return toDTO(jpaRepository.save(entity));
    }

    @Override
    public Optional<D> findById(I id) {
        return jpaRepository.findById(id).map(this::toDTO);
    }

    @Override
    public List<D> findAll() {
        return jpaRepository.findAll().stream().map(this::toDTO).toList();
    }

    protected abstract void updateEntityFromDto(D dto, E entity);

    @Override
    public D update(D dto, I id) {
        if (id == null) {
            throw new IllegalArgumentException("ID cannot be null for update operation");
        }
        E existingEntity = jpaRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Entity with id " + id + " not found"));
        updateEntityFromDto(dto, existingEntity);
        return toDTO(jpaRepository.save(existingEntity));
    }

    @Override
    public void deleteById(I id) {
        jpaRepository.deleteById(id);
    }
}
