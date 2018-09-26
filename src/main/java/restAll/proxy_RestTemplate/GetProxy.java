package restAll.proxy_RestTemplate;

import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.net.InetSocketAddress;
import java.net.Proxy;

public class GetProxy {

    public static RestTemplate restTemplate() {
        SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();

        Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("10.4.197.14", 8080));
        requestFactory.setProxy(proxy);

        return new RestTemplate(requestFactory);
    }
}
