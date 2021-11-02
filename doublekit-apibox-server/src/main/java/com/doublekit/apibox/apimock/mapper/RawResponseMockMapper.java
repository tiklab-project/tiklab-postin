package com.doublekit.apibox.apimock.mapper;

import com.doublekit.apibox.apidef.entity.AfterScriptEntity;
import com.doublekit.apibox.apidef.model.AfterScript;
import com.doublekit.apibox.apimock.entity.RawResponseMockEntity;
import com.doublekit.apibox.apimock.model.RawResponseMock;
import com.doublekit.beans.annotation.Mapper;

@Mapper(source = RawResponseMock.class,target = RawResponseMockEntity.class)
public class RawResponseMockMapper {
}
