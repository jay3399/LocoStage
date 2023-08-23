package com.example.locostage.ui.response;

import com.example.locostage.application.dto.ArtistDTO;
import com.example.locostage.application.dto.EventDTO;
import com.example.locostage.application.dto.FestivalDTO;
import com.example.locostage.domain.model.Artist;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class MainPageResponse {


    private List<EventDTO> eventDTOS;
    private List<FestivalDTO> festivalDTOS;

    private List<ArtistDTO> artistDTOS;




}
