To create datasource which required to run this example:

    xa-data-source add \
     --name=MyDS \
     --driver-name=h2 \
     --jndi-name=java:jboss/datasources/MyDS \
     --user-name=sa \
     --password=sa \
     --xa-datasource-properties={ \
      "URL" => "jdbc:h2:/tmp/localtxtest;AUTO_SERVER=TRUE"}

After deploy, go http://localhost:8080/jberet-support-jdbc-example-1.0-SNAPSHOT/
