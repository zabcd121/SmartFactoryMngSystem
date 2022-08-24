package com.mirae.smartfactory.controller.process;

import com.mirae.smartfactory.dto.*;
import com.mirae.smartfactory.service.ResourceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/resource")
@RequiredArgsConstructor
public class ResourceApiController {

    private final ResourceService resourceService;

    @PostMapping("/outerscrap")
    public OuterScrapNameListDto outerScrapAdd(@RequestParam String name) {
        return resourceService.saveOuterScrapName(name);
    }

    @PostMapping("/si")
    public SiNameListDto siAdd(@RequestParam String name) {
        return resourceService.saveSiName(name);
    }

    @PostMapping("/ingredient")
    public IngredientNameListDto ingredientAdd(@RequestParam String name) {
        return resourceService.saveIngredientName(name);
    }

    @PostMapping("/contact")
    public BusinessContactNameListDto businessContactAdd(@RequestParam String name) {
        return resourceService.saveBusinessContact(name);
    }

    @GetMapping
    public ResourceNameDto resourcesSearch() {
        return resourceService.findAllResources();
    }






}
