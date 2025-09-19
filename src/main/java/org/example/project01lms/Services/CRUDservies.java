package org.example.project01lms.Services;

import org.example.project01lms.Dto.CustomerDto.CustomerUpdationDto;

import java.util.List;

public interface CRUDservies<REQ, RES, ENTITY> {
    RES save(REQ dto);

    RES update(REQ dto);

    List<RES> findAll();

    RES findById(Long Id);
}
