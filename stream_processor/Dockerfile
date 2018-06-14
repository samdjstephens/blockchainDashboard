FROM maven:3

COPY . /root/stream_processor
WORKDIR /root/stream_processor

RUN mvn clean package

CMD java -cp "target/stream_processor-1.0-SNAPSHOT.jar:target/lib/*" com.samdjstephens.StarterPipeline