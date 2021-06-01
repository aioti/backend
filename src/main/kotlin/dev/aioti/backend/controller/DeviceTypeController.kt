package dev.aioti.backend.controller

import dev.aioti.backend.dto.request.DeviceCategoryRegisterDTO
import dev.aioti.backend.service.DeviceCategoryService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/device/type")
class DeviceTypeController(
    val service: DeviceCategoryService
) {

    @PostMapping
    fun create(@RequestBody requestDTO: DeviceCategoryRegisterDTO) =
        ResponseEntity
            .status(HttpStatus.CREATED)
            .body(service.create(requestDTO))

    @PatchMapping("/{id}")
    fun update(@RequestBody requestDTO: DeviceCategoryRegisterDTO, @PathVariable id: Long) =
        ResponseEntity.ok(service.update(id, requestDTO))

    @GetMapping
    fun deviceTypes() = ResponseEntity.ok(service.deviceTypes())

    @GetMapping("/{name}")
    fun deviceByName(@PathVariable name: String) = ResponseEntity.ok(service.deviceByName(name))

}