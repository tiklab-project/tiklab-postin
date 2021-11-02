package com.doublekit.apibox.apimock.mapper;

import com.doublekit.apibox.apidef.entity.AfterScriptPo;
import com.doublekit.apibox.apidef.model.AfterScript;
import com.doublekit.apibox.apimock.entity.RawResponseMockPo;
import com.doublekit.apibox.apimock.model.RawResponseMock;
import com.doublekit.beans.annotation.Mapper;

@Mapper(source = RawResponseMock.class,target = RawResponseMockPo.class)
public class RawResponseMockMapper {
}
