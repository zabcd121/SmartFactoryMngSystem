package com.mirae.smartfactory.service;

import com.mirae.smartfactory.domain.resource.AdditiveName;
import com.mirae.smartfactory.dto.ProcessDTO;
import com.mirae.smartfactory.repository.ProcessRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProcessService {
    private final ProcessRepository processRepository;

    public void insertProcess(ProcessDTO processDTO){
        AdditiveName additiveName = AdditiveName.ALLOY;
        System.out.println("additiveName = " + additiveName);
    }
}
