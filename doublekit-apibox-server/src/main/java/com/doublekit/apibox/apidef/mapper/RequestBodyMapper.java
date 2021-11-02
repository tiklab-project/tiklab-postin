package com.doublekit.apibox.apidef.mapper;


import com.doublekit.apibox.apidef.entity.RequestBodyPo;
import com.doublekit.apibox.apidef.model.RequestBodyEx;
import com.doublekit.apibox.apimock.entity.FormParamMockPo;
import com.doublekit.apibox.apimock.model.FormParamMock;
import com.doublekit.beans.annotation.Mapper;
import org.springframework.web.bind.annotation.RequestBody;

@Mapper(source = RequestBodyEx.class,target = RequestBodyPo.class)
public class RequestBodyMapper {
}
