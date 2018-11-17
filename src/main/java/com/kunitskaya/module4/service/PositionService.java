package com.kunitskaya.module4.service;

import com.kunitskaya.module4.domain.Position;
import com.kunitskaya.module4.domain.Salary;
import com.kunitskaya.module6.domain.salaryemulator.Skill;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.kunitskaya.logging.ProjectLogger.LOGGER;

public class PositionService {
    public static List<Position> positions = new ArrayList<>();

    private ExpressionParser parser = new SpelExpressionParser();
    private StandardEvaluationContext context = new StandardEvaluationContext(this);

    private static Map<Position, Integer> getPositionRatings() {
        Map<Position, Integer> positionRatings = new HashMap<>();

        String message = "Position: %s, assigned rating: %s";
        for (int i = 0; i < positions.size(); i++) {
            Position p = positions.get(i);

            int rating = i + 1;
            positionRatings.put(p, rating);
            LOGGER.info(String.format(message, p.getName(), rating));
        }
        return positionRatings;
    }

    public static void recalculateSalaryForPosition(Position position) {
        Map<Position, Integer> positionRatings = getPositionRatings();

        int rating = positionRatings.get(position);
        double initialAmount = position.getSalary().getAmount();
        double finalAmount = initialAmount + rating * 10;


        String message = "Recalculating salary for: %s. Initial salary: %s, final salary: %s, position rating: %s";
        LOGGER.info(String.format(message, position.getName(), initialAmount, finalAmount, rating));

        position.setSalary(new Salary(finalAmount));
    }

    public static void dropSkill(Position position, Skill skill) {
        String message = "Removing skill: %s, from position: %s";
        LOGGER.info(String.format(message, position.getName(), skill.getName()));
        position.getSkills().remove(skill);
    }

    public static void readRequiredSkills(Position position) {
        List<Skill> skills = position.getSkills();

        String m = "Total required skills count: %s for position: %s";
        LOGGER.info(String.format(m, skills.size(), position.getName()));

        String message = "Required skill: %s, level: %s";
        skills.forEach(s ->
                LOGGER.info(String.format(message, s.getName(), s.getLevel()))
        );
    }

    public List<Position> createPositions(Map<String, Double> targetPositions) {
        LOGGER.info("Total count of positions to create: " + targetPositions.size());

        List<Position> positions = targetPositions.entrySet()
                                                  .stream()
                                                  .map(p -> new Position(p.getKey(), new Salary(p.getValue())))
                                                  .collect(Collectors.toList());

        String message = "Created position: %s, salary: %s";
        positions.forEach(p -> LOGGER.info(String.format(message, p.getName(), p.getSalary().getAmount())));
        return positions;
    }

    public void updatePositions(Map<Position, Double> positionUpdates) {
        LOGGER.info("Total count of positions to update: " + positionUpdates.size());
        String message = "Updated position: %s, set salary: %s";

        positionUpdates.forEach((position, salary) -> {
            position.setSalary(new Salary(salary));
            LOGGER.info(String.format(message, position.getName(), salary));
        });
    }

    public void readAllPositions() {
        String message = "Total positions count: %s. Listing all available positions in the company:";
        String positionMessage = "Position: %s, salary: %s";

        LOGGER.info(String.format(message, positions.size()));

        for (Position p : positions) {
            LOGGER.info(String.format(positionMessage, p.getName(), p.getSalary().getAmount()));
        }
    }

    public void deletePositions(Position... positionsToDelete) {
        LOGGER.info("Total count of positions to delete: " + positionsToDelete.length);

        for (int i = 0; i < positions.size(); i++) {
            String exp = "positions[" + i + "]";
            Position p = parser.parseExpression(exp).getValue(context, Position.class);

            String message = "Deleting position: %s, salary: %s";

            for (Position positionToDelete : positionsToDelete) {
                if (p.equals(positionToDelete)) {

                    LOGGER.info(String.format(message, p.getName(), p.getSalary().getAmount()));
                    positions.remove(p);
                }
            }
        }
    }
}
