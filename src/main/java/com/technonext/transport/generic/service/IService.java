package com.technonext.transport.generic.service;

import com.technonext.transport.generic.model.BaseEntity;
import com.technonext.transport.generic.payload.request.IDto;
import com.technonext.transport.generic.payload.response.PageData;
import org.springframework.data.domain.Pageable;

import java.util.Collection;
import java.util.List;

public interface IService<E extends BaseEntity, D extends IDto> {

    E create(D d);

    E update(D d, Long id);

    <T> T getSingle(Long id);

    E findById(Long id);

    void updateActiveStatus(Long id, Boolean b);

    E saveItem(E entity);

    List<E> saveItemList(List<E> entityList);

    default void validateClientData(D d, Long id) {
    }

    PageData getAll(Boolean isActive, Pageable pageable);

    List<E> findAllByIds(Collection<Long> ids);
}
