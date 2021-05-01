package dev.aioti.backend.controller

import dev.aioti.backend.dto.CurrentUserDTO
import dev.aioti.backend.dto.request.HouseRequestDTO
import dev.aioti.backend.entity.House
import dev.aioti.backend.service.HouseService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/house")
class HouseController(
    private val service: HouseService,
    private val currentUserDTO: CurrentUserDTO
) {

    @GetMapping
    fun houses() =
        ResponseEntity.ok(service.houses(currentUserDTO.user))

    @GetMapping("/{id}")
    fun house(@PathVariable id: Long) =
        ResponseEntity.ok(service.house(id, currentUserDTO.user))

    @PostMapping
    fun create(@RequestBody requestDTO: HouseRequestDTO) =
        ResponseEntity
            .status(HttpStatus.CREATED)
            .body(service.create(House(requestDTO, currentUserDTO.user)))

    @PatchMapping("/{id}")
    fun update(@RequestBody requestDTO: HouseRequestDTO, @PathVariable id: Long) =
        ResponseEntity.ok(service.update(id, House(requestDTO), currentUserDTO.user))

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long) =
        ResponseEntity.ok(service.delete(id, currentUserDTO.user))

    @PutMapping("/{houseId}/user/{userId}")
    fun permitUser(@PathVariable houseId: Long, @PathVariable userId: Long) =
        ResponseEntity.ok(service.permitUser(houseId, userId, currentUserDTO.user))

    @DeleteMapping("/{houseId}/user/{userId}")
    fun revokeUser(@PathVariable houseId: Long, @PathVariable userId: Long) =
        ResponseEntity.ok(service.revokeUser(houseId, userId, currentUserDTO.user))

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long) =
        ResponseEntity.ok(service.delete(id, currentUserDTO.user))
}
