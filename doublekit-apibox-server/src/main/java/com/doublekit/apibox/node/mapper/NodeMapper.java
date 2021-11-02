package com.doublekit.apibox.node.mapper;

import com.doublekit.apibox.node.entity.NodePo;
import com.doublekit.apibox.node.model.Node;
import com.doublekit.beans.annotation.Mapper;

@Mapper(source = Node.class,target = NodePo.class)
public class NodeMapper {
}
