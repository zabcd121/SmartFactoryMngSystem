package com.mirae.smartfactory.service;

import com.mirae.smartfactory.domain.billet.Billet;
import com.mirae.smartfactory.domain.process.Process;
import com.mirae.smartfactory.domain.process.casting.Casting;
import com.mirae.smartfactory.domain.process.casting.CastingData;
import com.mirae.smartfactory.domain.process.casting.CastingPreparation;
import com.mirae.smartfactory.domain.process.casting.CastingTemperature;
import com.mirae.smartfactory.dto.process.casting.CastingDto;
import com.mirae.smartfactory.dto.process.casting.CastingListDto;
import com.mirae.smartfactory.repository.CastingRepository;
import com.mirae.smartfactory.repository.MemberRepository;
import com.mirae.smartfactory.repository.ProcessRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CastingService {

    private final CastingRepository castingRepository;
    private final MemberRepository memberRepository;
    private final ProcessRepository processRepository;

    @Transactional
    public void saveCasting(CastingDto castingDto) {
        System.out.println(castingDto.getTappingEndTime());
        Process process = processRepository.findByDateAndDailyProcessId(castingDto.getProcess().getDate(), castingDto.getProcess().getDailyProcessId());
//        Process process = Process.createProcessWithDto(castingDto.getProcess(), memberRepository.findById(castingDto.getProcess().getMemberId()));
        CastingPreparation castingPreparation = CastingPreparation.createCastingPreparationWithDto(castingDto.getCastingPreparation());
        CastingData castingData = CastingData.createCastingDataWithDto(castingDto.getCastingData());
        CastingTemperature castingTemperature = CastingTemperature.createCastingTemperatureWithDto(castingDto.getCastingTemperature());
        Billet billet = Billet.createBilletWithDto(castingDto.getBillet());

        Casting casting = Casting.createCastingWithDto(castingDto, process, castingPreparation, castingData, castingTemperature, billet);

        castingRepository.save(casting);
    }

    public CastingListDto findCastings(LocalDate date) {
        List<Casting> castings = castingRepository.findListByDate(date);

        List<CastingDto> castingDtos = castings.stream()
                .map(c -> new CastingDto(c, c.getProcess().getDailyProcessId()))
                .collect(Collectors.toList());

        CastingListDto result = new CastingListDto(castingDtos);

        return result;
    }
}
