package com.serbit.transaction.demo.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.serbit.transaction.demo.constant.DateTimeConstant;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Created by David on 01 Apr, 2023
 **/
@Data
public class TransactionCreationRequest {

    @NotNull(message = "Amount is required")
    private BigDecimal amount;

    @NotNull(message = "Transaction timestamp is required")
    @PastOrPresent(message = "Transaction timestamp must be in the past or present")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DateTimeConstant.DATE_TIME_PATTERN)
    private LocalDateTime timestamp;
}
