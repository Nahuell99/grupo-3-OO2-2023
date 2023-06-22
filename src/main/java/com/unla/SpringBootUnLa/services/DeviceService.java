package com.unla.SpringBootUnLa.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.unla.SpringBootUnLa.entities.Device;

import com.unla.SpringBootUnLa.repositories.IDeviceRepository;

@Service
public class DeviceService {

    private final IDeviceRepository deviceRepository;

    public DeviceService(IDeviceRepository deviceRepository) {
        this.deviceRepository = deviceRepository;
    }

    public Device saveDevice(Device device) {
        return deviceRepository.save(device);
    }

    public Device getDeviceById(int deviceId) {
        return deviceRepository.findById(deviceId).orElse(null);
    }

    public List<Device> getAllDevices() {
        return deviceRepository.findAll();
    }

    public List<Device> getActiveDevices() {
        return deviceRepository.findByActivoTrueOrderByCreatedAt();
    }

    public void deleteDevice(int deviceId) {
        deviceRepository.deleteById(deviceId);
    }
}
