package com.serbit.transaction.demo.algorithim;

import javafx.util.Pair;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * Created by David on 03 Apr, 2023
 **/
@ExtendWith(MockitoExtension.class)
class AlgorithmSolutionsTest {

    private static final String DATE_TIME_PATTERN = "dd-MM-yyyy HH:mm:ss";

    @BeforeEach
    void setUp() {
    }

    @Test
    void testMergeIntervalsScenarioOne() {

        List<Pair<LocalDateTime, LocalDateTime>> p = new ArrayList<>();

        Pair<LocalDateTime, LocalDateTime> c = new Pair<>(getDateTime("01-01-2023 01:10:00"), getDateTime("01-01-2023 01:12:00"));
        p.add(c);

        Pair<LocalDateTime, LocalDateTime> d = new Pair<>(getDateTime("01-01-2023 01:11:00"), getDateTime("01-01-2023 01:15:00"));
        p.add(d);

        Pair<LocalDateTime, LocalDateTime> e = new Pair<>(getDateTime("01-01-2023 01:16:00"), getDateTime("01-01-2023 01:18:00"));
        p.add(e);

        List<Pair<LocalDateTime, LocalDateTime>> result = AlgorithmSolutions.mergeIntervals(p);
        assertThat(result.get(0).getKey().toString()).isEqualTo("2023-01-01T01:10");
        assertThat(result.get(0).getValue().toString()).isEqualTo("2023-01-01T01:15");
        assertThat(result.get(1).getKey().toString()).isEqualTo("2023-01-01T01:16");
        assertThat(result.get(1).getValue().toString()).isEqualTo("2023-01-01T01:18");
    }

    @Test
    void testMergeIntervalsScenarioTwo() {

        List<Pair<LocalDateTime, LocalDateTime>> p = new ArrayList<>();

        Pair<LocalDateTime, LocalDateTime> c = new Pair<>(getDateTime("01-01-2023 01:10:00"), getDateTime("01-01-2023 01:12:00"));
        p.add(c);

        Pair<LocalDateTime, LocalDateTime> d = new Pair<>(getDateTime("01-01-2023 01:13:00"), getDateTime("01-01-2023 01:15:00"));
        p.add(d);

        Pair<LocalDateTime, LocalDateTime> e = new Pair<>(getDateTime("01-01-2023 01:16:00"), getDateTime("01-01-2023 01:18:00"));
        p.add(e);

        List<Pair<LocalDateTime, LocalDateTime>> result = AlgorithmSolutions.mergeIntervals(p);
        assertThat(result.get(0).getKey().toString()).isEqualTo("2023-01-01T01:10");
        assertThat(result.get(0).getValue().toString()).isEqualTo("2023-01-01T01:12");
        assertThat(result.get(1).getKey().toString()).isEqualTo("2023-01-01T01:13");
        assertThat(result.get(1).getValue().toString()).isEqualTo("2023-01-01T01:15");
        assertThat(result.get(2).getKey().toString()).isEqualTo("2023-01-01T01:16");
        assertThat(result.get(2).getValue().toString()).isEqualTo("2023-01-01T01:18");
    }

    @Test
    void testGetSubArrayWithMaxXORScenarioOne() {
        int[] array = {1,2,3,4};
        int[] result = AlgorithmSolutions.getSubArrayWithMaxXOR(array);

        assertThat(result[0]).isEqualTo(3);
        assertThat(result[1]).isEqualTo(4);
    }

    @Test
    void testGetSubArrayWithMaxXORScenarioTwo() {
        int[] array = {25, 10, 2, 8, 5, 3} ;
        int[] result = AlgorithmSolutions.getSubArrayWithMaxXOR(array);

        assertThat(result[0]).isEqualTo(25);
        assertThat(result[1]).isEqualTo(5);
    }


    private static LocalDateTime getDateTime(String dt) {
        return LocalDateTime.parse(dt, DateTimeFormatter.ofPattern(DATE_TIME_PATTERN));
    }
}