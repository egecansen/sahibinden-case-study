FROM jenkins/jenkins:lts

USER root

RUN apt-get update && \
    apt-get install -y maven python3-pip && \
    pip3 install --break-system-packages --no-cache-dir gdown && \
    ln -s /usr/bin/python3 /usr/bin/python && \
    rm -rf /var/lib/apt/lists/*

USER jenkins

ENV PATH="$PATH:/usr/local/bin"
