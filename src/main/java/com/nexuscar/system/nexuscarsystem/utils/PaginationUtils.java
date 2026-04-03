package com.nexuscar.system.nexuscarsystem.utils;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import java.util.Collections;
import java.util.List;

public class PaginationUtils {
    public static <T> Page<T> paginateList(List<T> list, Pageable pageable) {

        if (list == null) {
            return new PageImpl<>(Collections.emptyList(), pageable, 0);
        }

        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), list.size());

        List<T> pageContent;
        if (start <= end) {
            pageContent = list.subList(start, end);
        } else {
            pageContent = Collections.emptyList();
        }

        return new PageImpl<>(pageContent, pageable, list.size());
    }
}