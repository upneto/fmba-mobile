package br.com.fiap.fmba.service;

import com.google.gson.Gson;

import java.io.BufferedOutputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

import br.com.fiap.fmba.resources.exception.ServiceException;

public abstract class AbstractService {

    public AbstractService() {
        super();
    }

    protected static final String URL_BACKEND = "http://192.168.15.18:8080/";

    protected static String token;

    public String buildParams(String url, Object... pathParams) {
        StringBuilder urlFormated = new StringBuilder(url);
        for(Object param : pathParams) {
            if(!urlFormated.equals(url)) {
                urlFormated.append("/");
            }
            urlFormated.append(param);
        }
        return urlFormated.toString();
    }

    public <T> T doGet(String url, T responseType, Object... pathParams) throws ServiceException {
        try {

            URL URL = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) URL.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-type", "application/json");
            connection.setRequestProperty("Accept", "application/json");
            connection.setRequestProperty("session-token", token);
            connection.setDoOutput(true);
            connection.setConnectTimeout(5000);

            connection.connect();

            StringBuilder json = new StringBuilder();
            Scanner scanner = new Scanner(URL.openStream());
            while (scanner.hasNext()) {
                json.append(scanner.next());
            }

            return (T) new Gson().fromJson(json.toString(), responseType.getClass());

        } catch (Exception e) {
            throw new ServiceException("Erro ao executar a operação", e);
        }
    }

    public void doPost(String url, Object body) throws ServiceException {
        this.doPost(null, body, false);
    }

    public <T> T doPost(String url, T payload, boolean getResponse) throws ServiceException {
        try {
            URL URL = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) URL.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-type", "application/json");
            connection.setRequestProperty("Accept", "application/json");
            connection.setRequestProperty("session-token", token);
            connection.setDoInput(true);
            connection.setDoOutput(getResponse);
            connection.setConnectTimeout(5000);

            connection.connect();

            String bodyJson = new Gson().toJson(payload);
            OutputStream output = new BufferedOutputStream(connection.getOutputStream());
            output.write(bodyJson.getBytes());
            output.flush();

            if (getResponse) {
                StringBuilder json = new StringBuilder();
                Scanner scanner = new Scanner(URL.openStream());
                while (scanner.hasNext()) {
                    json.append(scanner.next());
                }
                return (T) new Gson().fromJson(json.toString(), payload.getClass());
            }
            else {
                return null;
            }

        } catch (Exception e) {
            throw new ServiceException("Erro ao executar a operação", e);
        }
    }

    public void doPut(String url, Object body) throws ServiceException {
        try {
            URL URL = new URL(URL_BACKEND);
            HttpURLConnection connection = (HttpURLConnection) URL.openConnection();
            connection.setRequestMethod("PUT");
            connection.setRequestProperty("Content-type", "application/json");
            connection.setRequestProperty("Accept", "application/json");
            connection.setRequestProperty("session-token", token);
            connection.setDoInput(true);
            connection.setDoOutput(false);
            connection.setConnectTimeout(5000);

            connection.connect();

            String bodyJson = new Gson().toJson(body);
            OutputStream output = new BufferedOutputStream(connection.getOutputStream());
            output.write(bodyJson.getBytes());
            output.flush();

        } catch (Exception e) {
            throw new ServiceException("Erro ao executar a operação", e);
        }
    }

    public void doDelete(String url, Object id) throws ServiceException {
        try {

            URL URL = new URL(URL_BACKEND + "/" + id);
            HttpURLConnection connection = (HttpURLConnection) URL.openConnection();
            connection.setRequestMethod("DELETE");
            connection.setRequestProperty("Content-type", "application/json");
            connection.setRequestProperty("Accept", "application/json");
            connection.setRequestProperty("session-token", token);
            connection.setConnectTimeout(5000);

            connection.connect();

        } catch (Exception e) {
            throw new ServiceException("Erro ao executar a operação", e);
        }
    }
}
