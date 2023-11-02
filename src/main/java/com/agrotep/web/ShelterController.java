package com.agrotep.web;

import com.agrotep.dto.BookFullResponse;
import com.agrotep.dto.BookRequest;
import com.agrotep.model.ErrorResponse;
import com.agrotep.model.ValidationErrorResponse;
import com.agrotep.service.ShelterService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import jakarta.validation.Valid;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/books")
@RequiredArgsConstructor
@FieldDefaults(level= AccessLevel.PRIVATE)
public class ShelterController {

    final ShelterService shelterService;

    @Operation(
            summary = "Get all books",
            responses = @ApiResponse(responseCode = "200", description = "Books", content = @Content(array = @ArraySchema(schema = @Schema(implementation = BookFullResponse.class))))
    )
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<BookFullResponse> books() {
        return shelterService.getAll();
    }

    @Operation(
            summary = "Get book by ID",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Book for requested ID", content = @Content(schema = @Schema(implementation = BookFullResponse.class))),
                    @ApiResponse(responseCode = "404", description = "Requested data not found", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
            }
    )
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public BookFullResponse book(@PathVariable Long id) {
        return shelterService.getBook(id);
    }

    @Operation(
            summary = "Create new book",
            responses = {
                    @ApiResponse(responseCode = "201", description = "New book is created"),
                    @ApiResponse(responseCode = "400", description = "Wrong request format", content = @Content(schema = @Schema(implementation = ValidationErrorResponse.class)))
            }
    )
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createBook(@Valid @RequestBody BookRequest request) {
        final Long id = shelterService.create(request);
        final URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(id)
                .toUri();

        return ResponseEntity.created(uri).build();
    }

    @Operation(
            summary = "Update existing book",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Book for requested ID is updated", content = @Content(schema = @Schema(implementation = BookFullResponse.class))),
                    @ApiResponse(responseCode = "400", description = "Wrong request format", content = @Content(schema = @Schema(implementation = ValidationErrorResponse.class))),
                    @ApiResponse(responseCode = "404", description = "Requested data not found", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
            }
    )
    @PatchMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public BookFullResponse updateBook(@PathVariable Integer id, @Valid @RequestBody BookRequest request) {
        return shelterService.update(id, request);
    }

    @Operation(
            summary = "Remove book for ID",
            responses = @ApiResponse(responseCode = "204", description = "Book for requested ID is removed")
    )
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable Long id) {
        shelterService.delete(id);
    }
}
