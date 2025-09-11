package by.vanzoneway.dbrestapi.core.service.impl;

import by.vanzoneway.dbrestapi.core.exception.BonusPassengerAlreadyAssosiated;
import by.vanzoneway.dbrestapi.core.model.Bonus;
import by.vanzoneway.dbrestapi.core.model.Passenger;
import by.vanzoneway.dbrestapi.core.repository.BonusRepository;
import by.vanzoneway.dbrestapi.core.repository.PassengerRepository;
import by.vanzoneway.dbrestapi.core.service.BonusPassengerService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class BonusPassengerServiceImpl implements BonusPassengerService {

    private final BonusRepository bonusRepository;
    private final PassengerRepository passengerRepository;

    @Override
    @Transactional
    public void addPassengerToBonus(Long bonusId, Long passengerId) {
        Bonus bonus = bonusRepository.findById(bonusId)
                .orElseThrow(() -> new EntityNotFoundException("Bonus not found with id: " + bonusId));

        Passenger passenger = passengerRepository.findById(passengerId)
                .orElseThrow(() -> new EntityNotFoundException("Passenger not found with id: " + passengerId));

        // Проверяем, существует ли уже связь
        if (bonus.getPassengers().contains(passenger)) {
            throw new BonusPassengerAlreadyAssosiated("Passenger " + passengerId + " is already associated with bonus " + bonusId);
        }

        // Добавляем пассажира к бонусу
        bonus.getPassengers().add(passenger);
        passenger.getBonuses().add(bonus);
        bonusRepository.save(bonus);
        log.info("Added passenger {} to bonus {}", passengerId, bonusId);
    }

    @Override
    @Transactional
    public void removePassengerFromBonus(Long bonusId, Long passengerId) {
        Bonus bonus = bonusRepository.findById(bonusId)
                .orElseThrow(() -> new EntityNotFoundException("Bonus not found with id: " + bonusId));

        Passenger passenger = passengerRepository.findById(passengerId)
                .orElseThrow(() -> new EntityNotFoundException("Passenger not found with id: " + passengerId));

        // Проверяем, существует ли связь для удаления
        if (!bonus.getPassengers().contains(passenger)) {
            throw new EntityNotFoundException("Passenger " + passengerId + " is not associated with bonus " + bonusId);
        }

        // Удаляем пассажира из бонуса
        bonus.getPassengers().remove(passenger);
        passenger.getBonuses().remove(bonus);
        bonusRepository.save(bonus);
        log.info("Removed passenger {} from bonus {}", passengerId, bonusId);
    }

    @Override
    public List<Long> getPassengerBonuses(Long passengerId) {
        Passenger passenger = passengerRepository.findById(passengerId)
                .orElseThrow(() -> new EntityNotFoundException("Passenger not found with id: " + passengerId));

        return passenger.getBonuses().stream()
                .map(Bonus::getId)
                .collect(Collectors.toList());
    }

    @Override
    public List<Long> getBonusPassengers(Long bonusId) {
        Bonus bonus = bonusRepository.findById(bonusId)
                .orElseThrow(() -> new EntityNotFoundException("Bonus not found with id: " + bonusId));

        return bonus.getPassengers().stream()
                .map(Passenger::getId)
                .collect(Collectors.toList());
    }
}
