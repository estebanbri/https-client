Como cliente de un server que se comunica bajo ssl.
Tenes que seguir los siguientes pasos:

1- Extraer/Descargar el certificado ssl del server
openssl s_client -connect localhost:8443 2>/dev/null > server-certificate.crt

> 2>/dev/null silencia los errores si los hubiera al ejecutar el comando
> > myKey.crt lo vuelca a ese archivo al certificado extraido del server

2- Importar el certificado extraido del server dentro cacerts del jdk (en su carpeta security) 
o uno propio que podes crear del TrustStore usando keytool
Para crear un TrustStore myCacerts.jks propio ejecuta el siguiente comando
keytool -import -v -trustcacerts -alias serverKey -file server-certificate.crt -keystore client-truststore.jks -keypass 123456 -storepass 123456

3- Configurar RestTemplate (como sabemos RestTemplate es un client http por ende no soporta https por si solo, hay que configurarlo ver AppConfig.class)
RestTemplate va a necesitar de httpclient (org.apache.httpcomponents) para hacer la parte de validacion de certificado del lado del client.
Por ende le vas a tener que especificar a httpclient la ruta de tu truststore y la password. 