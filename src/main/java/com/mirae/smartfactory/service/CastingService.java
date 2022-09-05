package com.mirae.smartfactory.service;

import com.mirae.smartfactory.domain.billet.Billet;
import com.mirae.smartfactory.domain.process.Process;
import com.mirae.smartfactory.domain.process.casting.Casting;
import com.mirae.smartfactory.domain.process.casting.CastingData;
import com.mirae.smartfactory.domain.process.casting.CastingPreparation;
import com.mirae.smartfactory.domain.process.casting.CastingTemperature;
import com.mirae.smartfactory.dto.process.casting.CastingDto;
import com.mirae.smartfactory.dto.process.casting.CastingListDto;
import com.mirae.smartfactory.dto.process.casting.CastingQueryDto;
import com.mirae.smartfactory.repository.CastingRepository;
import com.mirae.smartfactory.repository.MemberRepository;
import com.mirae.smartfactory.repository.ProcessRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CastingService {

    private final CastingRepository castingRepository;
    private final MemberRepository memberRepository;
    private final ProcessRepository processRepository;

    @Transactional
    public Long saveCasting(CastingDto castingDto) {
        Process process = processRepository.findByDateAndDailyProcessId(castingDto.getProcess().getDate(), castingDto.getProcess().getDailyProcessId());

        CastingPreparation castingPreparation = CastingPreparation.createCastingPreparationWithDto(castingDto.getCastingPreparation());
        CastingData castingData = CastingData.createCastingDataWithDto(castingDto.getCastingData());
        CastingTemperature castingTemperature = CastingTemperature.createCastingTemperatureWithDto(castingDto.getCastingTemperature());
        Billet billet = Billet.createBilletWithDto(castingDto.getBillet());

        Casting casting = Casting.createCastingWithDto(castingDto, process, castingPreparation, castingData, castingTemperature, billet);
        castingRepository.save(casting);

        return casting.getCastingId();
    }

    @Transactional
    public Long updateCasting(Long castingId, CastingDto castingDto) {
        Process process = processRepository.findByDateAndDailyProcessId(castingDto.getProcess().getDate(), castingDto.getProcess().getDailyProcessId());

        CastingPreparation castingPreparation = CastingPreparation.createCastingPreparationWithDto(castingDto.getCastingPreparation());
        CastingData castingData = CastingData.createCastingDataWithDto(castingDto.getCastingData());
        CastingTemperature castingTemperature = CastingTemperature.createCastingTemperatureWithDto(castingDto.getCastingTemperature());
        Billet billet = Billet.createBilletWithDto(castingDto.getBillet());

        Casting casting = Casting.createCastingWithDtoAndId(castingId, castingDto, process, castingPreparation, castingData, castingTemperature, billet);

        castingRepository.update(casting);

        return casting.getCastingId();
    }

    public CastingListDto findCastings(LocalDate date) {
        List<Casting> castings = castingRepository.findListByDate(date);

        List<CastingQueryDto> castingDtos = castings.stream()
                .map(c -> new CastingQueryDto(c, c.getProcess().getDailyProcessId()))
                .collect(Collectors.toList());

        CastingListDto result = new CastingListDto(castingDtos);

        return result;
    }
}
