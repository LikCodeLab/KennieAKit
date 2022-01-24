package com.kennie.http.entity;


public interface IApiData<T> {

    String getCode();

    String getMsg();

    T getData();

    boolean isSuccess();

}
