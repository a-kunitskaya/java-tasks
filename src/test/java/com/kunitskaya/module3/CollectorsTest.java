package com.kunitskaya.module3;

import com.google.common.collect.ImmutableList;
import com.kunitskaya.module3.domain.collectors.A;
import org.testng.annotations.Test;

import java.util.List;

import static com.kunitskaya.logging.ProjectLogger.LOGGER;
import static com.kunitskaya.module3.service.collectors.CustomImmutableListCollector.toCustomImmutablelist;

public class CollectorsTest {

    @Test(description = "Tests custom immutable list collector on multiple instances")
    public void testCustomImmutableList() {

        LOGGER.info("Initializing stream...");

        List<A> aInstances = A.getAInstances(300);
        ImmutableList<A> immutableList = aInstances.stream()
                                                   .peek(i -> LOGGER.info("Instance in stream: " + i.getName()))
                                                   .collect(toCustomImmutablelist());

        LOGGER.info("Initializing parallel stream...");

        ImmutableList<A> immutableListParallel = aInstances.parallelStream()
                                                           .peek(i -> LOGGER.info("Instance in parallel stream: " + i.getName()))
                                                           .collect(toCustomImmutablelist());
    }
}
