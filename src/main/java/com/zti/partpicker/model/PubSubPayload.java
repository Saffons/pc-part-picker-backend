package com.zti.partpicker.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PubSubPayload {
    String sendToRole;

    String name;

    String manufacturer;

    double price;

    public String createMailBody(String userName) {
            return new StringBuilder().append("Hello ").append(userName)
                    .append("\n\n")
                    .append("we have added a new computer part, that might interest you. Take a look!")
                    .append("\n\n")
                    .append("Added part:")
                    .append("\n\n")
                    .append(manufacturer)
                    .append(" ")
                    .append(name)
                    .append(" with a price of ")
                    .append(price)
                    .append("\n\n")
                    .append("See you around!")
                    .toString();
        }
}
