FROM jenkins/jenkins:lts

USER root

# Connecting adb devices
COPY entrypoint.sh /entrypoint.sh
RUN chmod +x /entrypoint.sh
ENTRYPOINT ["/entrypoint.sh"]

# Maven, Python, gdown, Node.js, Appium
RUN apt-get update && \
    apt-get install -y maven python3-pip curl && \
    pip3 install --break-system-packages --no-cache-dir gdown && \
    curl -fsSL https://deb.nodesource.com/setup_20.x | bash - && \
    apt-get install -y nodejs && \
    npm install -g appium && \
    ln -s /usr/bin/python3 /usr/bin/python && \
    rm -rf /var/lib/apt/lists/*

USER jenkins

ENV PATH="$PATH:/usr/local/bin"
