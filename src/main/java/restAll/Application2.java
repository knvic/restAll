package restAll;


import org.springframework.http.*;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import restAll.jsonplaceholder_user.User;
import restAll.proxy_RestTemplate.GetProxy;


import java.nio.charset.Charset;
import java.util.Arrays;


public class Application2 {


    static final String  URL_EMPLOYEES= "http://localhost/test/hs/our-services/trstparametr/list/testmetod";
    static final String  URL_USER= "http://jsonplaceholder.typicode.com/users?$format=json";
    public static void main(String args[]) throws IllegalAccessException, NoSuchFieldException {


        //RestTemplate ДЛЯ ВНУТРЕННИХ АДРЕСОВ
        RestTemplate restTemplate = new RestTemplate();

        //!!!RestTemplate ЧЕРЕЗ PROXY  ДЛЯ ВНЕШНИХ АДРЕСОВ. ИСПОЛЬЗУЕТ СТОРОННИЙ МЕТОД
        GetProxy getProxy=new GetProxy();
        RestTemplate restTemplateProxy=getProxy.restTemplate();
    ///////////////////////////////////////////


        //Обращаемся к собственной обработке
        //УСТАНАВЛИВАЕМ КОДИНОВКУ!!!! ЧТОБЫ ЧИТАЛАСЬ КИРИЛИЦА
        restTemplate.getMessageConverters().add(0, new StringHttpMessageConverter(Charset.forName("windows-1251")));



        HttpHeaders headers = new HttpHeaders();

        headers.setAccept(Arrays.asList(new MediaType[] { MediaType.APPLICATION_JSON }));
        // Request to return JSON format
        headers.setContentType(MediaType.APPLICATION_JSON);
        //headers.setContentType(MediaType.APPLICATION_XML);
        headers.set("my_other_key", "my_other_value");

        // HttpEntity<String>: To get result as String.
       // HttpEntity<String> entity = new HttpEntity<String>(headers);
        HttpEntity<User[]> entity = new HttpEntity<User[]>(headers);


        ResponseEntity<User[]> response = restTemplateProxy.exchange(URL_USER,HttpMethod.GET, entity, User[].class);

        HttpStatus statusCode = response.getStatusCode();
        if(response.getStatusCode()==HttpStatus.OK){
            System.out.println("Response Satus Code: " + HttpStatus.OK.getReasonPhrase());
        }
        System.out.println("Response Satus Code: " + statusCode);

        // Status Code: 200
        if (statusCode == HttpStatus.OK) {
            // Response Body Data
            User[] list = response.getBody();

            if (list != null) {
                for (User e : list) {
                    System.out.println("Employee: " + e.getName() + " - " + e.getId());
                }
            }
        }



        /*ResponseEntity<String> response = restTemplate.exchange(URL_EMPLOYEES,  HttpMethod.GET, entity, String.class);

        String result = response.getBody();

        System.out.println();
        System.out.println(result);*/



    }

}