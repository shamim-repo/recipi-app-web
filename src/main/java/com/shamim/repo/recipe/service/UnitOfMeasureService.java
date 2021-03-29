package com.shamim.repo.recipe.service;

import com.shamim.repo.recipe.commands.UnitOfMeasureCommand;
import java.util.Set;

public interface UnitOfMeasureService {
    Set<UnitOfMeasureCommand> getUnitOfMeasures();
}
