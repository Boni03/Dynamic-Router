package com.pramati.bean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component("inputProcessorBean")
public class InputProcessorBean {

	private Logger log = LoggerFactory.getLogger(InputProcessorBean.class);

    /**
     * This method processes incoming messages and return responses.
     *
     * @param message a message coming from a human user in a chat
     * @return the reply of the bot.
     * @throws Exception if the input is null.
     */
    public String process(String message) throws Exception {
        if (message == null) {
            throw new Exception("Please send some message");
        }

        log.info("Received message: {}", message);

        return "You just said \"" + message.replace("\"", "-") + "\" !";
    }
}
