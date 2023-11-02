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
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import jakarta.validation.Valid;

import java.net.URI;
import java.util.List;

@Controller
//@RequestMapping("books")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ShelterController {

    final ShelterService shelterService;

    //    @Operation(
//            summary = "Get all books",
//            responses = @ApiResponse(responseCode = "200", description = "Books", content = @Content(array = @ArraySchema(schema = @Schema(implementation = BookFullResponse.class))))
//    )
    @GetMapping()
    public String getAllBooks(Model model) {
        List<BookFullResponse> bookFullResponseList = shelterService.getAll();
        model.addAttribute("books", bookFullResponseList);
        return "booksShelter";
    }

//    @Operation(
//            summary = "Get book by ID",
//            responses = {
//                    @ApiResponse(responseCode = "200", description = "Book for requested ID", content = @Content(schema = @Schema(implementation = BookFullResponse.class))),
//                    @ApiResponse(responseCode = "404", description = "Requested data not found", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
//            }
//    )
//    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
//    public BookFullResponse book(@PathVariable Long id) {
//        return shelterService.getBook(id);
//    }
//
//    @Operation(
//            summary = "Create new book",
//            responses = {
//                    @ApiResponse(responseCode = "201", description = "New book is created"),
//                    @ApiResponse(responseCode = "400", description = "Wrong request format", content = @Content(schema = @Schema(implementation = ValidationErrorResponse.class)))
//            }
//    )

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String getCreatePage(@ModelAttribute("book") BookRequest request){
        return "createBook";
    }

    @PostMapping("/create")
    public String createBook(@Valid BookRequest request, BindingResult result, Model model) {
        if (result.hasErrors()) {
            if (result.hasFieldErrors("name")) {
                model.addAttribute("error_message", "Длинное название книги, используйте не больше 10 символов");
            }
            if (result.hasFieldErrors("bookDate")) {
                model.addAttribute("error_message", "Дата которую вы выбрали признана будущим. Пожалуцйста выберите прошедшую дату");
            }
            return "createBook";
        }
        shelterService.create(request);

        List<BookFullResponse> bookFullResponseList = shelterService.getAll();

        model.addAttribute("books", bookFullResponseList);

        return "redirect:/";
    }

    @GetMapping("/shelter-book-sorted")
    public String shelterBookSorted(Model model) {
        List<BookFullResponse> books = shelterService.getAllSortedByName();
        model.addAttribute("books", books);
        return "booksShelter";
    }


//    @RequestMapping(value = "/create",method = RequestMethod.POST)
//    public ResponseEntity<Void> createBook(@Valid @RequestBody BookRequest request) {
//        final Long id = shelterService.create(request);
//        final URI uri = ServletUriComponentsBuilder
//                .fromCurrentRequest()
//                .path("/{id}")
//                .buildAndExpand(id)
//                .toUri();
//
//        return ResponseEntity.created(uri).build();
//    }
//
//    @Operation(
//            summary = "Update existing book",
//            responses = {
//                    @ApiResponse(responseCode = "200", description = "Book for requested ID is updated", content = @Content(schema = @Schema(implementation = BookFullResponse.class))),
//                    @ApiResponse(responseCode = "400", description = "Wrong request format", content = @Content(schema = @Schema(implementation = ValidationErrorResponse.class))),
//                    @ApiResponse(responseCode = "404", description = "Requested data not found", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
//            }
//    )
//    @PatchMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
//    public BookFullResponse updateBook(@PathVariable Integer id, @Valid @RequestBody BookRequest request) {
//        return shelterService.update(id, request);
//    }
//
//    @Operation(
//            summary = "Remove book for ID",
//            responses = @ApiResponse(responseCode = "204", description = "Book for requested ID is removed")
//    )
//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    @DeleteMapping("/{id}")
//    public void deleteBook(@PathVariable Long id) {
//        shelterService.delete(id);
//    }
}
