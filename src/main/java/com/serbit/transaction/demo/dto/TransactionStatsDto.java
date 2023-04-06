package com.serbit.transaction.demo.dto;

import lombok.Data;

import java.math.BigDecimal;

/**
 * Created by David on 02 Apr, 2023
 **/
@Data
public class TransactionStatsDto {

    private BigDecimal sum;

    private BigDecimal avg;

    private BigDecimal max;

    private BigDecimal min;

    private long count;
}
