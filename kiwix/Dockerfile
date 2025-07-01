# STAGE 1: Create the final add-on image using a pre-built nginx
# https://developers.home-assistant.io/docs/add-ons/configuration#add-on-dockerfile
#
# WARNING: This approach uses a third-party, single-architecture image.
# It will likely FAIL for any user not on an amd64 (standard PC) architecture.
# The previous multi-stage build is the correct way to support all Home Assistant users.
ARG BUILD_FROM
FROM rnaresh/nginx-custom-modules:1.25.1-alpine

# Install runtime dependencies
# Note: The base image is Alpine, so we can still use apk.
# nginx is already in the image, but we need our other dependencies.
RUN \
    apk add --no-cache \
        python3 \
        bash \
        curl \
        wget

# Copy kiwix-serve from official multi-architecture Kiwix image
# This COPY command will still work and pull the correct architecture for kiwix-serve
COPY --from=ghcr.io/kiwix/kiwix-serve:latest /usr/local/bin/kiwix-serve /usr/local/bin/kiwix-serve

# Ensure kiwix-serve is executable and test it works
RUN chmod +x /usr/local/bin/kiwix-serve \
    && /usr/local/bin/kiwix-serve --version

# Copy root filesystem
COPY rootfs /

# Set permissions and create directories
RUN chmod a+x /etc/services.d/kiwix/run \
    && chmod a+x /etc/services.d/kiwix/finish \
    && chmod a+x /etc/services.d/nginx/run \
    && chmod a+x /etc/services.d/nginx/finish \
    && chmod a+x /usr/bin/kiwix-helper

# Create directory for ZIM files
RUN mkdir -p /data/zim

# Expose port
EXPOSE 8099
