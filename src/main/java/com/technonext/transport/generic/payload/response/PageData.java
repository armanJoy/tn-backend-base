package com.technonext.transport.generic.payload.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.domain.Page;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class PageData {
    List<? extends Object> model;
    int totalPages;
    int currentPage;
    long totalElements;

    public static PageData empty() {
        Page<Object> pagedData = Page.empty();
        return PageData.builder()
                .model(pagedData.getContent())
                .totalPages(pagedData.getTotalPages())
                .totalElements(pagedData.getTotalElements())
                .currentPage(1)
                .build();
    }
}
