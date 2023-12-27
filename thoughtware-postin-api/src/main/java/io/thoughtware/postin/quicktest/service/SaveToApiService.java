package io.thoughtware.postin.quicktest.service;

import io.thoughtware.toolkit.join.annotation.JoinProvider;
import io.thoughtware.postin.quicktest.model.SaveToApi;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@JoinProvider(model = SaveToApi.class)
public interface SaveToApiService {

    String saveToApi(@NotNull @Valid SaveToApi saveToApi);
}
