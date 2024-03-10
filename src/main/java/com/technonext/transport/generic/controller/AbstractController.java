package com.technonext.transport.generic.controller;

import com.technonext.transport.common.ApiConstants;
import com.technonext.transport.common.constant.ApplicationConstant;
import com.technonext.transport.common.constant.Message;
import com.technonext.transport.generic.model.BaseEntity;
import com.technonext.transport.generic.payload.request.IDto;
import com.technonext.transport.generic.payload.response.MessageResponse;
import com.technonext.transport.generic.payload.response.PageData;
import com.technonext.transport.generic.service.IService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public abstract class AbstractController<E extends BaseEntity, D extends IDto> implements IController<D> {

    protected final IService<E, D> service;


    private String getModelName(E entity) {
        return entity.getClass().getSimpleName();
    }


    @Override
    @GetMapping(ApiConstants.PATH_VARIABLE_BY_ID)
    public <T> T getSingle(@PathVariable Long id) {
        return service.getSingle(id);
    }

    @Override
    @PostMapping()
    public ResponseEntity<MessageResponse> create(@Valid @RequestBody D dto) {
        E entity =  service.create(dto);
        return ResponseEntity.ok(new MessageResponse(getModelName(entity), Message.CREATED_SUCCESSFULLY, entity.getId()));
    }

    @Override
    @PutMapping(ApiConstants.PATH_VARIABLE_BY_ID)
    public ResponseEntity<MessageResponse> update(@Valid @RequestBody D dto, @PathVariable Long id) {
         E entity = service.update(dto, id);
        return ResponseEntity.ok(new MessageResponse(getModelName(entity), Message.UPDATED_SUCCESSFULLY, entity.getId()));
    }

    @Override
    @PatchMapping(ApiConstants.PATH_VARIABLE_BY_ID)
    public ResponseEntity<MessageResponse> updateActiveStatus(@PathVariable Long id, Boolean isActive) {
        service.updateActiveStatus(id, isActive);
        return ResponseEntity.ok(new MessageResponse(Message.ACTIVE_STATUS_CHANGED_SUCCESSFULLY));
    }

    @Override
    @GetMapping(ApiConstants.GET_LIST)
    public PageData getAll(@Nullable @RequestParam(value = "active", defaultValue = "true") Boolean isActive,
                           @PageableDefault(sort = ApplicationConstant.DEFAULT_SORT,
                                   direction = Sort.Direction.DESC) Pageable pageable) {
        return service.getAll(isActive, pageable);
    }

}
