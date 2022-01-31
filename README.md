# SSL-client
Proyecto de prueba de creacion de app web spring boot que se comunica via SSL con otra app que trabaja bajo SSL.

### Como cliente de un server ssl tenes que seguir los siguientes pasos:

1. <ins>**Extraer/Descargar el certificado ssl del server**</ins>  
> openssl s_client -connect localhost:8443 2>/dev/null > serverPublicKey.crt

> 2>/dev/null silencia los errores si los hubiera al ejecutar el comando  
>  myKey.crt lo vuelca a ese archivo al certificado extraido del server

2. <ins>**Importar el certificado extraido del server dentro cacerts del jdk (en su carpeta security) 
o uno propio que podes crear del TrustStore usando keytool**</ins>  

>Para crear un TrustStore myCacerts.jks propio ejecuta el siguiente comando  

> keytool -import -v -trustcacerts -alias serverPublicKey -file serverPublicKey.crt -keystore client-truststore.jks -keypass 123456 -storepass 123456

3. <ins>**Configurar RestTemplate**</ins>
> Como sabemos RestTemplate es un client http por ende no soporta https por si solo, hay que configurarlo ver AppConfig.class)
RestTemplate va a necesitar de httpclient (org.apache.httpcomponents) para hacer la parte de validacion de certificado del lado del client.
Por ende le vas a tener que especificar a httpclient la <ins>**ruta de tu truststore**</ins> y la <ins>**password**</ins>. 

Resultado final de tu app cliente del server ssl haya sido configurado correctamente y seguido todos los pasos :)
![This is an image](https://github.com/estebanbri/ssl-client/blob/master/resultado.png)

Una imagen vale mas que mil palabras:  
Tal como se ve en la imagen, el certificado ssl descargado del server contiene la key publica del mismo. Dicho certificado lo importamos dentro de nuestro TrustStore para que pase a ser un certificado de confianza del cliente. 
![This is an image](https://github.com/estebanbri/https-client/blob/master/truststore-detail.png)
