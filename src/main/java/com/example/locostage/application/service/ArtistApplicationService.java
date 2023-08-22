package com.example.locostage.application.service;

import com.example.locostage.application.dto.ArtistDTO;
import com.example.locostage.application.dto.mapper.ArtistMapper;
import com.example.locostage.application.dto.mapper.EventMapper;
import com.example.locostage.domain.model.Artist;
import com.example.locostage.domain.service.ArtistService;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ArtistApplicationService {

    private final ArtistService artistService;


    public List<ArtistDTO> getAllArtists(String country ) {

        return ArtistMapper.toDTOs(artistService.getAll(country));
    }


}


// 아티스트 정보 및 공연 일정 제공, 아티스트 팔로우 기능 제공.