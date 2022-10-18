package br.com.fiap.fmba.service;

import com.google.gson.Gson;

import org.json.JSONObject;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Serializable;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

import br.com.fiap.fmba.resources.exception.ServiceException;
import retrofit2.Retrofit;

public abstract class AbstractService implements Serializable {

    public AbstractService() {
        super();
    }

    protected static String token;

    public <S> S doGet(String url, Class<S> responseClass, Object... pathParams) throws ServiceException {

        S saida = null;
        HttpURLConnection connection = null;

        try {
            URL URL = new URL(url);
            connection = (HttpURLConnection) URL.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-type", "application/json");
            connection.setRequestProperty("Accept", "application/json");
            connection.setRequestProperty("session-token", token);
            connection.setDoOutput(true);
            connection.setConnectTimeout(5000);

            connection.connect();

            int statusCode = connection.getResponseCode();
            if (statusCode == HttpURLConnection.HTTP_OK) {
                StringBuilder json = new StringBuilder();
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line;
                while ((line = reader.readLine()) != null) {
                    json.append(line);
                }
                saida = (S) new Gson().fromJson(json.toString(), responseClass);
            }
            else {
                throw new ServiceException("Não foi possível executar a operação!");
            }

        } catch (Exception e) {
            throw new ServiceException("Erro ao executar a operação", e);
        }
        finally {
            connection.disconnect();
        }
        return saida;
    }

    public void doPost(String url, Object body) throws ServiceException {
        this.doPost(null, body);
    }

    public <E, S> S doPost(String url, E request, Class<S> responseClass) throws ServiceException {

        S saida = null;
        HttpURLConnection connection = null;

        try {
            URL URL = new URL(url);
            connection = (HttpURLConnection) URL.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-type", "application/json");
            connection.setRequestProperty("Accept", "application/json");
            connection.setRequestProperty("session-token", token);
            connection.setDoInput(true);
            connection.setDoOutput(true);
            connection.setConnectTimeout(5000);
            connection.connect();

            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(connection.getOutputStream()));
            writer.write(new Gson().toJson(request));
            writer.flush();
            writer.close();

            int statusCode = connection.getResponseCode();
            if (statusCode == HttpURLConnection.HTTP_OK) {
                StringBuilder json = new StringBuilder();
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line;
                while ((line = reader.readLine()) != null) {
                    json.append(line);
                }
                saida = (S) new Gson().fromJson(json.toString(), responseClass);
            }
            else {
                throw new ServiceException("Não foi possível executar a operação!");
            }
        }
        catch (Exception e) {
            throw new ServiceException("Erro ao executar a operação", e);
        }
        finally {
            connection.disconnect();
        }
        return saida;
    }

    public void doPut(String url, Object request) throws ServiceException {

        HttpURLConnection connection = null;

        try {
            URL URL = new URL(url);
            connection = (HttpURLConnection) URL.openConnection();
            connection.setRequestMethod("PUT");
            connection.setRequestProperty("Content-type", "application/json");
            connection.setRequestProperty("Accept", "application/json");
            connection.setRequestProperty("session-token", token);
            connection.setDoInput(true);
            connection.setDoOutput(false);
            connection.setConnectTimeout(5000);
            connection.connect();

            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(connection.getOutputStream()));
            writer.write(new Gson().toJson(request));
            writer.flush();
            writer.close();

            int statusCode = connection.getResponseCode();
            if (statusCode != HttpURLConnection.HTTP_OK) {
                throw new ServiceException("Não foi possível executar a operação!");
            }
        }
        catch (Exception e) {
            throw new ServiceException("Erro ao executar a operação", e);
        }
        finally {
            connection.disconnect();
        }
    }

    public void doDelete(String url, Object id) throws ServiceException {

        HttpURLConnection connection = null;

        try {
            URL URL = new URL(url + "/" + id);
            connection = (HttpURLConnection) URL.openConnection();
            connection.setRequestMethod("DELETE");
            connection.setRequestProperty("Content-type", "application/json");
            connection.setRequestProperty("Accept", "application/json");
            connection.setRequestProperty("session-token", token);
            connection.setConnectTimeout(5000);
            connection.connect();

            int statusCode = connection.getResponseCode();
            if (statusCode != HttpURLConnection.HTTP_OK) {
                throw new ServiceException("Não foi possível executar a operação!");
            }

        }
        catch (Exception e) {
            throw new ServiceException("Erro ao executar a operação", e);
        }
        finally {
            connection.disconnect();
        }
    }
}
