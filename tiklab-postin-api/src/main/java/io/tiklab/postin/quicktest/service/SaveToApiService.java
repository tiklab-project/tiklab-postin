package io.tiklab.postin.quicktest.service;

import io.tiklab.toolkit.join.annotation.JoinProvider;
import io.tiklab.postin.quicktest.model.SaveToApi;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@JoinProvider(model = SaveToApi.class)
public interface SaveToApiService {

    String saveToApi(@NotNull @Valid SaveToApi saveToApi);
}
