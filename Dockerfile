FROM jenkins/jenkins:lts

USER root

# Install tools: Maven, Python, gdown, Node.js, Appium, ADB
RUN apt-get update && \
    apt-get install -y maven python3-pip curl wget unzip && \
    pip3 install --break-system-packages --no-cache-dir gdown && \
    curl -fsSL https://deb.nodesource.com/setup_20.x | bash - && \
    apt-get install -y nodejs && \
    npm install -g appium && \
    ln -s /usr/bin/python3 /usr/bin/python && \
    wget -q https://dl.google.com/android/repository/platform-tools-latest-linux.zip -O /tmp/platform-tools.zip && \
    unzip -q /tmp/platform-tools.zip -d /opt && \
    ln -s /opt/platform-tools/adb /usr/bin/adb && \
    rm /tmp/platform-tools.zip && \
    rm -rf /var/lib/apt/lists/*

COPY entrypoint.sh /usr/local/bin/entrypoint.sh
RUN chmod +x /usr/local/bin/entrypoint.sh

USER jenkins
ENV PATH="$PATH:/usr/local/bin"
EXPOSE 8080 50000
ENTRYPOINT ["/usr/local/bin/entrypoint.sh"]
