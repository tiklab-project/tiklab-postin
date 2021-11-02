package com.doublekit.apibox.node.mapper;

import com.doublekit.apibox.node.entity.NodeEntity;
import com.doublekit.apibox.node.model.Node;
import com.doublekit.beans.annotation.Mapper;

@Mapper(source = Node.class,target = NodeEntity.class)
public class NodeMapper {
}
