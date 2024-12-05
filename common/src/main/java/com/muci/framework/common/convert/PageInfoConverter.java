package com.muci.framework.common.convert;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.muci.framework.common.entity.PageInfo;

@Mapper
public interface PageInfoConverter {
    PageInfoConverter INSTANCE = Mappers.getMapper(PageInfoConverter.class);
    PageInfo convert(PageInfo pageInfo);
}
