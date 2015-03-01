To create datasource which required to run this example:

    xa-data-source add \
     --name=MyTxDS \
     --driver-name=h2 \
     --jndi-name=java:jboss/datasources/MyTxDS \
     --user-name=sa \
     --password=sa \
     --xa-datasource-properties={ \
      "URL" => "jdbc:h2:/tmp/txtest;AUTO_SERVER=TRUE"}

    data-source add \
     --name=MyNonTxDS \
     --driver-name=h2 \
     --jndi-name=java:jboss/datasources/MyNonTxDS \
     --user-name=sa \
     --password=sa \
     --jta=false \
     --connection-url=jdbc:h2:/tmp/txtest;AUTO_SERVER=TRUE

After deploy, go http://localhost:8080/jberet-support-jdbc-example-1.0-SNAPSHOT/
