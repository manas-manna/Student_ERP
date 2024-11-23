package com.manasmann.studenterp.mapper;

import com.manasmann.studenterp.dto.BillRequest;
import com.manasmann.studenterp.dto.AllBillResponse;
import com.manasmann.studenterp.entity.Bills;
import org.springframework.stereotype.Service;

@Service
public class BillsMapper {

    public Bills toBills(BillRequest request) {
        return Bills.builder()
                .description(request.description())
                .amount(request.amount())
                .dueDate(request.dueDate())
                .deadline(request.deadline())
                .build();
    }


    public AllBillResponse toAllBillResponse(Bills bill, Double creditBalance, Double currentDue) {
        return new AllBillResponse(bill.getId(),bill.getDescription(),bill.getAmount(),bill.getDueDate(),
                bill.getDeadline(),creditBalance,currentDue);
    }

}