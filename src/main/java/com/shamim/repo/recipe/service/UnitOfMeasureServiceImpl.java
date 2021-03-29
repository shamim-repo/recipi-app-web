package com.shamim.repo.recipe.service;

import com.shamim.repo.recipe.commands.UnitOfMeasureCommand;
import com.shamim.repo.recipe.converters.UnitOfMeasureCommandToUnitOfMeasure;
import com.shamim.repo.recipe.converters.UnitOfMeasureToUnitOfMeasureCommand;
import com.shamim.repo.recipe.domain.UnitOfMeasure;
import com.shamim.repo.recipe.repository.UnitOfMeasureRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class UnitOfMeasureServiceImpl implements UnitOfMeasureService{

    private final UnitOfMeasureRepository unitOfMeasureRepository;
    private final UnitOfMeasureToUnitOfMeasureCommand toUnitOfMeasureCommand;
    private final UnitOfMeasureCommandToUnitOfMeasure toUnitOfMeasure;

    public UnitOfMeasureServiceImpl(UnitOfMeasureRepository unitOfMeasureRepository, UnitOfMeasureToUnitOfMeasureCommand toUnitOfMeasureCommand, UnitOfMeasureCommandToUnitOfMeasure toUnitOfMeasure) {
        this.unitOfMeasureRepository = unitOfMeasureRepository;
        this.toUnitOfMeasureCommand = toUnitOfMeasureCommand;
        this.toUnitOfMeasure = toUnitOfMeasure;
    }


    @Override
    public Set<UnitOfMeasureCommand> getUnitOfMeasures() {
        return StreamSupport.stream(unitOfMeasureRepository.findAll()
                .spliterator(),false)
                .map(toUnitOfMeasureCommand::convert)
                .collect(Collectors.toSet());
    }
}
