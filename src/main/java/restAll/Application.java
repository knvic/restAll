package restAll;


import com.google.gson.Gson;

import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import restAll.jsonplaceholder_user.User;
import restAll.metadata_1C.Metadata1C;
import restAll.proxy_RestTemplate.GetProxy;


import java.nio.charset.Charset;


public class Application {



    public static void main(String args[]) throws IllegalAccessException, NoSuchFieldException {


        //RestTemplate ДЛЯ ВНУТРЕННИХ АДРЕСОВ
        RestTemplate restTemplate = new RestTemplate();

        //!!!RestTemplate ЧЕРЕЗ PROXY  ДЛЯ ВНЕШНИХ АДРЕСОВ. ИСПОЛЬЗУЕТ СТОРОННИЙ МЕТОД
        GetProxy getProxy=new GetProxy();
        RestTemplate restTemplateProxy=getProxy.restTemplate();


        //Преобразование строки  JSON в класс через Gson
        String  jsonString= restTemplate.getForObject("http://localhost/test/odata/standard.odata/Catalog_Клиенты?$format=json", String.class);
        Gson gson = new Gson();
        Metadata1C model = gson.fromJson(jsonString, Metadata1C.class);

        //Приверы работы отбора в запросе  :
        String qqq = restTemplate.getForObject("http://localhost/test/odata/standard.odata/Catalog_Клиенты?$format=json&$filter=substringof('000000003',Code)", String.class);
        String qqq1 = restTemplate.getForObject("http://localhost/test/odata/standard.odata/Catalog_Клиенты?$format=json&$filter=substringof('Роман',Description)", String.class);

        //System.out.println(qqq1);

        // Маппинг RestTemplate  СРАЗУ В КЛАСС JAVA!!!!!!!!!!!!!!
       ResponseEntity<Metadata1C> r =   restTemplate.getForEntity("http://localhost/test/odata/standard.odata/Catalog_Клиенты?$filter=substringof('Роман',Description) eq true", Metadata1C.class);
       Metadata1C r1 =   restTemplate.getForObject("http://localhost/test/odata/standard.odata/Catalog_Клиенты?$format=json&$filter=substringof('000000003',Code)", Metadata1C.class);
        Metadata1C r2 =   restTemplate.getForObject("http://localhost/test/odata/standard.odata/Catalog_Клиенты?$format=json", Metadata1C.class);

        // Используем RestTemplate в который мы ранее внедрили proxy
        User[] user =   restTemplateProxy.getForObject("http://jsonplaceholder.typicode.com/users?$format=json", User[].class);
       // System.out.println();

        //Пример с использованием LIKE в запросе метаданных
        String like=restTemplate.getForObject("http://localhost/test/odata/standard.odata/Catalog_Клиенты?$format=json&$filter=like(Code,'000000002')", String.class);
        System.out.println(like);
        String like1=restTemplate.getForObject("http://localhost/test/odata/standard.odata/Catalog_Клиенты?$format=json&$filter=like(Description,'Га%')", String.class);
       // System.out.println(like1);


        //Обращаемся к собственной обработке
        //УСТАНАВЛИВАЕМ КОДИНОВКУ!!!! ЧТОБЫ ЧИТАЛАСЬ КИРИЛИЦА
        restTemplate.getMessageConverters().add(0, new StringHttpMessageConverter(Charset.forName("windows-1251")));

        //String ddd1 = restTemplate.getForObject("http://localhost/test/hs/our-services/list", String.class);

       // System.out.println(ddd1);
        String ddd2 = restTemplate.getForObject("http://localhost/test/hs/our-services/trstparametr/list/testmetod", String.class);

        System.out.println(ddd2);

    }

}