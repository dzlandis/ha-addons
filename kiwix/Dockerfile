# https://developers.home-assistant.io/docs/add-ons/configuration#add-on-dockerfile
ARG BUILD_FROM
FROM $BUILD_FROM

# Install required packages for Home Assistant addon
RUN \
    apk add --no-cache \
        python3 \
        bash \
        curl \
        wget \
        nginx \
    && rm -rf /var/cache/apk/*

# Copy kiwix-serve from official multi-architecture Kiwix image
COPY --from=ghcr.io/kiwix/kiwix-serve:latest /usr/local/bin/kiwix-serve /usr/local/bin/kiwix-serve

# Ensure kiwix-serve is executable and test it works
RUN chmod +x /usr/local/bin/kiwix-serve \
    && /usr/local/bin/kiwix-serve --version

# Copy root filesystem
COPY rootfs /

# Set permissions
RUN chmod a+x /etc/services.d/kiwix/run \
    && chmod a+x /etc/services.d/kiwix/finish \
    && chmod a+x /etc/services.d/nginx/run \
    && chmod a+x /etc/services.d/nginx/finish \
    && chmod a+x /usr/bin/kiwix-helper

# Create directory for ZIM files
RUN mkdir -p /data/zim

# Expose port
EXPOSE 8099
