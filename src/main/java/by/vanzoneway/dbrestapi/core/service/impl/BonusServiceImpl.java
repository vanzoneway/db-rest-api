package by.vanzoneway.dbrestapi.core.service.impl;

import by.vanzoneway.dbrestapi.api.dto.bonus.request.BonusCreateRequest;
import by.vanzoneway.dbrestapi.api.dto.bonus.request.BonusUpdateRequest;
import by.vanzoneway.dbrestapi.api.dto.bonus.response.BonusResponse;
import by.vanzoneway.dbrestapi.core.mapper.BonusMapper;
import by.vanzoneway.dbrestapi.core.model.Bonus;
import by.vanzoneway.dbrestapi.core.repository.BonusRepository;
import by.vanzoneway.dbrestapi.core.service.BonusService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class BonusServiceImpl implements BonusService {

    private final BonusRepository bonusRepository;
    private final BonusMapper bonusMapper;

    @Override
    @Transactional
    public BonusResponse createBonus(BonusCreateRequest request) {
        Bonus bonus = bonusMapper.toEntity(request);
        Bonus savedBonus = bonusRepository.save(bonus);
        log.info("Created bonus with id: {}", savedBonus.getId());
        return bonusMapper.toResponse(savedBonus);
    }

    @Override
    @Transactional
    public BonusResponse updateBonus(BonusUpdateRequest request) {
        Bonus bonus = bonusRepository.findById(request.id())
                .orElseThrow(() -> new EntityNotFoundException("Bonus not found with id: " + request.id()));

        bonusMapper.updateEntity(request, bonus);
        Bonus updatedBonus = bonusRepository.save(bonus);
        log.info("Updated bonus with id: {}", updatedBonus.getId());

        return bonusMapper.toResponse(updatedBonus);
    }

    @Override
    @Transactional
    public void deleteBonus(Long bonusId) {
        if (!bonusRepository.existsById(bonusId)) {
            throw new EntityNotFoundException("Bonus not found with id: " + bonusId);
        }
        bonusRepository.deleteById(bonusId);
        log.info("Deleted bonus with id: {}", bonusId);
    }

    @Override
    public List<BonusResponse> findAllBonuses() {
        List<Bonus> bonuses = bonusRepository.findAll();
        return bonusMapper.toResponseList(bonuses);
    }

    @Override
    public BonusResponse findBonus(Long bonusId) {
        return bonusMapper.toResponse(bonusRepository.findById(bonusId)
                .orElseThrow(() -> new EntityNotFoundException("Bonus not found with id: " + bonusId)));
    }
}
