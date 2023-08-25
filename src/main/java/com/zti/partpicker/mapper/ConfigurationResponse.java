package com.zti.partpicker.mapper;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Class that is used for mapping Configuration object (that has references to CPU, GPU, etc.)
 * to JSON response that contains all information about every computer part from it
 */

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ConfigurationResponse {
    private Long configurationId;
    private double configurationPrice;

    private String cpuName;
    private String cpuManufacturer;
    private double cpuPrice;
    private int cpuCores;
    private double cpuSpeed;
    private String cpuSocket;

    private String gpuName;
    private String gpuManufacturer;
    private double gpuPrice;
    private int gpuMemory;
    private double gpuSpeed;

    private String memoryName;
    private String memoryManufacturer;
    private double memoryPrice;
    private String memoryMemoryType;
    private int memoryCapacity;
    private int memorySpeed;

    private String motherboardName;
    private String motherboardManufacturer;
    private double motherboardPrice;
    private String motherboardMemoryType;
    private String motherboardSocket;
    private String motherboardChipset;
    private boolean motherboardM2;

    private String storageName;
    private String storageManufacturer;
    private double storagePrice;
    private boolean storageM2;
    private int storageCapacity;
    private int storageSpeed;
}
