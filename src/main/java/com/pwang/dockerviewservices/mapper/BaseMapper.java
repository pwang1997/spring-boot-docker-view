package com.pwang.dockerviewservices.mapper;

/**
 * @author Puck Wang
 * @project docker-view-services
 * @created 2/18/2023
 */
public interface BaseMapper<T,K> {

    public K convertToInstance(T object);
}
