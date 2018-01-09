package com.example.simple.springboot.project.dao;

import com.example.simple.springboot.project.entity.Sector;

import java.util.List;

public interface SectorDao {

    List<Sector> getAllSectors();
    Sector getByValue(Integer value);
    List<Sector> getSortedSectors();
}
