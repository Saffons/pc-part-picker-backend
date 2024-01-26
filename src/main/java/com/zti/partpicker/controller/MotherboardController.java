package com.zti.partpicker.controller;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.zti.partpicker.exception.MotherboardNotFoundException;
import com.zti.partpicker.model.Motherboard;
import com.zti.partpicker.model.PubSubPayload;
import com.zti.partpicker.pubsub.PubSub;
import com.zti.partpicker.repository.ConfigurationRepository;
import com.zti.partpicker.repository.MotherboardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controller class responsible for handling Motherboard-related operations and endpoints.
 */
@CrossOrigin("*")
@RestController
@RequestMapping("/api/parts/motherboard")
public class MotherboardController {

    @Autowired
    private final MotherboardRepository repository;

    @Autowired
    private final ConfigurationRepository configurationRepository;

    @Autowired
    private PubSub.PubsubOutboundGateway messagingGateway;


    /**
     * Constructor for the MotherboardController class.
     *
     * @param repository An instance of MotherboardRepository for interacting with Motherboards data.
     * @param configurationRepository An instance of ConfigurationRepository for preparing JSON responses
     */
    MotherboardController(MotherboardRepository repository, ConfigurationRepository configurationRepository) {
        this.repository = repository;
        this.configurationRepository = configurationRepository;
    }

    @GetMapping
    List<Motherboard> all() {
        return repository.findAll();
    }

    @PostMapping
    Motherboard newMotherboard(@RequestBody Motherboard newMotherboard) throws JsonProcessingException {
        ObjectWriter ow = new ObjectMapper().writer();
        String json = ow.writeValueAsString(new PubSubPayload("ROLE_USER", newMotherboard.getName(), newMotherboard.getManufacturer(), newMotherboard.getPrice()));
        messagingGateway.sendToPubsub(json);
        return repository.save(newMotherboard);
    }

    @GetMapping("/{id}")
    Motherboard one(@PathVariable Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new MotherboardNotFoundException(id));
    }

    @DeleteMapping("/{id}")
    ResponseEntity<String> deleteMotherboard(@PathVariable Long id) {
        if (configurationRepository.findAllByMotherboard(id).size() > 0) {
            return ResponseEntity.badRequest().build();
        };
        repository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}