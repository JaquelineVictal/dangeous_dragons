package tech.java.dangeous_dragons.domain.service.battle;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tech.java.dangeous_dragons.application.mapper.BattleToBattleResponseMapper;
import tech.java.dangeous_dragons.application.payloads.response.battle.BattleResponse;
import tech.java.dangeous_dragons.common.exceptions.NotFoundException;
import tech.java.dangeous_dragons.infrastructure.persistence.entity.battle.Battle;
import tech.java.dangeous_dragons.infrastructure.persistence.repository.battle.BattleRepository;

@Service
@RequiredArgsConstructor
public class GetByIdAndIsActiveService {

    private final BattleRepository battleRepository;

    public BattleResponse execute(Long id) {
        Battle battle = battleRepository.findByIdAndIsActiveTrue(id)
                .orElseThrow(() -> new NotFoundException("battle"));
        return BattleToBattleResponseMapper.execute(battle);
    }
}
