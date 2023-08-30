package com.example.locostage.ui.response;

import com.example.locostage.application.dto.ArtistDTO;
import com.example.locostage.application.dto.EventListDTO;
import com.example.locostage.application.dto.FestivalDTO;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class MainPageResponse {


    private List<EventListDTO> eventListDTOS;
    private List<FestivalDTO> festivalDTOS;

    private List<ArtistDTO> artistDTOS;




}
