package com.zti.partpicker.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.zti.partpicker.exception.CPUNotFoundException;
import com.zti.partpicker.model.CPU;
import com.zti.partpicker.model.PubSubPayload;
import com.zti.partpicker.pubsub.PubSub;
import com.zti.partpicker.repository.CPURepository;
import com.zti.partpicker.repository.ConfigurationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller class responsible for handling CPU-related operations and endpoints.
 */
//@CrossOrigin(origins = "https://kaktus-react-spring.netlify.app")
@CrossOrigin("*")
@RestController
@RequestMapping("/api/parts/cpu")
public class CPUController {

    @Autowired
    private final CPURepository repository;

    @Autowired
    private final ConfigurationRepository configurationRepository;

    @Autowired
    private PubSub.PubsubOutboundGateway messagingGateway;


    /**
     * Constructor for the CPUController class.
     *
     * @param repository An instance of CPURepository for interacting with the CPUs' data.
     * @param configurationRepository An instance of ConfigurationRepository for preparing JSON responses
     */
    CPUController(CPURepository repository, ConfigurationRepository configurationRepository) {
        this.repository = repository;
        this.configurationRepository = configurationRepository;
    }

    /**
     * Retrieves a list of all CPUs.
     *
     * @return A list of CPU objects.
     */
    @GetMapping
    List<CPU> all() {
        return repository.findAll();
    }

    /**
     * Creates a new CPU.
     *
     * @param newCPU The CPU object containing the details of the new CPU.
     * @return The saved CPU object.
     */
    @PostMapping
    CPU newCPU(@RequestBody CPU newCPU) throws JsonProcessingException {
        ObjectWriter ow = new ObjectMapper().writer();
        String json = ow.writeValueAsString(new PubSubPayload("ROLE_USER", newCPU.getName(), newCPU.getManufacturer(), newCPU.getPrice()));
        messagingGateway.sendToPubsub(json);
        return repository.save(newCPU);
    }

    /**
     * Retrieves details of a single CPU by its ID.
     *
     * @param id The ID of the CPU to retrieve.
     * @return The CPU object corresponding to the provided ID.
     * @throws CPUNotFoundException If no CPU with the given ID is found.
     */
    @GetMapping("/{id}")
    CPU one(@PathVariable Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new CPUNotFoundException(id));
    }

    /**
     * Deletes a CPU by its ID.
     *
     * @param id The ID of the CPU to delete.
     */
    @DeleteMapping("/{id}")
    ResponseEntity<String> deleteCPU(@PathVariable Long id) {
        if (configurationRepository.findAllByCpu(id).size() > 0) {
            return ResponseEntity.badRequest().build();
        };

        repository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}