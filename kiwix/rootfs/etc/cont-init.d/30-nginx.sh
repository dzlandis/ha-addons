#!/usr/bin/with-contenv bashio
# shellcheck shell=bash
set -e

#################
# NGINX SETTING #
#################

# Generate Ingress configuration using tempio (like Portainer)
bashio::var.json \
	interface "$(bashio::addon.ip_address)" \
	port "$(bashio::addon.ingress_port)" \
	ingress_entry "$(bashio::addon.ingress_entry)" |
	tempio \
		-template /etc/nginx/templates/ingress.gtpl \
		-out /etc/nginx/servers/ingress.conf

bashio::log.info "Nginx configured for ingress on $(bashio::addon.ip_address):$(bashio::addon.ingress_port)"
