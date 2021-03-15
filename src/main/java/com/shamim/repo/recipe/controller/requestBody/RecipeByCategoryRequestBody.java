package com.shamim.repo.recipe.controller.requestBody;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public class RecipeByCategoryRequestBody {
    private List<Long> ids;
    public RecipeByCategoryRequestBody() {
    }
    public RecipeByCategoryRequestBody(List<Long> ids) {
        this.ids = ids;
    }

    public List<Long> getIds() {
        return ids;
    }

    public void setIds(List<Long> ids) {
        this.ids = ids;
    }
}
