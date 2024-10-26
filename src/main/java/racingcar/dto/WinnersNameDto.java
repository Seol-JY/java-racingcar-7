package racingcar.dto;

import java.util.List;
import racingcar.domain.Car;
import racingcar.vo.Name;

public record WinnersNameDto(List<String> winners) {

    public static WinnersNameDto from(List<Car> winningCars) {
        List<String> winnerNames = winningCars.stream()
                .map(Car::getName)
                .map(Name::getValue)
                .toList();

        return new WinnersNameDto(List.copyOf(winnerNames));
    }
}