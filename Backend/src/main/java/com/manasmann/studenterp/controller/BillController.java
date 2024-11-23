package com.manasmann.studenterp.controller;

import com.manasmann.studenterp.dto.BillRequest;
import com.manasmann.studenterp.dto.BillResponse;
import com.manasmann.studenterp.service.BillService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/bills")
@RequiredArgsConstructor
public class BillController {
    private final BillService billService;

    @PostMapping
    public ResponseEntity<BillResponse> createBill(@RequestBody @Valid BillRequest billRequest) {
        BillResponse response = billService.createBill(billRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}