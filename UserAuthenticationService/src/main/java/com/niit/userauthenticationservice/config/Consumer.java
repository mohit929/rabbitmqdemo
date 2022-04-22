package com.niit.userauthenticationservice.config;

import com.niit.rabbitmq.domain.UserDTO;
import com.niit.userauthenticationservice.domain.User;
import com.niit.userauthenticationservice.exception.UserAlreadyExistsException;
import com.niit.userauthenticationservice.service.UserServiceImpl;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Consumer {
    @Autowired
    private UserServiceImpl userService;

    @RabbitListener(queues="user_queue")
    public void getUserDtoFromRabbitMq(UserDTO userDto) throws UserAlreadyExistsException
    {
        System.out.println(userDto.toString());
        User user=new User();
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        userService.saveUser(user);
    }
}
