package dev.aioti.backend.controller

import dev.aioti.backend.dto.request.DeviceRegisterRequestDTO
import dev.aioti.backend.service.DeviceService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/device")
class DeviceController(
    val service: DeviceService
) {

    @PostMapping
    fun create(@RequestBody requestDTO: DeviceRegisterRequestDTO) =
        ResponseEntity
            .status(HttpStatus.CREATED)
            .body(service.create(requestDTO))

    @GetMapping("/{id}")
    fun device(@PathVariable id: Long) =
        ResponseEntity.ok(service.device(id))

    @GetMapping
    fun devices() =
        ResponseEntity.ok(service.devices())

    @PatchMapping("/{id}")
    fun update(@PathVariable id: Long, @RequestBody requestDTO: DeviceRegisterRequestDTO) =
        ResponseEntity.ok(service.update(id, requestDTO))

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long) =
        ResponseEntity.ok(service.delete(id))
}