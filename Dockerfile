FROM ubuntu:22.04

# Install dependencies
RUN apt-get update && \
    apt-get install -y curl unzip wget gnupg2 software-properties-common sudo

# Install Java 8
RUN add-apt-repository ppa:openjdk-r/ppa && \
    apt-get update && \
    apt-get install -y openjdk-8-jdk

# Install Gradle
ENV GRADLE_VERSION=7.4
RUN wget -q https://services.gradle.org/distributions/gradle-$GRADLE_VERSION-bin.zip && \
    unzip gradle-$GRADLE_VERSION-bin.zip && \
    rm gradle-$GRADLE_VERSION-bin.zip && \
    mv gradle-$GRADLE_VERSION /opt/gradle && \
    ln -s /opt/gradle/bin/gradle /usr/bin/gradle

# Set environment variables
ENV JAVA_HOME=/usr/lib/jvm/java-8-openjdk-amd64
ENV GRADLE_HOME=/opt/gradle
ENV PATH=$PATH:$GRADLE_HOME/bin:$JAVA_HOME/bin

# Configure user
RUN username="selenium" && \
    addgroup -gid 1000 $username && \
    mkdir -p "/home/$username" && \
    cp -a /root/. "/home/$username" && \
    adduser --uid 1000 --home "/home/$username" --gid 1000 --quiet --disabled-password --gecos "Selenium User" $username && \
    echo "$username ALL=(ALL) NOPASSWD:ALL" >> /etc/sudoers && \
    chown -R "$username:$username" "/home/$username"

# Set timezone and environment
ENV TZ=Europe/Budapest
ENV DEBIAN_FRONTEND=noninteractive
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

# Switch to selenium user
WORKDIR /home/selenium/
USER selenium
ENV HOME=/home/selenium/

CMD ["/bin/bash"]
