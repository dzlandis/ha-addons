# STAGE 1: Build nginx with the substitutions module
FROM alpine:3.18 as builder

#hadolint ignore=DL3003
RUN \
    NGINX_VERSION="1.24.0" && \
    SUBS_FILTER_VERSION="0.6.4" && \
    apk add --no-cache --virtual .build-deps \
        build-base \
        curl \
        pcre-dev \
        zlib-dev \
        openssl-dev \
        git \
    && cd /tmp \
    && curl -fSL "http://nginx.org/download/nginx-${NGINX_VERSION}.tar.gz" -o nginx.tar.gz \
    && tar -zxf nginx.tar.gz \
    && curl -fSL "https://github.com/yaoweibin/ngx_http_substitutions_filter_module/archive/v${SUBS_FILTER_VERSION}.tar.gz" -o subs.tar.gz \
    && tar -zxf subs.tar.gz \
    && cd nginx-${NGINX_VERSION} \
    && ./configure \
        --prefix=/usr/local/nginx \
        --add-module=/tmp/ngx_http_substitutions_filter_module-${SUBS_FILTER_VERSION} \
        --with-http_ssl_module \
        --with-http_v2_module \
        --conf-path=/etc/nginx/nginx.conf \
        --sbin-path=/usr/sbin/nginx \
        --http-log-path=/var/log/nginx/access.log \
        --error-log-path=/var/log/nginx/error.log \
        --lock-path=/var/lock/nginx.lock \
        --pid-path=/var/run/nginx.pid \
        --modules-path=/usr/lib/nginx/modules \
    && make \
    && make install

# STAGE 2: Create the final add-on image
# https://developers.home-assistant.io/docs/add-ons/configuration#add-on-dockerfile
ARG BUILD_FROM
FROM $BUILD_FROM

# Install runtime dependencies
RUN \
    apk add --no-cache \
        python3 \
        bash \
        curl \
        wget

# Copy the compiled nginx binary from the builder stage
COPY --from=builder /usr/sbin/nginx /usr/sbin/nginx

# Copy kiwix-serve from official multi-architecture Kiwix image
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
