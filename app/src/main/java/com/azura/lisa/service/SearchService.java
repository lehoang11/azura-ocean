package com.azura.lisa.service;

import com.azura.lisa.dto.SearchDTO;
import org.springframework.data.domain.Pageable;

public interface SearchService {

    SearchDTO filter(String q, Pageable pageRequest);
}
