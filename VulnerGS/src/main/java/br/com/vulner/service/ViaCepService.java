package br.com.vulner.service;

import java.io.IOException;

import br.com.vulner.beans.Endereco;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import com.google.gson.Gson;

public class ViaCepService {

    public Endereco getEndereco(String cep) throws ClientProtocolException, IOException {

        Endereco endereco = null;

        //request
        HttpGet request = new HttpGet("https://viacep.com.br/ws/"+cep+"/json/");

        //client
        CloseableHttpClient httpClient=
                HttpClientBuilder.create().disableRedirectHandling().build();

        //response
        CloseableHttpResponse response= httpClient.execute(request);


        //entity
        HttpEntity entity= response.getEntity();


        if(entity != null) {

            String result= EntityUtils.toString(entity);

            Gson gson = new Gson();

            endereco = gson.fromJson(result, Endereco.class);
        }


        return endereco;
    }

}