package com.data.collector.CollectTool;

import java.util.List;

public class SalesDtoList {
    private List<SaleDto> saleDtoList;

    public SalesDtoList(List<SaleDto> saleDtoList) {
        this.saleDtoList = saleDtoList;
    }

    public List<SaleDto> getSaleDtoList() {
        return saleDtoList;
    }

    public void setSaleDtoList(List<SaleDto> saleDtoList) {
        this.saleDtoList = saleDtoList;
    }
}
