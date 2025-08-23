package org.example.project01lms.Services;

import java.util.List;

public interface CRUDservies<DTO , ENTITY> {
    DTO save(DTO dto);

    DTO update(DTO dto);

    List<DTO>  findall();

    DTO findById(DTO dto);
}
