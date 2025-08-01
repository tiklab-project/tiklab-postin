package io.tiklab.postin.autotest.http.unit.cases.service;

import io.tiklab.postin.autotest.http.unit.cases.model.SaveToCase;
import io.tiklab.toolkit.join.annotation.JoinProvider;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@JoinProvider(model = SaveToCase.class)
public interface SaveToCaseService {

    String saveToCase(@NotNull @Valid SaveToCase saveToCase);
}
