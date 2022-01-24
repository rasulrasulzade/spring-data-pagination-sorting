package com.company.springdatapaginationsorting;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/books")
public class BookController {
    @Autowired
    BookRepository bookRepository;

    @GetMapping
    public ResponseEntity<BookResponse> getBooks(@RequestParam Optional<Integer> page, @RequestParam Optional<Integer> pageSize, @RequestParam Optional<String> direction, @RequestParam Optional<String> sortBy) {
        Sort.Direction order = Sort.Direction.ASC;
        if (direction.isPresent() && direction.get().equals("desc")) order = Sort.Direction.DESC;

        Page<Book> pageData = bookRepository.findAll(
                PageRequest.of(page.orElse(0), pageSize.orElse(10), order, sortBy.orElse("id"))
        );
        BookResponse response = new BookResponse();
        response.setBooks(pageData.getContent());
        response.setPage(pageData.getNumber());
        response.setPageSize(pageData.getSize());
        response.setLast(pageData.isLast());
        response.setTotalCount(pageData.getTotalElements());
        response.setTotalPages(pageData.getTotalPages());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
