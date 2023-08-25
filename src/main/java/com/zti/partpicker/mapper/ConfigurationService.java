package com.zti.partpicker.mapper;

import com.zti.partpicker.model.*;
import com.zti.partpicker.repository.*;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

/**
 * Service that is used to create valid JSON response
 * that includes information about every computer part from Configuration object
 */
@Service
public class ConfigurationService {
    private final ConfigurationRepository configurationRepository;
    private final CPURepository cpuRepository;
    private final GPURepository gpuRepository;
    private final MemoryRepository memoryRepository;
    private final MotherboardRepository motherboardRepository;
    private final StorageRepository storageRepository;

    /**
     * Constructor of the ConfigurationService class
     * @param configurationRepository configurationRepository
     * @param cpuRepository cpuRepository
     * @param gpuRepository gpuRepository
     * @param memoryRepository memoryRepository
     * @param motherboardRepository motherboardRepository
     * @param storageRepository storageRepository
     */
    public ConfigurationService(ConfigurationRepository configurationRepository,
                                CPURepository cpuRepository,
                                GPURepository gpuRepository,
                                MemoryRepository memoryRepository,
                                MotherboardRepository motherboardRepository,
                                StorageRepository storageRepository) {
        this.configurationRepository = configurationRepository;
        this.cpuRepository = cpuRepository;
        this.gpuRepository = gpuRepository;
        this.memoryRepository = memoryRepository;
        this.motherboardRepository = motherboardRepository;
        this.storageRepository = storageRepository;
    }

    /**
     *
     * @param configuration Configuration that is being mapped to ConfigurationResponse
     * @return valid ConfigurationResponse object
     */
    @Transactional
    public ConfigurationResponse createConfigurationResponse(Configuration configuration) {
        CPU cpu = cpuRepository.findById(configuration.getCpu()).orElse(null);
        GPU gpu = gpuRepository.findById(configuration.getGpu()).orElse(null);
        Memory memory = memoryRepository.findById(configuration.getMemory()).orElse(null);
        Motherboard motherboard = motherboardRepository.findById(configuration.getMotherboard()).orElse(null);
        Storage storage = storageRepository.findById(configuration.getStorage()).orElse(null);

        assert cpu != null;
        assert gpu != null;
        assert memory != null;
        assert motherboard != null;
        assert storage != null;

        return new ConfigurationResponse(
                configuration.getId(),
                cpu.getPrice() + gpu.getPrice() + memory.getPrice() + motherboard.getPrice() + storage.getPrice(),
                cpu.getName(),
                cpu.getManufacturer(),
                cpu.getPrice(),
                cpu.getCores(),
                cpu.getSpeed(),
                cpu.getSocket(),
                gpu.getName(),
                gpu.getManufacturer(),
                gpu.getPrice(),
                gpu.getMemory(),
                gpu.getSpeed(),
                memory.getName(),
                memory.getManufacturer(),
                memory.getPrice(),
                memory.getMemoryType(),
                memory.getCapacity(),
                memory.getSpeed(),
                motherboard.getName(),
                motherboard.getManufacturer(),
                motherboard.getPrice(),
                motherboard.getMemoryType(),
                motherboard.getSocket(),
                motherboard.getChipset(),
                motherboard.isM2(),
                storage.getName(),
                storage.getManufacturer(),
                storage.getPrice(),
                storage.isM2(),
                storage.getCapacity(),
                storage.getSpeed()
        );
    }
}
