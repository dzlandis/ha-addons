server {
  listen {{ .interface }}:{{ .port }} default_server;
  server_name _;
  include /etc/nginx/includes/server_params.conf;
  include /etc/nginx/includes/proxy_params.conf;
  
  client_max_body_size 4G;
  
  location / {
    # Remove restrictive headers that can break iframe embedding
    proxy_hide_header Content-Security-Policy;
    proxy_hide_header X-Frame-Options;
    proxy_hide_header X-Content-Type-Options;
    
    # Disable compression to allow content rewriting
    proxy_set_header Accept-Encoding "";
    
    # Proxy to kiwix-serve
    proxy_pass http://127.0.0.1:8090;
    proxy_http_version 1.1;
    proxy_set_header Upgrade $http_upgrade;
    proxy_set_header Connection $connection_upgrade;
    proxy_set_header X-Real-IP $remote_addr;
    proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;
    proxy_set_header X-Forwarded-Proto $scheme;
    proxy_set_header Host $http_host;
    proxy_set_header X-Forwarded-Host $http_host;
    
    # Content rewriting for Kiwix ingress compatibility
    sub_filter_types text/html text/css text/javascript application/javascript application/json;
    sub_filter_once off;
    
    # Rewrite absolute paths to include ingress path
    sub_filter 'href="/' 'href="{{ .ingress_entry }}/';
    sub_filter 'src="/' 'src="{{ .ingress_entry }}/';
    sub_filter 'action="/' 'action="{{ .ingress_entry }}/';
    sub_filter "href='/" "href='{{ .ingress_entry }}/";
    sub_filter "src='/" "src='{{ .ingress_entry }}/";
    sub_filter 'url("/' 'url("{{ .ingress_entry }}/';
    sub_filter "url('/" "url('{{ .ingress_entry }}/";
    
    # Handle redirects properly for ingress
    proxy_redirect ~^http://.*:8090(.*)$ {{ .ingress_entry }}$1;
    proxy_redirect ~^/(.*)$ {{ .ingress_entry }}/$1;
  }
}
