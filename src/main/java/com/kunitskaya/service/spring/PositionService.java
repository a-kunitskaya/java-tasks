package com.kunitskaya.service.spring;

import com.kunitskaya.domain.beans.Position;
import com.kunitskaya.domain.beans.Salary;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.kunitskaya.logging.ProjectLogger.LOGGER;

public class PositionService {

    public static List<Position> createPositions(Map<String, Salary> targetPositions) {
        List<Position> positions = targetPositions.entrySet()
                                                  .stream()
                                                  .map(p -> new Position(p.getKey(), p.getValue()))
                                                  .collect(Collectors.toList());

        String message = "Created position: %s, salary: %s";
        positions.forEach(p -> {
            LOGGER.info(String.format(message, p.getName(), p.getSalary().getAmount()));
        });

        return positions;
    }


    public static void updatePositions(Map<Position, Salary> positionUpdates) {
        String message = "Updated position: %s, set salary: %s";

        positionUpdates.forEach((position, salary) -> {
            position.setSalary(salary);
            LOGGER.info(String.format(message, position.getName(), salary.getAmount()));
        });
    }

    public static void readAllPositions() {
        List<Position> positions = Position.getAllPositions();
        String message = "Total positions count: %s. Listing all available positions in the company:";

        LOGGER.info(String.format(message, positions.size()));

        for (Position p : positions) {
            String name = p.getName() == null ? "<No name>" : p.getName();
            LOGGER.info("Position: " + name);
        }
    }

    public static void deletePositions(Position... positions) {
        LOGGER.info("Deleting positions...");


        }
}
