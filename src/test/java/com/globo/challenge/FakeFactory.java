package com.globo.challenge;

import com.globo.challenge.dto.CepDto;
import com.globo.challenge.dto.UserDto;

import java.time.LocalDate;

public class FakeFactory {

    public static CepDto cep() {
        CepDto response = new CepDto();
        response.setCep("12630-000");
        response.setLogradouro("");
        response.setComplemento("");
        response.setBairro("");
        response.setLocalidade("Cachoeira Paulista");
        response.setUf("SP");
        response.setIbge("3508603");
        response.setGia("2355");
        response.setDdd("12");
        response.setSiafi("6273");
        return response;
    }

    public static UserDto userDto() {
        UserDto response = new UserDto();
        response.setName("Lucas Ventura");
        response.setDocument("474-534-598-42");
        response.setCep("12630-000");
        response.setBirthday(LocalDate.of(1998, 7, 21));
        return response;
    }
}
