package com.data.collector.web;

import com.data.collector.dto.StatisticDto;
import com.data.collector.service.StatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class StatisticController {

    @Autowired
    StatisticService statisticService;

    @RequestMapping("/stat")
    public List<StatisticDto> showAllStat() {
        return statisticService.getStatisticForAllPublishers();
    }

    @RequestMapping("/stat/{publisherId}")
    public List<StatisticDto> showStatForPublisher(@PathVariable("publisherId") int id) {
        return statisticService.getStatisticForOnePublisher(id);
    }

}
