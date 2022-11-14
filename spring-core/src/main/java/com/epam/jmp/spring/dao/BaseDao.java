package com.epam.jmp.spring.dao;

import java.util.Collections;
import java.util.List;

public interface BaseDao<T> {
    default List<T> paginate(List<T> entities, int pageSize, int pageNum) {
        int from = pageSize * (pageNum - 1);
        return entities.isEmpty() || entities.size() <= from
                ? Collections.emptyList()
                : entities.subList(from, Math.min(from + pageSize, entities.size()));
    }
}
