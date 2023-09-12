package tech.java.dangeous_dragons.infrastructure.seed;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import tech.java.dangeous_dragons.common.enums.CharacterTypeEnum;
import tech.java.dangeous_dragons.infrastructure.persistence.entity.character.CharacterConfig;
import tech.java.dangeous_dragons.infrastructure.persistence.repository.character.CharacterConfigRepository;
import tech.java.dangeous_dragons.infrastructure.seed.entity.SeedControl;
import tech.java.dangeous_dragons.infrastructure.seed.service.SeedControlService;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class CharacterConfigSeed implements CommandLineRunner {

    private final CharacterConfigRepository characterConfigRepository;
    private final SeedControlService seedControlService;
    private final Long seedId = 1L;


    @Override
    public void run(String... args) throws Exception {


        if (seedControlService.existsById(seedId)) return;

        List<CharacterConfig> characterConfigs = new ArrayList<>();

        CharacterConfig warriorConfig = new CharacterConfig();
        warriorConfig.setHero(true);
        warriorConfig.setCharacterType(CharacterTypeEnum.WARRIOR);
        warriorConfig.setHealthPoints(20);
        warriorConfig.setAttack(7);
        warriorConfig.setDefense(5);
        warriorConfig.setAgility(6);
        warriorConfig.setQuantityDice(1);
        warriorConfig.setDiceFaces(12);
        characterConfigs.add(warriorConfig);

        CharacterConfig barbarianConfig = new CharacterConfig();
        barbarianConfig.setHero(true);
        barbarianConfig.setCharacterType(CharacterTypeEnum.BARBARIAN);
        barbarianConfig.setHealthPoints(21);
        barbarianConfig.setAttack(10);
        barbarianConfig.setDefense(2);
        barbarianConfig.setAgility(5);
        barbarianConfig.setQuantityDice(2);
        barbarianConfig.setDiceFaces(8);
        characterConfigs.add(barbarianConfig);

        CharacterConfig knightConfig = new CharacterConfig();
        knightConfig.setHero(true);
        knightConfig.setCharacterType(CharacterTypeEnum.KNIGHT);
        knightConfig.setHealthPoints(26);
        knightConfig.setAttack(6);
        knightConfig.setDefense(8);
        knightConfig.setAgility(3);
        knightConfig.setQuantityDice(2);
        knightConfig.setDiceFaces(6);
        characterConfigs.add(knightConfig);

        CharacterConfig orcConfig = new CharacterConfig();
        orcConfig.setHero(false);
        orcConfig.setCharacterType(CharacterTypeEnum.ORC);
        orcConfig.setHealthPoints(42);
        orcConfig.setAttack(7);
        orcConfig.setDefense(1);
        orcConfig.setAgility(2);
        orcConfig.setQuantityDice(3);
        orcConfig.setDiceFaces(4);
        characterConfigs.add(orcConfig);

        CharacterConfig giantConfig = new CharacterConfig();
        giantConfig.setHero(false);
        giantConfig.setCharacterType(CharacterTypeEnum.GIANT);
        giantConfig.setHealthPoints(34);
        giantConfig.setAttack(10);
        giantConfig.setDefense(4);
        giantConfig.setAgility(4);
        giantConfig.setQuantityDice(2);
        giantConfig.setDiceFaces(6);
        characterConfigs.add(giantConfig);

        CharacterConfig werewolfConfig = new CharacterConfig();
        werewolfConfig.setHero(false);
        werewolfConfig.setCharacterType(CharacterTypeEnum.WEREWOLF);
        werewolfConfig.setHealthPoints(34);
        werewolfConfig.setAttack(10);
        werewolfConfig.setDefense(4);
        werewolfConfig.setAgility(4);
        werewolfConfig.setQuantityDice(2);
        werewolfConfig.setDiceFaces(6);
        characterConfigs.add(werewolfConfig);

        characterConfigRepository.saveAll(characterConfigs);

        createdSeedControl();
    }

    private void createdSeedControl() {
        SeedControl seedControl = new SeedControl();
        seedControl.setId(seedId);
        seedControl.setSeedExecuted(true);
        seedControlService.save(seedControl);
    }
}