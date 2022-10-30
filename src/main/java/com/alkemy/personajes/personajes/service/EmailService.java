package com.alkemy.personajes.personajes.service;

public interface EmailService {

    void sendWelcomeEmailto(String to);//es la persona a quien
    //se lo voy a enviar. El from es fijo, es de proyecto y es el que use para
    //registrarme en sendgrid
}
