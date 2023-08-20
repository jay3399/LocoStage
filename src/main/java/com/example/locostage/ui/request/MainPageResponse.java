package com.example.locostage.ui.request;

import com.example.locostage.application.dto.EventDTO;
import com.example.locostage.application.dto.FestivalDTO;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
public class MainPageResponse {


    private List<EventDTO> eventDTOS;
    private List<FestivalDTO> festivalDTOS;




}
