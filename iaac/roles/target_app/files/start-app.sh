#!/bin/sh

#nohup \
  java \
    -Xms512m -Xmx1G \
    -XX:+IgnoreUnrecognizedVMOptions -XX:+UnlockDiagnosticVMOptions -XX:+UnlockExperimentalVMOptions \
    -XX:ReservedCodeCacheSize=200m \
    -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=heapdump.hprof \
    -Dderby.stream.error.file=log/derby.log \
    -Dcom.sun.management.jmxremote=true -Dcom.sun.management.jmxremote.ssl=false -Dcom.sun.management.jmxremote.authenticate=false \
    -Djava.rmi.server.hostname=127.0.0.1 -Dcom.sun.management.jmxremote.port=9999 -Dcom.sun.management.jmxremote.rmi.port=9999 \
    -jar dbo-1.0-SNAPSHOT.jar \
      --spring.profiles.active=qa \
      --debug=false \
      --logging.level.root=ERROR \
      --logging.level.org.springframework=ERROR \
      --logging.level.org.hibernate=ERROR \
      --logging.level.org.hibernate.stat=ERROR \
      --logging.level.org.hibernate.SQL=ERROR \
      --spring.jpa.show-sql=false \
#> /dev/null 2>&1 &
