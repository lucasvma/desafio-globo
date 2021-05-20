package com.globo.challenge.service;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.globo.challenge.dto.CepDto;
import com.globo.challenge.dto.UserDto;
import com.globo.challenge.model.User;
import com.globo.challenge.repository.UserRepository;
import com.globo.challenge.util.CpfCnpjUtil;
import com.globo.challenge.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.Period;

@Service
public class UserService {

    private static String VIACEP_URL = "https://viacep.com.br/ws/";

    @Autowired
    UserRepository repository;

    public void create(UserDto userDto) throws Exception {
        CepDto viaCepResponse = getViaCepData(userDto.getCep());

        String document = StringUtil.onlyNumbers(userDto.getDocument());
        String name = StringUtil.removeSpecialCharacters(userDto.getName());

        if (CpfCnpjUtil.isCpf(document) && CpfCnpjUtil.isValidCPF(document)) {
            document = CpfCnpjUtil.formatCpf(document);
        } else if (CpfCnpjUtil.isCnpj(document) && CpfCnpjUtil.isValidCNPJ(document)) {
            document = CpfCnpjUtil.formatCnpj(document);
        } else {
            throw new Exception("Invalid document, please try again!");
        }

        User responseUser = repository.findByDocumentOrName(document, name);

        if (responseUser != null) {
            throw new Exception("User already exists, please try another name or document!");
        }

        User user = new User();
        user.setName(name);
        user.setDocument(document);
        user.setCity(viaCepResponse.getLocalidade());
        user.setState(viaCepResponse.getUf());
        user.setNeighborhood(viaCepResponse.getBairro());
        user.setAge(checkAge(userDto.getBirthday()));

        repository.save(user);
    }

    public CepDto getViaCepData(String cep) throws Exception {
        String url = VIACEP_URL + cep + "/json/";
        CepDto response = new RestTemplate().getForObject(url, CepDto.class);

        if (response.isErro()) {
            throw new Exception("Invalid CEP, please try again!");
        }

        return response;
    }

    public int checkAge(LocalDate birthday) {
        return Period.between(birthday, LocalDate.now()).getYears();
    }
}
