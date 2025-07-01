# https://developers.home-assistant.io/docs/add-ons/configuration#add-on-dockerfile
ARG BUILD_FROM
FROM $BUILD_FROM

# Install dependencies and Kiwix tools
ARG BUILD_ARCH
RUN \
    apk add --no-cache \
        curl \
        wget \
        python3 \
        py3-pip \
    && case "${BUILD_ARCH}" in \
        amd64) KIWIX_ARCH="linux-x86_64" ;; \
        aarch64) KIWIX_ARCH="linux-aarch64" ;; \
        armv7) KIWIX_ARCH="linux-armv7" ;; \
        armhf) KIWIX_ARCH="linux-armv6" ;; \
        i386) KIWIX_ARCH="linux-i586" ;; \
        *) echo "Unsupported architecture: ${BUILD_ARCH}" && exit 1 ;; \
    esac \
    && KIWIX_VERSION="3.3.0" \
    && curl -L "https://github.com/kiwix/kiwix-tools/releases/download/${KIWIX_VERSION}/kiwix-tools_${KIWIX_ARCH}-${KIWIX_VERSION}.tar.gz" \
        -o /tmp/kiwix-tools.tar.gz \
    && tar -xzf /tmp/kiwix-tools.tar.gz -C /tmp \
    && find /tmp -name "kiwix-serve" -type f -exec cp {} /usr/local/bin/ \; \
    && chmod +x /usr/local/bin/kiwix-serve \
    && rm -rf /tmp/kiwix-tools* \
    && rm -rf /var/cache/apk/*

# Copy root filesystem
COPY rootfs /

# Set permissions
RUN chmod a+x /etc/services.d/kiwix/run \
    && chmod a+x /etc/services.d/kiwix/finish \
    && chmod a+x /usr/bin/kiwix-helper

# Create directory for ZIM files
RUN mkdir -p /data/zim

# Expose port
EXPOSE 8080
