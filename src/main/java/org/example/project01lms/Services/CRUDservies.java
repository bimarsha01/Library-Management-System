package org.example.project01lms.Services;

import org.example.project01lms.Dto.LoanDto.LoanUpdationDto;

import java.util.List;

public interface CRUDservies<REQ, RES, ENTITY> {
    RES save(REQ dto);

    RES update(LoanUpdationDto dto);

    List<RES> findAll();

    RES findById(Long Id);
}
