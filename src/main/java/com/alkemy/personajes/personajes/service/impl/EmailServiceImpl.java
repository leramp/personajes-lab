package com.alkemy.personajes.personajes.service.impl;

import com.alkemy.personajes.personajes.service.EmailService;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.io.IOException;


@Service
public class EmailServiceImpl implements EmailService {
    @Autowired
    private Environment env;//vamos a poner nuestro apiKey porque como tiene que ser seguro no tiene que estar en el código

    @Value("${alkemy.personajes.email.sender}")
    private String emailSender;//es el front, desde donde se envía el mail, y para tenerlo hardocdeado desde el código, lo pusimos
    //en una de las properties, con el @Value es la forma  de poner el valor de esta property y en vez de obtener un valor fijo
    //obtengo un valor con $ y {} cual es el nombre de la property
    @Value("${alkemy.personajes.email.enabled}")
    private boolean enabled;// si esta en false, corto la ejecucion. Esto es para habilitar o desabilitar el envío de mails

    public void sendWelcomeEmailto(String to){
        if(!enabled){
            return;
        }
        String apiKey = env.getProperty("EMAIL_API_KEY");//lo primero es obtener el apikey para poder ejecutar
        //el envio del correo. El env me da el environment de java y le pido las properties por nombre
        //Ese EMAIL_API_KEY lo setteo desde edit Configuration en environmet variables y le stteamos la clave valor, que es
        //la clave que nos dio sendgrid cuando generamos la clave

        Email fromEmail = new Email(emailSender);//otra cosa que necesitamos es un obbeto de tipo Email tanto para el front como
        //para el to. esta clase y las otras siguientes salen de la dependencia de sendgrid. al front le pasamos lo que
        //tenemos configurado (la direccion de email)
        Email toEmail = new Email(to);//esto es lo mismo que lo anterior con la diferencia que el to se lo pasamos por parámetro
        Content content = new Content(//aca inicializamos el cuerpo y el mensaje. Eso crea una clase de tipo Content
                "text/plain",
                "Bienvenido/a a Alkemy Personajes"
        );
        String subject = "Alkemy Personajes";//luego defino al sujeto del envío de mail

        Mail mail = new Mail(fromEmail, subject, toEmail, content);//con todos esos atributos creo el mail
        SendGrid sg = new SendGrid(apiKey);//una vez hecho eso creo un objeto de tipo sendgrid y le paso el apiky. Con
        //el apiKey tengo relacionado la cuanta a la que pertenece y los permisos
        Request request = new Request();//genero un nuevo request (tambien de sendgrid) y le seteamos el metodo post
        try{
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");//tambien le decimos a que endpoint le vamos a pegar
            request.setBody(mail.build());//le setteamos el body, le seteamos la construccion del amil con los datos q le seteamos antes
            Response response = sg.api(request);//luego para el response le pasmamos el request y aca se produce la ejecución.

            System.out.println(response.getStatusCode());//aca logueo aparte es el response.getStatusCode, etc, etc
            System.out.println(response.getBody());
            System.out.println(response.getHeaders());
        }catch(IOException ex){
            System.out.println("Error trying to send the email");

            //de que forma utilizamos este codigo para enviar correo? lo vemos en el controller de autenticacion al momento de hacer el singup
        }
    }
}
