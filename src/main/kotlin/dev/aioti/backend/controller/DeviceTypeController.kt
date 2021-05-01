package dev.aioti.backend.controller

import dev.aioti.backend.dto.request.DeviceTypeRegisterDTO
import dev.aioti.backend.service.DeviceTypeService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/device/type")
class DeviceTypeController(
    val service: DeviceTypeService
) {

    @PostMapping
    fun create(@RequestBody requestDTO: DeviceTypeRegisterDTO) =
        ResponseEntity
            .status(HttpStatus.CREATED)
            .body(service.create(requestDTO))

    @PatchMapping("/{id}")
    fun update(@RequestBody requestDTO: DeviceTypeRegisterDTO, @PathVariable id: Long) =
        ResponseEntity.ok(service.update(id, requestDTO))

    @GetMapping
    fun deviceTypes() = ResponseEntity.ok(service.deviceTypes())

}